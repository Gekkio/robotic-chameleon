Robotic Chameleon: Android Java library for YUV scaling and conversion
======================================================================

## Introduction
Robotic Chameleon is an Android wrapper library for [libyuv](http://code.google.com/p/libyuv/), which provides highly optimized YUV scaling and conversion functionality, and support for a high variety of color formats.
The library consists of a native shared library, and corresponding Java library JAR-file. Native libraries for all currently supported NDK architectures (`armeabi`, `armeabi-v7a`, `mips`, `x86`) are available.

Robotic Chameleon is licensed under the Apache License, Version 2.0.

### Java library

```xml
<dependency>
  <groupId>fi.gekkio.roboticchameleon</groupId>
  <artifactId>robotic-chameleon</artifactId>
  <version>0.1.0</version>
</dependency>
```

### Native library

If you want to support all kinds of devices, you should include all native libraries as dependencies. `armeabi` is enough to have support for all Android ARM devices, but `armeabi-v7a` contains highly optimized NEON code, which gives a major speed boost on modern devices. On the other hand, if you know your target device in advance, you can get a much smaller APK if you only include the native library you need.

```xml
<dependency>
  <groupId>fi.gekkio.roboticchameleon</groupId>
  <artifactId>librobotic-chameleon</artifactId>
  <version>0.1.0</version>
  <type>so</type>
  <classifier>armeabi</classifier>
</dependency>
<dependency>
  <groupId>fi.gekkio.roboticchameleon</groupId>
  <artifactId>librobotic-chameleon</artifactId>
  <version>0.1.0</version>
  <type>so</type>
  <classifier>armeabi-v7a</classifier>
</dependency>
<dependency>
  <groupId>fi.gekkio.roboticchameleon</groupId>
  <artifactId>librobotic-chameleon</artifactId>
  <version>0.1.0</version>
  <type>so</type>
  <classifier>mips</classifier>
</dependency>
<dependency>
  <groupId>fi.gekkio.roboticchameleon</groupId>
  <artifactId>librobotic-chameleon</artifactId>
  <version>0.1.0</version>
  <type>so</type>
  <classifier>x86</classifier>
</dependency>
```

## Modifying Robotic Chameleon
Robotic Chameleon depends on libyuv, which is included as a Git submodule (`git submodule init` / `git submodule update`).
The project can be compiled from the command-line using Maven 3+, or using an IDE that has support for Maven Android projects.

For example, Eclipse users will need an up-to-date Eclipse installation with m2e, and the following extensions:

+ [Android Development Tools](http://developer.android.com/tools/sdk/eclipse-adt.html)
+ [m2e-android](http://rgladwell.github.io/m2e-android/)
