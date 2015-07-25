The comparison of different encoding and decoding approaches.

The plan is to add more over time.

Run on an i7-4770K processor with Oracle Java 8 update 51 -Xmx4m to simulate a machine under memory pressure with a number of active threads.
```
Benchmark                                    Mode  Cnt         Score        Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   2643775.833 �  85213.308  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   7372069.315 �  41969.895  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  10272300.095 � 164331.025  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1882715.657 �   5721.681  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   2298122.062 �  47455.846  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  17402535.621 � 112926.708  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20  18129064.557 � 191216.487  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20  13848769.378 �  60555.711  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   8824218.966 � 492007.547  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   7091716.147 �  19232.591  ops/s

```

Run with -Xmx16m
```
Benchmark                                    Mode  Cnt         Score        Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   5395187.171 �  17525.486  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   7967263.552 � 259148.327  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  11644515.566 �  52786.179  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1884355.264 �   3442.892  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   5050422.611 �  31681.322  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  16837387.866 � 814047.308  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20  18225151.521 � 132811.688  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20  13848365.955 � 102407.681  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   8868356.295 � 368546.131  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   7077634.663 �  30359.636  ops/s
```

Run with -Xmx64m
```
Benchmark                                    Mode  Cnt         Score        Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   6796923.881 �  22116.936  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   8116746.205 �  26883.030  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  11831718.908 � 109633.121  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1852540.497 �  65251.968  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   6556082.249 �  88612.336  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  17307141.673 � 184737.866  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20  17922513.884 � 772599.551  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20  13862767.881 �  39514.729  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   8977483.098 �  34449.368  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   7027436.605 � 137810.247  ops/s
```

Run on an i7-4770K processor with Oracle Java 8 update 51  -Xmx2g to simulate a machine running single threaded i.e. not competing for resources.

```
Benchmark                                    Mode  Cnt         Score        Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   7202404.552 � 250191.323  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   8031623.244 �  45445.473  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  11742226.435 �  50485.030  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1823612.987 �  96290.363  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   7377574.919 � 201953.118  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  15567529.664 �  58888.269  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20  18058413.029 � 122003.798  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20  13792889.546 �  87887.202  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   8977592.746 �  26577.712  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   7037589.895 �  69803.496  ops/s
```

