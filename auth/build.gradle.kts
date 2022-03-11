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

    implementation(Google.MATERIAL)

    Navigation.run {
        implementation(FRAGMENT_KTX)
        implementation(UI_KTX)
    }

    implementation(DataStore.PREFERENCES)

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
    testImplementation(Moshi.KOTLIN)
    Mock.run {
        testImplementation(K)
        testImplementation(WEB_SERVER)
    }
}
