plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    androidLibrary()
}

dependencies {
    Network.run {
        implementation(RETROFIT)
        implementation(CONVERTER_MOSHI)
    }

    Moshi.run {
        implementation(KOTLIN)
        implementation(ADAPTERS)
        implementation(KOTLIN_CODEGEN)
    }

    implementation(Coroutines.CORE)

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
    Mock.run {
        testImplementation(K)
        testImplementation(WEB_SERVER)
    }
}
