# Overview
This test looks at different ways to serialize and deserialize using functions.

## Serialized form
Running javaSerialization
```
00000000 AC ED 00 05 73 72 00 21  6A 61 76 61 2E 6C 61 6E ····sr·! java.lan
00000010 67 2E 69 6E 76 6F 6B 65  2E 53 65 72 69 61 6C 69 g.invoke .Seriali
00000020 7A 65 64 4C 61 6D 62 64  61 6F 61 D0 94 2C 29 36 zedLambd aoa··,)6
00000030 85 02 00 0A 49 00 0E 69  6D 70 6C 4D 65 74 68 6F ····I··i mplMetho
00000040 64 4B 69 6E 64 5B 00 0C  63 61 70 74 75 72 65 64 dKind[·· captured
00000050 41 72 67 73 74 00 13 5B  4C 6A 61 76 61 2F 6C 61 Argst··[ Ljava/la
00000060 6E 67 2F 4F 62 6A 65 63  74 3B 4C 00 0E 63 61 70 ng/Objec t;L··cap
00000070 74 75 72 69 6E 67 43 6C  61 73 73 74 00 11 4C 6A turingCl asst··Lj
00000080 61 76 61 2F 6C 61 6E 67  2F 43 6C 61 73 73 3B 4C ava/lang /Class;L
00000090 00 18 66 75 6E 63 74 69  6F 6E 61 6C 49 6E 74 65 ··functi onalInte
000000a0 72 66 61 63 65 43 6C 61  73 73 74 00 12 4C 6A 61 rfaceCla sst··Lja
000000b0 76 61 2F 6C 61 6E 67 2F  53 74 72 69 6E 67 3B 4C va/lang/ String;L
000000c0 00 1D 66 75 6E 63 74 69  6F 6E 61 6C 49 6E 74 65 ··functi onalInte
000000d0 72 66 61 63 65 4D 65 74  68 6F 64 4E 61 6D 65 71 rfaceMet hodNameq
000000e0 00 7E 00 03 4C 00 22 66  75 6E 63 74 69 6F 6E 61 ·~··L·"f unctiona
000000f0 6C 49 6E 74 65 72 66 61  63 65 4D 65 74 68 6F 64 lInterfa ceMethod
00000100 53 69 67 6E 61 74 75 72  65 71 00 7E 00 03 4C 00 Signatur eq·~··L·
00000110 09 69 6D 70 6C 43 6C 61  73 73 71 00 7E 00 03 4C ·implCla ssq·~··L
00000120 00 0E 69 6D 70 6C 4D 65  74 68 6F 64 4E 61 6D 65 ··implMe thodName
00000130 71 00 7E 00 03 4C 00 13  69 6D 70 6C 4D 65 74 68 q·~··L·· implMeth
00000140 6F 64 53 69 67 6E 61 74  75 72 65 71 00 7E 00 03 odSignat ureq·~··
00000150 4C 00 16 69 6E 73 74 61  6E 74 69 61 74 65 64 4D L··insta ntiatedM
00000160 65 74 68 6F 64 54 79 70  65 71 00 7E 00 03 78 70 ethodTyp eq·~··xp
00000170 00 00 00 06 75 72 00 13  5B 4C 6A 61 76 61 2E 6C ····ur·· [Ljava.l
00000180 61 6E 67 2E 4F 62 6A 65  63 74 3B 90 CE 58 9F 10 ang.Obje ct;··X··
00000190 73 29 6C 02 00 00 78 70  00 00 00 00 76 72 00 1A s)l···xp ····vr··
000001a0 6C 61 6D 62 64 61 2E 4C  61 6D 62 64 61 53 65 72 lambda.L ambdaSer
000001b0 69 61 6C 69 7A 61 74 69  6F 6E 00 00 00 00 00 00 ializati on······
000001c0 00 00 00 00 00 78 70 74  00 34 6E 65 74 2F 6F 70 ·····xpt ·4net/op
000001d0 65 6E 68 66 74 2F 63 68  72 6F 6E 69 63 6C 65 2F enhft/ch ronicle/
000001e0 63 6F 72 65 2F 75 74 69  6C 2F 53 65 72 69 61 6C core/uti l/Serial
000001f0 69 7A 61 62 6C 65 46 75  6E 63 74 69 6F 6E 74 00 izableFu nctiont·
00000200 05 61 70 70 6C 79 74 00  26 28 4C 6A 61 76 61 2F ·applyt· &(Ljava/
00000210 6C 61 6E 67 2F 4F 62 6A  65 63 74 3B 29 4C 6A 61 lang/Obj ect;)Lja
00000220 76 61 2F 6C 61 6E 67 2F  4F 62 6A 65 63 74 3B 74 va/lang/ Object;t
00000230 00 1A 6C 61 6D 62 64 61  2F 4C 61 6D 62 64 61 53 ··lambda /LambdaS
00000240 65 72 69 61 6C 69 7A 61  74 69 6F 6E 74 00 23 6C erializa tiont·#l
00000250 61 6D 62 64 61 24 6A 61  76 61 53 65 72 69 61 6C ambda$ja vaSerial
00000260 69 7A 61 74 69 6F 6E 24  65 61 31 61 64 31 31 30 ization$ ea1ad110
00000270 24 31 74 00 26 28 4C 6A  61 76 61 2F 6C 61 6E 67 $1t·&(Lj ava/lang
00000280 2F 53 74 72 69 6E 67 3B  29 4C 6A 61 76 61 2F 6C /String; )Ljava/l
00000290 61 6E 67 2F 53 74 72 69  6E 67 3B 71 00 7E 00 0E ang/Stri ng;q·~··
```

