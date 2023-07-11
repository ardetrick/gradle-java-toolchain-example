# Gradle Java Toolchain Example (Using Kotlin DSL)

test

An example of how to use the Gradle Toolchains for a Java project using the Gradle Kotlin DSL. Gradle
[Toolchains](https://docs.gradle.org/current/userguide/toolchains.html) allow different tasks to use a different Java
version (and even a specific vendor). Allowing an easy way to compile on one version, but run on another. In addition,
Gradle can be configured to automatically attempt a download of a missing JDK in case it cannot be found locally,
minimizing the expectations of the host running the commands.

## Getting Started

No dependencies required! Start with any one of these commands:

```shell
# Compiles with Java 15 runs on Java 16 (Amazon). 
./gradlew run
# Compiles with Java 15 runs on Java 20 (Adoptium).
./gradlew runOn20
# Compiles with Java 16 runs tests on Java 16.
./gradlew testOn16
```

Note that if a Java version cannot be found locally, it will download it. This may take some time.

View the console output of the "run" commands to see what version of Java was used to compile and
what version of Java was used at runtime.

## Toolchain Download Repositories

Read the full Gradle docs [here](https://docs.gradle.org/current/userguide/toolchains.html#sub:download_repositories).

The [foojay resolver](https://github.com/gradle/foojay-toolchains) configured in `settings.gradle.kts` handles the
resolving Java runtimes.

To show all tool chains for a project, run:

```shell
./gradlew -q javaToolchains
```

By default, any tool chains downloaded will be placed in `$HOME/.gradle/jdks`.
