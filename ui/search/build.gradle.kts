plugins {
    id("com.android.library")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    defaultConfig{
        minSdkVersion(24)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures.viewBinding = true
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.fragment:fragment-ktx:1.3.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.recyclerview:recyclerview:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("io.coil-kt:coil:1.2.0")

    //Koin
    implementation("org.koin:koin-core:2.2.2")
    implementation("org.koin:koin-core-ext:2.2.2")
    implementation("org.koin:koin-android:2.2.2")
    implementation("org.koin:koin-androidx-ext:2.2.2")
    implementation("org.koin:koin-androidx-viewmodel:2.2.2")
    implementation("org.koin:koin-androidx-scope:2.2.2")
    implementation("org.koin:koin-androidx-fragment:2.2.2")

    implementation(project(":ui:common"))
    implementation(project(":presentation:search"))
}