import org.gradle.api.internal.initialization.ClassLoaderIds.buildScript

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")

    // Need to add the versino here very important here
    id("dev.icerock.mobile.multiplatform-resources") version "0.23.0"
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

            // Resourcs; So resources can be found on the ios side here
            export("dev.icerock.moko:resources:0.22.3")
            export("dev.icerock.moko.graphics:0.9.0")
        }
    }
    // Be careful of the following right ehre
    val ktorVersion = "2.3.2"
    val dateTimeVersion = "0.4.0"
    sourceSets {
        val commonMain by getting {
            dependencies {

                // Step 2:
                //put your multiplatform dependencies here
            // where we put the multiplatform stuff right here

                api("dev.icerock.moko:resources:0.22.3")
                implementation(compose.ui)  //used with imageResource
                // Add the code here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // For compose testing here thank you
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.3") // 手动添加


                // Viewmodel stuff here
                implementation("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
                implementation("dev.icerock.moko:mvvm-flow:0.16.1") // api mvvm-core, CFlow for native and binding extensions
                implementation("dev.icerock.moko:mvvm-livedata:0.16.1") // api mvvm-core, LiveData and extensions
                implementation("dev.icerock.moko:mvvm-state:0.16.1") // api mvvm-livedata, ResourceState class and extensions
                implementation("dev.icerock.moko:mvvm-livedata-resources:0.16.1") // api mvvm-core, moko-resources, extensions for LiveData with moko-resources
                implementation("dev.icerock.moko:mvvm-flow-resources:0.16.1") // api mvvm-core, moko-resources, extensions for Flow with moko-resources


                // Network stuff
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

                // Need this to use the JsonFeature using this in Android main
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")




                implementation ("ch.qos.logback:logback-classic:1.2.3")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")

            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val iosMain by getting {
            // ...
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")

            }}
            val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.multi_platform_android_project"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

    // Define in the common main here
//    sourceSets["commonMain"].apply {
//        res.srcDirs("src/androidMain/res", "src/commonMain/resources")
//    }



}
dependencies {
    implementation("androidx.compose.ui:ui-android:1.6.0-alpha04")
    implementation("androidx.navigation:navigation-runtime-ktx:2.6.0")
    implementation("androidx.paging:paging-common-ktx:3.2.0")
}

// Use a mocko plugin as we said before here for sharing the ocmmon
// resource here

// Using for sharing common resoucres here

buildscript {
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.22.3")
    }
}


// Compile things from xml to the actual code here

// The plugin for this needs to be added at the top here
//multiplatformResources {
//    multiplatformResourcesPackage = "com.plcoding.kmm_sharingresources"
//    multiplatformResourcesClassName = "SharedRes"
//}





