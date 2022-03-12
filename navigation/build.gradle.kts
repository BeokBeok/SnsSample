plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    androidLibrary()
}

dependencies {
    implementation(project(":shared"))

    Google.run {
        implementation(MATERIAL)
        testImplementation(TRUTH)
    }

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    testImplementation(JUnit.CORE)
    testImplementation(AndroidX.CORE_TESTING)
}
