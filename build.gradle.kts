plugins {
    kotlin("jvm") version "1.6.10"
    java
    id ("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.xbaimiao"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
}
