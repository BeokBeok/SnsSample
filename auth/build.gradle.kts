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

    implementation(AndroidX.FRAGMENT_KTX)

    implementation(Google.MATERIAL)

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
    Mock.run {
        testImplementation(K)
        testImplementation(WEB_SERVER)
    }
}
