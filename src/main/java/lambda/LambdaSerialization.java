package lambda;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.core.Jvm;
import net.openhft.chronicle.core.pool.ClassAliasPool;
import net.openhft.chronicle.core.util.SerializableFunction;
import net.openhft.chronicle.wire.BinaryWire;
import net.openhft.chronicle.wire.TextWire;
import net.openhft.chronicle.wire.Wire;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by peter on 29/08/15.
 */
@State(Scope.Thread)
public class LambdaSerialization {
    static {
        ClassAliasPool.CLASS_ALIASES.addAlias(Functions.class);
    }

    final Bytes bytes = Bytes.allocateElasticDirect();
    final Wire wire = new BinaryWire(bytes);
    final Bytes tbytes = Bytes.allocateElasticDirect();
    final TextWire twire = new TextWire(tbytes);
    byte[] byteArr = null;

    // run with -Xm1g
    public static void main(String[] args) throws RunnerException, InvocationTargetException, IllegalAccessException {
        if (Jvm.isDebug()) {
            LambdaSerialization main = new LambdaSerialization();
            for (Method m : LambdaSerialization.class.getMethods()) {
                if (m.getAnnotation(Benchmark.class) != null) {
//                    for (int i = 0; i < 1; i++) {
                    main.byteArr = null;
                    main.bytes.clear();
                    main.tbytes.clear();
                    System.out.println("Running " + m.getName());
                    Object ret = m.invoke(main);
                    if (!"Hello*".equals(ret)) {
                        throw new AssertionError(m + " returned " + ret);
                    }
//                    }
                    if (main.byteArr != null) {
                        main.tbytes.readPosition(0);
                        System.out.println(Bytes.wrapForRead(main.byteArr).toHexString());
                    }
                    if (main.bytes.writePosition() > 0) {
                        main.bytes.readPosition(0);
                        System.out.println(main.bytes.toHexString());
                    }
                    if (main.tbytes.writePosition() > 0) {
                        main.tbytes.readPosition(0);
                        System.out.println(main.tbytes.toString());
                    }
                }
            }
        } else {
            int time = Boolean.getBoolean("longTest") ? 30 : 2;
            System.out.println("measurementTime: " + time + " secs");
            Options opt = new OptionsBuilder()
                    .include(LambdaSerialization.class.getSimpleName())
                    .warmupIterations(5)
                    .measurementIterations(5)
                    .forks(5)
                    .mode(Mode.SampleTime)
                    .measurementTime(TimeValue.seconds(time))
                    .timeUnit(TimeUnit.NANOSECONDS)
                    .build();

            new Runner(opt).run();
        }
    }

    @Benchmark
    public String javaSerialization() throws IOException, ClassNotFoundException {
        SerializableFunction<String, String> appendStar = s -> s + "*";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(appendStar);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(byteArr = baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Function<String, String> fun = (Function<String, String>) ois.readObject();
        return fun.apply("Hello");
    }

    @Benchmark
    public String javaSerializationWithEnum() throws IOException, ClassNotFoundException {
        SerializableFunction<String, String> appendStar = Functions.APPEND_STAR;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(appendStar);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(byteArr = baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Function<String, String> fun = (Function<String, String>) ois.readObject();
        return fun.apply("Hello");
    }

    @Benchmark
    public String wireSerialization() throws IOException, ClassNotFoundException {
        SerializableFunction<String, String> appendStar = s -> s + "*";

        bytes.clear();
        wire.write().object(appendStar);

        Function<String, String> fun = (Function<String, String>) wire.read().object(Object.class);
        return fun.apply("Hello");
    }

    @Benchmark
    public String wireSerializationWithEnum() throws IOException, ClassNotFoundException {
        SerializableFunction<String, String> appendStar = Functions.APPEND_STAR;

        bytes.clear();
        wire.write().object(appendStar);

        Function<String, String> fun = (Function<String, String>) wire.read().object(Object.class);
        return fun.apply("Hello");
    }

    @Benchmark
    public String textWireSerialization() throws IOException, ClassNotFoundException {
        SerializableFunction<String, String> appendStar = s -> s + "*";

        tbytes.clear();
        twire.getValueOut().object(appendStar);

        Function<String, String> fun = (Function<String, String>) twire.getValueIn().object(Object.class);
        return fun.apply("Hello");
    }

    @Benchmark
    public String textWireSerializationWithEnum() throws IOException, ClassNotFoundException {
        SerializableFunction<String, String> appendStar = Functions.APPEND_STAR;

        tbytes.clear();
        twire.getValueOut().object(appendStar);

        Function<String, String> fun = (Function<String, String>) twire.getValueIn().object(Object.class);
        return fun.apply("Hello");
    }

    enum Functions implements SerializableFunction<String, String> {
        APPEND_STAR {
            @Override
            public String apply(String s) {
                return s + '*';
            }
        }
    }
}
