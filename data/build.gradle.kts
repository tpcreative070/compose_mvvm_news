import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "co.tpcreative.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }



    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties") // Use rootProject.file
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { fis ->
            localProperties.load(fis)
        }
    }
    // Define WEATHER_API_KEY in BuildConfig. Fallback to a placeholder if not found.
    val apiKey = localProperties.getProperty(
        "NEWS_API_KEY",
        "b35a937aac0b49a689fc5daaa5fc6a87"
    )

    buildTypes.all {
        buildConfigField("String", "NEWS_API_KEY", "\"${apiKey}\"")
        buildConfigField("String", "BASE_URL", "\"https://newsapi.org/\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {

        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true

    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)

    // Paging
    implementation(libs.androidx.paging.runtime)

    // Retrofit & OkHttp

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)


    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Timber
    implementation(libs.timber)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}