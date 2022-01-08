plugins {
    kotlin("jvm") version "1.6.10"
    java
}

group = "com.xbaimiao"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.google.code.gson:gson:2.8.9")
}
