# Wresult (wrapped Result)

Wresult is a monad aimed to simplify the handling of Success and Failure scenarios in Kotlin.

### Installation

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {  
  ...
  repositories {
    maven { url "https://jitpack.io" }
  }
}  
```

**Step 2.** Add the dependency

```gradle
dependencies {
    implementation 'com.github.tscheppe:wresult:<wresult_version>
}
```
