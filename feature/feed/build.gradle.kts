plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    androidLibrary()

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":shared"))

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
        testImplementation(CORE_TESTING)
        implementation(FRAGMENT_KTX)
    }

    Navigation.run {
        implementation(FRAGMENT_KTX)
        implementation(UI_KTX)
    }

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
    testImplementation(Moshi.KOTLIN)
    Mock.run {
        testImplementation(K)
        testImplementation(WEB_SERVER)
    }
}
