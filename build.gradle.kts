// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.9")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
