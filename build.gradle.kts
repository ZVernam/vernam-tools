/*
 * Gradle Kotlin root project with modules
 *
 * https://docs.gradle.org/current/dsl/
 */
allprojects {
    group = "com.github.zeckson"
    version = "0.3.0"

    repositories {
        mavenCentral()
    }
}

buildscript {
    @Suppress("PropertyName")
    val kotlin_version by extra("1.3.72")

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

