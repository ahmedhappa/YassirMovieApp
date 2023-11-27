// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false //Kotlin
    id("com.google.dagger.hilt.android") version libs.versions.hilt.get() apply false //Hilt
}