rootProject.name = "compy"

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath("io.alcide:gradle-semantic-build-versioning:4.2.2")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

apply {
    plugin("io.alcide.gradle-semantic-build-versioning")
}

include("core-cli", "kernel-api", "shared", "modules")
