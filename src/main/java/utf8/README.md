The comparison of different encoding and decoding approaches.

The plan is to add more over time.

Run on an i7-3790X with Oracle Java 8 update 51 and -Xmx64m (Ubuntu 15.04)
```
Benchmark                                    Mode  Cnt         Score        Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   7253280.165 ±  65361.741  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   8390367.697 ±  68847.882  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  12741425.467 ± 195475.708  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1940184.123 ±  14156.122  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   6499953.595 ±  69457.824  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  19799238.628 ± 107360.636  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20  15252056.597 ±  98216.894  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20  15264096.424 ±  64860.594  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   8434139.748 ± 302338.290  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   6338738.829 ±  47627.463  ops/s
```

Run on an i7-4770K with Oracle Java 8 update 51 and -Xmx64m (Windows 8.1)
```
Benchmark                                    Mode  Cnt         Score        Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   6796923.881 �  22116.936  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   8116746.205 �  26883.030  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  11831718.908 � 109633.121  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1852540.497 �  65251.968  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   6370133.609 �  50136.218  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  17010210.148 � 413837.676  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20  12008413.335 � 125709.384  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20  11752050.579 �  43969.260  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   7895278.352 �  35139.565  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   5620780.588 �  72783.775  ops/s
```

Run on an E5-2650 v2 with Oracle Java 8 update 51 with -Xmx64m (Ubuntu 15.04)

```
Benchmark                                    Mode  Cnt         Score        Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   6286867.336 ±  86341.070  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   8764641.857 ±  33268.734  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  11328164.878 ±  75575.608  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1832553.738 ±   7666.224  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   5921890.505 ±  97402.981  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  17719733.037 ± 918121.187  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20  19295214.288 ±  80538.706  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20  14446669.872 ±  59633.767  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   9086042.303 ±  27818.989  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   7314359.149 ±  19259.337  ops/s
```