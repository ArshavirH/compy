import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {

    implementation(project(":shared"))

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

afterEvaluate {
    extensions.findByType<KotlinJvmProjectExtension>()?.jvmToolchain(21)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.languageVersion = "1.9"
}
