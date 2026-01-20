plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // versions
    val kotlinArrowCore = "2.1.2"

    // implementations
    implementation("io.arrow-kt:arrow-core:${kotlinArrowCore}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}