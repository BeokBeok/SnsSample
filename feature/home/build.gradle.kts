plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    androidLibrary()
}

dependencies {
    implementation(Google.MATERIAL)

    Network.run {
        implementation(RETROFIT)
        implementation(CONVERTER_MOSHI)
    }

    Coroutines.run {
        implementation(CORE)
        testImplementation(TEST)
    }

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
    testImplementation(Moshi.KOTLIN)
    Mock.run {
        testImplementation(K)
        testImplementation(WEB_SERVER)
    }
}
