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
import java.nio.charset.Charset;

/**
 * The purpose of this test is to converting a CharSequence to a direct buffer.
 */
@State(Scope.Thread)
public class EncodeMain {
    static final String text = "0123456789" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz-_" +
            "0123456789" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz-_";

    static final Unsafe UNSAFE;

    static final Field VALUE;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    static {
        assert text.length() == 128;
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);

            VALUE = String.class.getDeclaredField("value");
            VALUE.setAccessible(true);

        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    ByteBuffer bb = ByteBuffer.allocateDirect(128 * 3); // plenty for a 64 character string.

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(EncodeMain.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void encode_simpleToUTF8() {
        bb.clear();
        bb.put(text.getBytes(UTF_8));
    }

    @Benchmark
    public void encode_usingSimpleLoop() {
        bb.clear();
        ascii:
        {
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (ch > 0x7f) break ascii;
                bb.put((byte) ch);
            }
            return;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }

    @Benchmark
    public void encode_usingSimpleLoopUnrolled() {
        bb.clear();
        ascii:
        {
            for (int i = 0; i < text.length() - 1; i += 2) {
                char ch = text.charAt(i);
                char ch2 = text.charAt(i + 1);
                if ((ch | ch2) > 0x7f) break ascii;
                bb.putShort((short) ((ch << 8) | ch2));
            }
            return;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }

    @Benchmark
    public void encode_unsafeLoopCharArray() throws IllegalAccessException {
        long address = ((DirectBuffer) bb).address(), ptr = address;
        char[] value = (char[]) VALUE.get(text);
        if (bb.capacity() < value.length * 3)
            throw new AssertionError();
        ascii:
        {
            for (char ch : value) {
                if (ch > 0x7f) break ascii;
                UNSAFE.putByte(ptr++, (byte) ch);
            }
            bb.position((int) (ptr - address));
            return;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }

    @Benchmark
    public void encode_unsafeLoopCharAt() {
        long address = ((DirectBuffer) bb).address(), ptr = address;
        if (bb.capacity() < text.length() * 3)
            throw new AssertionError();
        ascii:
        {
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (ch > 0x7f) break ascii;
                UNSAFE.putByte(ptr++, (byte) ch);
            }
            bb.position((int) (ptr - address));
            return;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }

    @Benchmark
    public void encode_unsafeLoopCharAtUnrolled() {
        long address = ((DirectBuffer) bb).address(), ptr = address;
        if (bb.capacity() < text.length() * 3)
            throw new AssertionError();
        ascii:
        {
            for (int i = 0; i < text.length() - 3; i += 4) {
                char ch = text.charAt(i);
                char ch2 = text.charAt(i + 1);
                char ch3 = text.charAt(i + 2);
                char ch4 = text.charAt(i + 3);
                if ((ch | ch2 | ch3 | ch4) > 0x7f) break ascii;
                UNSAFE.putInt(ptr, (ch << 24) | (ch2 << 16) | (ch3 << 8) | ch4);
                ptr += 4;
            }
            bb.position((int) (ptr - address));
            return;
        }
        throw new UnsupportedOperationException("Handled in full implementation");
    }
}
