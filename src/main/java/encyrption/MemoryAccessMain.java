package encyrption;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * This benchmark looks at the relative cost of different operations when use with memory access scan.
 */
public class MemoryAccessMain {
    public static final int ARRAY_BYTE_BASE_OFFSET;
    public static final int ARRAY_INT_BASE_OFFSET;
    static final String text = "0123456789" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz-_" +
            "0123456789" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz-_";
    static final Unsafe UNSAFE;
    static final int ARRAY_LONG_BASE_OFFSET;
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static byte[] byteArray = text.getBytes(ISO_8859_1);
    static int[] intArray = new int[byteArray.length / 4];
    static int[] longArray = new int[byteArray.length / 8];
    static ByteBuffer bb = ByteBuffer.allocateDirect(text.length());

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            ARRAY_BYTE_BASE_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);
            ARRAY_INT_BASE_OFFSET = UNSAFE.arrayBaseOffset(int[].class);
            ARRAY_LONG_BASE_OFFSET = UNSAFE.arrayBaseOffset(long[].class);

            UNSAFE.copyMemory(byteArray, ARRAY_BYTE_BASE_OFFSET, intArray, ARRAY_INT_BASE_OFFSET, byteArray.length);
            ;
            UNSAFE.copyMemory(byteArray, ARRAY_BYTE_BASE_OFFSET, longArray, ARRAY_LONG_BASE_OFFSET, byteArray.length);
            for (int i = 0; i < text.length(); i++)
                bb.put((byte) text.charAt(i));
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    static long address(ByteBuffer bb) {
        return ((sun.nio.ch.DirectBuffer) bb).address();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MemoryAccessMain.class.getSimpleName())
                .forks(9)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public long checkSumWithCopyByByte() {
        UNSAFE.copyMemory(null, address(bb), byteArray, ARRAY_BYTE_BASE_OFFSET, byteArray.length);
        long sum = 0;
        for (byte b : byteArray)
            sum += b;
        return sum;
    }

    @Benchmark
    public long checkSumWithCopyByInt() {
        UNSAFE.copyMemory(null, address(bb), intArray, ARRAY_INT_BASE_OFFSET, intArray.length * 4);
        long sum = 0;
        for (int b : intArray)
            sum += b;
        return sum;
    }

    @Benchmark
    public long checkSumWithCopyByLong() {
        UNSAFE.copyMemory(null, address(bb), longArray, ARRAY_LONG_BASE_OFFSET, longArray.length * 8);
        long sum = 0;
        for (long l : longArray)
            sum += l;
        return sum;
    }

    @Benchmark
    public long checkSumByteBufferByByte() {
        long sum = 0;
        int len = bb.capacity();
        for (int i = 0; i < len; i++)
            sum += bb.get(i);
        return sum;
    }

    @Benchmark
    public long checkSumByteBufferByInt() {
        long sum = 0;
        int len = bb.capacity();
        for (int i = 0; i < len; i += 4)
            sum += bb.getInt(i);
        return sum;
    }

    @Benchmark
    public long checkSumByteBufferByLong() {
        long sum = 0;
        int len = bb.capacity();
        for (int i = 0; i < len; i += 8)
            sum += bb.getLong(i);
        return sum;
    }

    @Benchmark
    public long checkSumNativeMemoryByByte() {
        long sum = 0;
        long address = address(bb);
        int len = bb.capacity();
        for (int i = 0; i < len; i++)
            sum += UNSAFE.getByte(address + i);
        return sum;
    }

    @Benchmark
    public long checkSumNativeMemoryByInt() {
        long sum = 0;
        long address = address(bb);
        int len = bb.capacity();
        for (int i = 0; i < len; i += 4)
            sum += UNSAFE.getInt(address + i);
        return sum;
    }

    @Benchmark
    public long checkSumNativeMemoryByLong() {
        long sum = 0;
        long address = address(bb);
        int len = bb.capacity();
        for (int i = 0; i < len; i += 8)
            sum += UNSAFE.getLong(address + i);
        return sum;
    }

}

/*
Java 6 update 45

MemoryAccessMain.checkSumByteBufferByByte    thrpt  180  15765664.870 ±  45281.627  ops/s
MemoryAccessMain.checkSumByteBufferByInt     thrpt  180  12623510.616 ±  16533.129  ops/s
MemoryAccessMain.checkSumByteBufferByLong    thrpt  180  12073116.876 ±  39165.995  ops/s
MemoryAccessMain.checkSumNativeMemoryByByte  thrpt  180  17155297.294 ±  21487.254  ops/s
MemoryAccessMain.checkSumNativeMemoryByInt   thrpt  180  44490609.186 ±  78431.971  ops/s
MemoryAccessMain.checkSumNativeMemoryByLong  thrpt  180  66721501.649 ±  99363.707  ops/s
MemoryAccessMain.checkSumWithCopyByByte      thrpt  180  20867969.278 ± 265946.406  ops/s
MemoryAccessMain.checkSumWithCopyByInt       thrpt  180  46016224.415 ± 569515.894  ops/s
MemoryAccessMain.checkSumWithCopyByLong      thrpt  180  60932722.843 ± 115947.869  ops/s
 */

/*
Java 7 update 79
Benchmark                                     Mode  Cnt         Score        Error  Units
MemoryAccessMain.checkSumByteBufferByByte    thrpt  180  16918577.630 ±  51245.822  ops/s
MemoryAccessMain.checkSumByteBufferByInt     thrpt  180  36167662.991 ± 139725.872  ops/s
MemoryAccessMain.checkSumByteBufferByLong    thrpt  180  56660167.208 ± 276707.368  ops/s
MemoryAccessMain.checkSumNativeMemoryByByte  thrpt  180  17523037.640 ± 183608.284  ops/s
MemoryAccessMain.checkSumNativeMemoryByInt   thrpt  180  52735133.453 ± 397806.177  ops/s
MemoryAccessMain.checkSumNativeMemoryByLong  thrpt  180  83016876.842 ± 830511.972  ops/s
MemoryAccessMain.checkSumWithCopyByByte      thrpt  180  21979723.170 ±  52855.668  ops/s
MemoryAccessMain.checkSumWithCopyByInt       thrpt  180  49464500.478 ± 156216.776  ops/s
MemoryAccessMain.checkSumWithCopyByLong      thrpt  180  61204197.542 ± 882471.209  ops/s
 */

/*
Java 8 update 51

Benchmark                                     Mode  Cnt         Score         Error  Units
MemoryAccessMain.checkSumByteBufferByByte    thrpt  180  16370833.979 ±  107792.167  ops/s
MemoryAccessMain.checkSumByteBufferByInt     thrpt  180  35119418.564 ±  588643.940  ops/s
MemoryAccessMain.checkSumByteBufferByLong    thrpt  180  53270687.238 ± 1159158.958  ops/s
MemoryAccessMain.checkSumNativeMemoryByByte  thrpt  180  16883728.154 ±  368126.958  ops/s
MemoryAccessMain.checkSumNativeMemoryByInt   thrpt  180  54721757.958 ±  528531.287  ops/s
MemoryAccessMain.checkSumNativeMemoryByLong  thrpt  180  94902919.608 ± 2540250.454  ops/s
MemoryAccessMain.checkSumWithCopyByByte      thrpt  180  21851803.842 ±   63869.431  ops/s
MemoryAccessMain.checkSumWithCopyByInt       thrpt  180  56224050.537 ±  572306.198  ops/s
MemoryAccessMain.checkSumWithCopyByLong      thrpt  180  70043182.790 ±  300654.953  ops/s
 */
