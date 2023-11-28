plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.yassirmovieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.yassirmovieapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false

            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "API_KEY",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwY2ViNWJkNTFiZDgwN2YyYzJiZWJhYzRiYTE1YjU2MyIsInN1YiI6IjY1NjM0NGY0NzA2ZTU2MDBhY2YwYWQ1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.y4m26jpBLOcw6ZqDe6wD36nND_s3sWmktMwaUoVWQH0\""
            )

            applicationIdSuffix = ".debug"
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "API_KEY",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwY2ViNWJkNTFiZDgwN2YyYzJiZWJhYzRiYTE1YjU2MyIsInN1YiI6IjY1NjM0NGY0NzA2ZTU2MDBhY2YwYWQ1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.y4m26jpBLOcw6ZqDe6wD36nND_s3sWmktMwaUoVWQH0\""
            )

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.manifest)

    // Coil Compose
    implementation(libs.coil.compose)
    implementation(libs.swiprefresh.compose)

    // lifecycle
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    //Retrofit - Api calling
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.logging.interceptor)

    //Hilt - Dependency injection
    implementation(libs.hilt.core)
    kapt(libs.hilt.kapt.compiler)
    implementation(libs.hilt.compose)

    // Paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    // Room
    implementation(libs.room.core)
    ksp(libs.room.kapt.compiler)
    implementation(libs.room.paging)

    //Timber - Logging
    implementation(libs.timber)

    testImplementation(libs.junit.core)

    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
}