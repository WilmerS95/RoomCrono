// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id ("com.google.dagger.hilt.android") version "2.51.1" apply false
    //id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    //id("org.jetbrains.kotlin.android") version "2.0.20-Beta2"

}

buildscript {
    repositories {
        google()
        mavenCentral()
        //maven { url = uri("https://jitpack.io") }
        //maven { url = uri("https://plugins.gradle.org/m2/") }

    }
    dependencies {
        classpath(libs.gradle.v802)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.kotlin.ksp)
        classpath(libs.kotlin.gradle.plugin.v180)
        //classpath (libs.gradle.v413)
        //compile project(":form")
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
    }
}

//apply(plugin = "org.jetbrains.kotlin.android")