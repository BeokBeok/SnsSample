plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.3")
    implementation(kotlin("gradle-plugin", "1.5.31"))
}
