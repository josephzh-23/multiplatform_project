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
                jvmTarget = "17"
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
    // Be careful of the following right ehre
    val ktorVersion = "2.3.2"
    val dateTimeVersion = "0.4.0"

    var koinVersion = "3.4.3"
    sourceSets {
        val commonMain by getting {
            dependencies {

                // Step 2:
                //put your multiplatform dependencies here
            // where we put the multiplatform stuff right here

                api("dev.icerock.moko:resources:0.22.3")
                api("dev.icerock.moko:resources-compose:0.22.3")
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
//                implementation("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
//                implementation("dev.icerock.moko:mvvm-flow:0.16.1") // api mvvm-core, CFlow for native and binding extensions
//                implementation("dev.icerock.moko:mvvm-livedata:0.16.1") // api mvvm-core, LiveData and extensions
//                implementation("dev.icerock.moko:mvvm-state:0.16.1") // api mvvm-livedata, ResourceState class and extensions
//                implementation("dev.icerock.moko:mvvm-livedata-resources:0.16.1") // api mvvm-core, moko-resources, extensions for LiveData with moko-resources
//                implementation("dev.icerock.moko:mvvm-flow-resources:0.16.1") // api mvvm-core, moko-resources, extensions for Flow with moko-resources


                // Network stuff
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

                // Need this to use the JsonFeature using this in Android main
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")




                implementation ("ch.qos.logback:logback-classic:1.2.3")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")

                // How to deal with the navigator in compose
                api("moe.tlaster:precompose:1.5.0")
                api("moe.tlaster:precompose-viewmodel:1.5.0")



                // Imageloading
                api("io.github.qdsfdhvh:image-loader:1.5.1")

                // Using dependency injection
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.insert-koin:koin-test:$koinVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                // Using koin here with the network stuff here very important
                implementation("io.insert-koin:koin-android:$koinVersion")
                implementation("io.insert-koin:koin-androidx-compose:$koinVersion")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            // ...
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
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


    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

//    defaultConfig {
//        minSdk = (findProperty("android.minSdk") as String).toInt()
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

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





