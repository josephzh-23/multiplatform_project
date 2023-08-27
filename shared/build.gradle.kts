import org.gradle.api.internal.initialization.ClassLoaderIds.buildScript
import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"

            // so the code can be found in the xcode code base here
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

                // Step 2:
                //put your multiplatform dependencies here
            // where we put the multiplatform stuff right here

                implementation(compose.ui)  //used with imageResource
                // Add the code here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.multi_platform_android_project"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }

    // Define in the common main here
    sourceSets["commonMain"].apply {
        res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    }



}
dependencies {
    implementation("androidx.compose.ui:ui-android:1.6.0-alpha04")
    implementation("androidx.navigation:navigation-runtime-ktx:2.6.0")
}

// Use a mocko plugin as we said before here for sharing the ocmmon
// resource here

org.jetbrains.kotlin.fir.declarations.builder.buildScript{
    dependencies{
        classpath("dev.icerock.moko:resources-generator:0.22.3")

        // Using this here
    }

}









