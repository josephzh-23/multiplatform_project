plugins {
    //trick: for the same plugin versions in all sub-modules

    // Here we don't want to include the versions as said this
    // because the version will be imported from the settings.gradle.kts


    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    // Add this here and we need to add the version here
    id("org.jetbrains.compose").apply(false)
}
//
//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}
