package javatest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * This benchmark looks at the relative cost of different operations when use with memory access scan.
 */
/* performing 4 instanceof taking 2.2 ns each.
Benchmark                   Mode  Cnt          Score         Error  Units
InstanceOfMain.instanceOf  thrpt   20  113904463.916 � 6213493.515  ops/s
 */
public class InstanceOfMain {
    static Object[] o = {(byte) 1, 1, 1.0, 1.0f};

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(InstanceOfMain.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public boolean instanceOf() {
        boolean allNum = true;
        for (Object v : o) {
            allNum &= v instanceof Number;
        }
        return allNum;
    }
}