package utf8;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by peter.lawrey on 25/07/2015.
 */
public class AllMain {

    // run with -Xmx8m
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DecodeMain.class.getSimpleName())
                .include(EncodeMain.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