Running javaSerializationWithEnum
```
00000000 AC ED 00 05 7E 72 00 24  6C 61 6D 62 64 61 2E 4C ····~r·$ lambda.L
00000010 61 6D 62 64 61 53 65 72  69 61 6C 69 7A 61 74 69 ambdaSer ializati
00000020 6F 6E 24 46 75 6E 63 74  69 6F 6E 73 00 00 00 00 on$Funct ions····
00000030 00 00 00 00 12 00 00 78  72 00 0E 6A 61 76 61 2E ·······x r··java.
00000040 6C 61 6E 67 2E 45 6E 75  6D 00 00 00 00 00 00 00 lang.Enu m·······
00000050 00 12 00 00 78 70 74 00  0B 41 50 50 45 4E 44 5F ····xpt· ·APPEND_
00000060 53 54 41 52                                      STAR             
```

Running wireSerialization
```
00000000 C0 B6 10 53 65 72 69 61  6C 69 7A 65 64 4C 61 6D ···Seria lizedLam
00000010 62 64 61 82 3D 01 00 00  C2 63 63 BC 1A 6C 61 6D bda·=··· ·cc··lam
00000020 62 64 61 2E 4C 61 6D 62  64 61 53 65 72 69 61 6C bda.Lamb daSerial
00000030 69 7A 61 74 69 6F 6E C3  66 69 63 B8 34 6E 65 74 ization· fic·4net
00000040 2F 6F 70 65 6E 68 66 74  2F 63 68 72 6F 6E 69 63 /openhft /chronic
00000050 6C 65 2F 63 6F 72 65 2F  75 74 69 6C 2F 53 65 72 le/core/ util/Ser
00000060 69 61 6C 69 7A 61 62 6C  65 46 75 6E 63 74 69 6F ializabl eFunctio
00000070 6E C4 66 69 6D 6E E5 61  70 70 6C 79 C4 66 69 6D n·fimn·a pply·fim
00000080 73 B8 26 28 4C 6A 61 76  61 2F 6C 61 6E 67 2F 4F s·&(Ljav a/lang/O
00000090 62 6A 65 63 74 3B 29 4C  6A 61 76 61 2F 6C 61 6E bject;)L java/lan
000000a0 67 2F 4F 62 6A 65 63 74  3B C3 69 6D 6B 06 C2 69 g/Object ;·imk··i
000000b0 63 FA 6C 61 6D 62 64 61  2F 4C 61 6D 62 64 61 53 c·lambda /LambdaS
000000c0 65 72 69 61 6C 69 7A 61  74 69 6F 6E C3 69 6D 6E erializa tion·imn
000000d0 B8 23 6C 61 6D 62 64 61  24 77 69 72 65 53 65 72 ·#lambda $wireSer
000000e0 69 61 6C 69 7A 61 74 69  6F 6E 24 65 61 31 61 64 ializati on$ea1ad
000000f0 31 31 30 24 31 C3 69 6D  73 B8 26 28 4C 6A 61 76 110$1·im s·&(Ljav
00000100 61 2F 6C 61 6E 67 2F 53  74 72 69 6E 67 3B 29 4C a/lang/S tring;)L
00000110 6A 61 76 61 2F 6C 61 6E  67 2F 53 74 72 69 6E 67 java/lan g/String
00000120 3B C3 69 6D 74 B8 26 28  4C 6A 61 76 61 2F 6C 61 ;·imt·&( Ljava/la
00000130 6E 67 2F 53 74 72 69 6E  67 3B 29 4C 6A 61 76 61 ng/Strin g;)Ljava
00000140 2F 6C 61 6E 67 2F 53 74  72 69 6E 67 3B C2 63 61 /lang/St ring;·ca
00000150 82 00 00 00 00                                   ·····            
```

Running wireSerializationWithEnum
```
00000000 C0 B6 09 46 75 6E 63 74  69 6F 6E 73 EB 41 50 50 ···Funct ions·APP
00000010 45 4E 44 5F 53 54 41 52                          END_STAR         
```

Running textWireSerialization
```
!SerializedLambda {
  cc: !type lambda.LambdaSerialization,
  fic: net/openhft/chronicle/core/util/SerializableFunction,
  fimn: apply,
  fims: (Ljava/lang/Object;)Ljava/lang/Object;,
  imk: 6,
  ic: lambda/LambdaSerialization,
  imn: lambda$textWireSerialization$ea1ad110$1,
  ims: (Ljava/lang/String;)Ljava/lang/String;,
  imt: (Ljava/lang/String;)Ljava/lang/String;,
  ca: [
  ]
}
```

Running textWireSerializationWithEnum
```
!Functions APPEND_STAR
```
