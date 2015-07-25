package utf8;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by peter.lawrey on 24/07/2015.
 */
@State(Scope.Thread)
public class DecodeMain {
    static final String text = "0123456789" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz-_" +
            "0123456789" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz-_";

    static final Unsafe UNSAFE;

    static final Field VALUE, COUNT;
    ByteBuffer bb = ByteBuffer.allocateDirect(128); // plenty for a 64 character string.
    StringBuilder sb = new StringBuilder();

    {
        bb.clear();
        bb.put(text.getBytes(StandardCharsets.UTF_8));
    }

    static {
        assert text.length() == 128;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);

            VALUE = Class.forName("java.lang.AbstractStringBuilder").getDeclaredField("value");
            VALUE.setAccessible(true);

            COUNT = Class.forName("java.lang.AbstractStringBuilder").getDeclaredField("count");
            COUNT.setAccessible(true);

        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DecodeMain.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public CharSequence simpleFromUTF8() {
        bb.clear();
        byte[] bytes = new byte[bb.remaining()];
        bb.get(bytes);
        String s = new String(bytes, StandardCharsets.UTF_8);
        assert s.equals(text);
        return s;
    }

    @Benchmark
    public CharSequence usingSimpleLoop() {
        bb.clear();
        sb.setLength(0);
        ascii:
        {
            while (bb.remaining() > 0) {
                byte b = bb.get();
                if (b < 0) break ascii;
                sb.append((char) b);
            }
            assert sb.toString().equals(text);
            return sb;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }

    @Benchmark
    public CharSequence usingCharArray() throws IllegalAccessException {
        bb.clear();
        sb.setLength(0);
        int len = bb.capacity();
        sb.ensureCapacity(len);
        char[] chars = (char[]) VALUE.get(sb);
        int i = 0;
        ascii:
        {
            for (; i < len; i++) {
                byte b = bb.get();
                if (b < 0) break ascii;
                chars[i] = (char) b;
            }
            COUNT.set(sb, len);
            assert sb.toString().equals(text);
            return sb;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }

    @Benchmark
    public CharSequence usingCharArrayAndAddress() throws IllegalAccessException {
        long address = ((DirectBuffer) bb).address();
        sb.setLength(0);
        int len = bb.capacity();
        sb.ensureCapacity(len);
        char[] chars = (char[]) VALUE.get(sb);
        int i = 0;
        ascii:
        {
            for (; i < len; i++) {
                byte b = UNSAFE.getByte(address + i);
                if (b < 0) break ascii;
                chars[i] = (char) b;
            }
            COUNT.set(sb, len);
            assert sb.toString().equals(text);
            return sb;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }
}
