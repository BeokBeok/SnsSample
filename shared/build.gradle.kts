plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    androidLibrary()

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Google.MATERIAL)
    implementation(JUnit.CORE)
    implementation(Coroutines.TEST)

    Glide.run {
        implementation(CORE)
        kapt(COMPILER)
    }

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
}
