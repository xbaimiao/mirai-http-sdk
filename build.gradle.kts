plugins {
    kotlin("jvm") version "1.6.10"
    java
    `maven-publish`
}

group = "com.xbaimiao"
version = "1.0.2-mini"

repositories {
    mavenCentral()
    maven(url = uri("https://run.xbaimiao.com/nexus/repository/maven-releases/"))
}

java {
    withSourcesJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}


dependencies {
    compileOnly("com.google.code.gson:gson:2.8.9")
    compileOnly("org.greenrobot:eventbus:3.1.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

publishing {
    repositories {
        maven("https://run.xbaimiao.com/nexus/repository/maven-releases/") {
            credentials {
                username = project.findProperty("user").toString()
                password = project.findProperty("password").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "com.xbaimiao"
        }
    }
}