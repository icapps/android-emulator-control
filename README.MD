
# Android Emulator Control

[![Release](https://jitpack.io/v/icapps/android-emulator-control.svg)](https://jitpack.io/icapps/android-emulator-control)

A library that facilitates controlling an android emulator

## Features
Java library interface for controlling emulator
  - Control rotation
  - Pause/Resume execution
  - Control data/voice state
  - Control battery state
  - ...
  
The library has access to the same commands as can be executed by creating a telnet connection to the emulator. In fact,
that is how the library works: it creates a telnet connection to the emulator and executes the commands.

## Usage

Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:
```gradle
dependencies {
    compile 'com.github.icapps:android-emulator-control:{latest version}'
}
```

Library usage:
```Java
//Create emulator control instance where port is the emulator port, usually 5554 and authToken is the token stored in ~/.emulator_console_auth_token
Emulator emulator = EmulatorFactory.create(port, authToken);
emulator.rotate();
emulator.getPowerControl().powerConfig();
...
```

## Changelog

**V 0.1.0**
  - Initial release