/*
 * Kotlin Library with core-utils and tools
 *
 * https://docs.gradle.org/current/dsl/
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    // Add maven-publish plugin
    `maven-publish` apply true
}


repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

publishing {
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ZVernam/vernam-tools")
            credentials {
                username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")
            }
        }
    }
}


dependencies {
    @Suppress("LocalVariableName") val kotlin_version:String by rootProject.extra
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlin_version"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

tasks {
    jar {
        manifest {
            attributes(
                    mapOf("Implementation-Title" to project.name,
                            "Implementation-Version" to project.version)
            )
        }
    }

    java {
        withJavadocJar()
    }
}
