plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    //id("com.android.application") version "8.3.2"
    //apply(plugin = "org.jetbrains.kotlin.android")
    //id("org.jetbrains.kotlin.android") version "2.0.0"
}
android {
    namespace = "com.wilmer.roomcrono"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wilmer.roomcrono"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }

    }
}

dependencies {
    implementation(files("../libs/form-debug.aar"))
    api("com.github.vivchar:RendererRecyclerViewAdapter:3.0.1")
    implementation(libs.androidx.recyclerview)
    //implementation(files("../libs/form-release.aar"))
    //implementation(files("../libs/out.aar"))
    //implementation(libs.androidx.room.compiler.processing.testing)
    //compile project(":tu-proyecto")
    //KformMaster
    //implementation("com.github.thejuki:k-form-master:8.3.0")
    //implementation("com.thejuki:k-form-master:8.3.0")

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.retrofit2.converter.gson)
    implementation (libs.logging.interceptor)
    implementation (libs.google.gson)
    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.appcompat.v131)

    //View Pager2
    implementation (libs.androidx.viewpager2.v100)
    implementation (libs.accompanist.pager.v0202)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.runtime.livedata)
    //implementation(libs.androidx.room.compiler.processing.testing)
    this.kapt(libs.androidx.room.compiler)

    // Dagger Hilt
    implementation(libs.hilt.android.v2511)
    kapt(libs.hilt.android.compiler.v2511)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Swipe
    implementation(libs.swipe)

    // Other dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}