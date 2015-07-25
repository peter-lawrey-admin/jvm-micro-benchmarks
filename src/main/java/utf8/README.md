The comparison of different encoding and decoding approaches.

The plan is to add more over time.

Run on an i7-4770K processor with -Xmx4m to simulate a machine under memory pressure with a number of active threads.
```
Benchmark                                    Mode  Cnt         Score         Error  Units
DecodeMain.decode_fromUTF8                  thrpt   20   2586008.394 �   58076.968  ops/s
DecodeMain.decode_usingCharArray            thrpt   20   7060442.199 �   80661.464  ops/s
DecodeMain.decode_usingCharArrayAndAddress  thrpt   20  12157961.284 �   57507.939  ops/s
DecodeMain.decode_usingSimpleLoop           thrpt   20   1868694.780 �   11040.846  ops/s
EncodeMain.encode_simpleToUTF8              thrpt   20   2154026.508 �  122232.792  ops/s
EncodeMain.encode_unsafeLoopCharArray       thrpt   20  13011833.190 � 4666104.526  ops/s
EncodeMain.encode_unsafeLoopCharAt          thrpt   20   8101098.141 �  379051.713  ops/s
EncodeMain.encode_unsafeLoopCharAtUnrolled  thrpt   20   7501280.864 �  603143.596  ops/s
EncodeMain.encode_usingSimpleLoop           thrpt   20   4387012.497 �  194208.915  ops/s
EncodeMain.encode_usingSimpleLoopUnrolled   thrpt   20   3587829.134 �   98977.419  ops/s

```