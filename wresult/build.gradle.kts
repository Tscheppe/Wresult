plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "at.tscheppe.wresult"
version = "1.0"

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "at.tscheppe"
            artifactId = "wresult"
            version = "1.0.0"

            from(components["java"])
        }
    }
}