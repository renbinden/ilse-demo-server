plugins {
    kotlin("jvm") version "1.3.71"
}

group = "uk.co.renbinden"
version = "1.4.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(group = "org.http4k", name = "http4k-core", version = "3.242.0")
    implementation(group = "org.http4k", name = "http4k-server-jetty", version = "3.242.0")
    implementation(group = "org.http4k", name = "http4k-client-websocket", version = "3.242.0")
    implementation(group = "org.slf4j", name = "slf4j-jdk14", version = "1.7.30")
}