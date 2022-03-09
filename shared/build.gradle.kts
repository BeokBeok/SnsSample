plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    androidLibrary()

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Google.MATERIAL)
}
