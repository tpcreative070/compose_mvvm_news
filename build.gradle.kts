// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.ksp) apply false


}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)

    }
}


tasks.register("clean").configure {
    delete("build")
}