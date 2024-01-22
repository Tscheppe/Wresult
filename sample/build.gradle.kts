plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("at.tscheppe.wresult.MainKt")
}

dependencies {
    implementation(project(":wresult"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0")
}