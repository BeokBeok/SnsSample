plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.beok.ohousesample"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
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
}

dependencies {
    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APP_COMPAT)
        implementation(CONSTRAINT_LAYOUT)
    }

    implementation(Google.MATERIAL)

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    testImplementation(JUnit.CORE)
}
