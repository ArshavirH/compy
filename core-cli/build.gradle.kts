import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {

    implementation(project(":modules"))
    implementation(project(":shared"))

    implementation("info.picocli:picocli:4.7.7")
    kapt("info.picocli:picocli-codegen:4.7.7")

    implementation("org.mapstruct:mapstruct:1.6.3")
    kapt("org.mapstruct:mapstruct-processor:1.6.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

kapt {
    arguments {
        arg("project", "${project.group}/${project.name}")
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.compy.cli.AppKt"
    }
}

afterEvaluate {
    extensions.findByType<KotlinJvmProjectExtension>()?.jvmToolchain(21)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.languageVersion = "1.9"
}
