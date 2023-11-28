// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.gradle) apply false //Android Gradle Plugin
    alias(libs.plugins.kotlin) apply false //Kotlin
    alias(libs.plugins.hilt.android) apply false //Hilt
    alias(libs.plugins.kotlin.ksp) apply false //Kotlin ksp
}