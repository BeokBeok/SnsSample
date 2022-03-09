plugins {
    id("java-library")
    id("kotlin")
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
}
