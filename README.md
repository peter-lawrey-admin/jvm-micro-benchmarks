# jvm-micro-benchmarks
Microbenchmarks for JVM code.

Some benchmarks use JMH for latency test.

# Co-ordinated omission
Some benchmarks are hand written to compensate for co-ordinated ommission.  
Co-ordinated ommission can result in dramatically under rating poor latencies.  
The difference can be that your 99.999% latency with co-ordniated omission is actually you 90% latency.
Correcting for co-ordinated omission can help how why you have problems in real systems.

The way we correct for co-ordinated omission is to not time the latency from when we start the test, 
but instead we time from when the tests *should have started*.  To have a view on this, we have to have a
throughput we are testing for.  One way of doing this is to tst at regular intervals, however this can
give overly optimisitic figures as well.  To produce more robust figures we randomise the time between
tests so that we have an average interval which achieves the desired throughput.
```java
int rate = ... ; // the rate being tested.
long next = System.nanoTime();
int interval = (int) (1e9 / rate);
Random rand = new Random();
for (int t = 0; t < tests; t++) {
    next += rand.nextInt(2 * interval); // avg = interval.
    while(System.nanoTime() < next) {
        // busy wait
    }
    
    performTest(i);
    
    times[i] = System.nanoTime() - next;
}
```