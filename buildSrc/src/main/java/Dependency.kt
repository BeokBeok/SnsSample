object Version {
    const val KOTLIN = "1.5.31"
}

object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:1.7.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.1"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.4.1"
    const val CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:1.5.0"
    const val TRUTH = "com.google.truth:truth:1.1.3"
}

object JUnit {
    const val CORE = "junit:junit:4.13.2"
}

object Network {
    private const val RETROFIT_VERSION = "2.9.0"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
}

object Moshi {
    private const val VERSION = "1.12.0"

    const val KOTLIN = "com.squareup.moshi:moshi-kotlin:$VERSION"
    const val ADAPTERS = "com.squareup.moshi:moshi-adapters:$VERSION"
    const val KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$VERSION"
}

object Mock {
    const val K = "io.mockk:mockk:1.12.0"
    const val WEB_SERVER = "com.squareup.okhttp3:mockwebserver:4.9.3"
}

object Coroutines {
    private const val VERSION = "1.6.0"

    const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
    const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$VERSION"
}

object Hilt {
    const val VERSION = "2.40.1"

    const val ANDROID = "com.google.dagger:hilt-android:$VERSION"
    const val COMPILER = "com.google.dagger:hilt-compiler:$VERSION"
}

object Glide {
    private const val GLIDE_VER = "4.13.1"

    const val CORE = "com.github.bumptech.glide:glide:$GLIDE_VER"
    const val COMPILER = "com.github.bumptech.glide:compiler:$GLIDE_VER"
}

object DataStore {
    const val PREFERENCES = "androidx.datastore:datastore-preferences:1.0.0"
}
