plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    androidApplication()

    defaultConfig {
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:detail"))

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

    Navigation.run {
        implementation(FRAGMENT_KTX)
        implementation(UI_KTX)
    }

    testImplementation(JUnit.CORE)
}
