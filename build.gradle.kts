import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.BufferedReader
import java.io.InputStreamReader

plugins {
    kotlin("jvm") version "1.6.10"
    java
    id("com.github.johnrengelman.shadow") version ("7.1.2")
    `maven-publish`
}

group = "com.xbaimiao"
version = "1.0.4-alpha-" + id()

repositories {
    mavenCentral()
    maven(url = uri("https://run.xbaimiao.com/releases/"))
}

java {
    withSourcesJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.shadowJar{
    relocate("com.google.gson", "com.xbaimiao.mirai.libs.gson")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    repositories {
        maven("https://repo.fastmcmirror.org/content/repositories/releases/") {
            credentials {
                username = System.getenv("RepoUser")
                password = System.getenv("RepoPassword")
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

fun id(): String{
    val process = Runtime.getRuntime().exec("git rev-parse --short HEAD")
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val cid = reader.readLine()
    if (cid==null) {
        println("Failed get commit id")
        System.exit(0)
    }
    println("Commit ID: " + cid)
    return cid;
}