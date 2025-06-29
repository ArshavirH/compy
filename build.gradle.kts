plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "1.9.22"
}

group = "com.compy"

object Versions {
    const val JACKSON = "2.17.0"
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    version = rootProject.version

    apply {
        plugin("jacoco")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "FAILED", "SKIPPED")
        }
    }
}

dependencies {

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.JACKSON}")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:${Versions.JACKSON}")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
