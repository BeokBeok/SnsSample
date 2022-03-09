// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ProjectConfig.GRADLE)
        classpath(ProjectConfig.KOTLIN_GRADLE_PLUGIN)
        classpath(ProjectConfig.HILT_ANDROID_GRADLE_PLUGIN)
        classpath(ProjectConfig.NAVIGATION_SAFE_ARGS_GRADLE_PLUGIN)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
