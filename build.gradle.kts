plugins {
    id("java")
    id("application")
}

group = "com.ardetrick"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.ardetrick.Main")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.0")
}

// Use Java 16 by default
java.toolchain {
    languageVersion.set(JavaLanguageVersion.of(16))
    vendor.set(JvmVendorSpec.AMAZON)
}

// Set the compiler to Java 15 for tests
tasks.withType<JavaCompile> {
    javaCompiler.set(javaToolchains.compilerFor {
        languageVersion.set(JavaLanguageVersion.of(15))
    })
}

// Run tests on Java 16
tasks.register<Test>("testOn16") {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(16))
    })
}

// Run application on Java 20
tasks.register<JavaExec>("runOn20") {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(20))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    })
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.ardetrick.Main")
}

// Run application on Java 20
tasks.register<JavaExec>("runOn21") {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.AZUL)
    })
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.ardetrick.Main")
}

tasks.withType<Test> {
    useJUnitPlatform()
    // Prints the console output from tests to the Gradle output - makes it easier to observe.
    testLogging.showStandardStreams = true
}
