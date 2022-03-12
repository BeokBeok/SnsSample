plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    androidLibrary()

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":navigation"))

    implementation(Google.MATERIAL)

    Network.run {
        implementation(RETROFIT)
        implementation(CONVERTER_MOSHI)
    }

    Coroutines.run {
        implementation(CORE)
        testImplementation(TEST)
    }

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    AndroidX.run {
        implementation(FRAGMENT_KTX)
        implementation(SWIPE_REFRESH_LAYOUT)
        testImplementation(CORE_TESTING)
    }

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
    testImplementation(Moshi.KOTLIN)
    Mock.run {
        testImplementation(K)
        testImplementation(WEB_SERVER)
    }
}
