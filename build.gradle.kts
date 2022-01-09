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
    implementation("com.google.code.gson:gson:2.8.9")
//    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("org.greenrobot:eventbus:3.1.1")
}
