pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()

        // Step 5:

        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")


        // Needed for kotlin x seriliazer
        maven(url = "https://kotlin.bintray.com/kotlinx/")
    }


    plugins{

        // step 4:
        val kotlinVersion = extra["kotlin.version"] as String
        val apgVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)

        id("com.android.application").version(apgVersion)
        id("com.android.library").version(apgVersion)

        id("org.jetbrains.compose").version(composeVersion)


    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

    }
}

rootProject.name = "multi_platform_android_project"
include(":androidApp")
include(":shared")