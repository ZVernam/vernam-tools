import org.jetbrains.kotlin.konan.target.buildDistribution

plugins {
    id("org.jetbrains.kotlin.js")
}

dependencies {
    implementation(kotlin("stdlib-js"))

//    implementation(project(":kotlin-library"))

    testImplementation(kotlin("test-js"))
    testCompile("junit", "junit", "4.12")
}

kotlin.target {
    browser {
        webpackTask {
        }

        @Suppress("EXPERIMENTAL_API_USAGE")
        dceTask {
            enabled = false
        }

        @Suppress("EXPERIMENTAL_API_USAGE")
        distribution {

            directory = File("$projectDir/output/")
        }
    }
}