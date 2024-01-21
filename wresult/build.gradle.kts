plugins {
    kotlin("jvm")
}

group = "at.tscheppe"
version = "1.0"

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(8)
}