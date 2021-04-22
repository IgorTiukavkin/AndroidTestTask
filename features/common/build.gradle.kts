plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

android {
    compileSdkVersion(30)
    defaultConfig{
        targetSdkVersion(30)
        minSdkVersion(24)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation("androidx.core:core-ktx:1.3.2")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    // Ktor
    implementation("io.ktor:ktor-client-core:1.5.3")
    implementation("io.ktor:ktor-client-android:1.5.3")
    implementation("io.ktor:ktor-client-serialization:1.5.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    // Database
    implementation("com.squareup.sqldelight:android-driver:1.4.4")
}