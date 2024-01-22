plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "at.tscheppe.wresult"
version = "1.0.1"

dependencies {
    testImplementation(kotlin("test"))
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