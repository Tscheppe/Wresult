plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("at.tscheppe.wresult.MainKt")
}

dependencies {
    implementation(project(":wresult"))
}

kotlin {
    jvmToolchain(8)
}