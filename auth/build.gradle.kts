plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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
        implementation(CORE)
        kapt(COMPILER)
    }

    testImplementation(JUnit.CORE)
    testImplementation(Google.TRUTH)
    Mock.run {
        testImplementation(K)
        testImplementation(WEB_SERVER)
    }
}
