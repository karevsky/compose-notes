
object Versions {
    const val minSdk = 24
    const val targetSdk = 31
    const val compileSdk = 31
    const val versionCode = 1
    const val versionName = "1.0"

    const val firebaseBom = "28.4.2"
    const val landscapist = "1.4.1"
    const val firebaseServices = "4.3.10"
    const val kotlin = "1.5.30"
    const val coreKtx = "1.5.0"
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val compose = "1.0.3"
    const val lifecycle = "2.3.1"
    const val activity = "1.3.1"
    const val navigation = "2.4.0-alpha01"
    const val rxjava3 = "3.1.1"
    const val serialization = "1.3.0"
    const val rxandroid = "3.0.0"
    const val hilt = "2.38.1"
    const val room = "2.3.0"
    const val hiltNavigation = "1.0.0-alpha03"
    const val swipeRefreshLayout = "0.21.2-beta"
}

object Android {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val landscapist = "com.github.skydoves:landscapist-glide:${Versions.landscapist}"
    const val swipeRefreshLayout = "com.google.accompanist:accompanist-swiperefresh:${Versions.swipeRefreshLayout}"
}

object Kotlin {
    const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
}

object Navigation {
    const val navCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val navHilt = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val activity = "androidx.activity:activity-compose:${Versions.activity}"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
}

object Hilt {
    private const val hiltGroup = "com.google.dagger"

    const val android = "$hiltGroup:hilt-android:${Versions.hilt}"
    const val compiler = "$hiltGroup:hilt-android-compiler:${Versions.hilt}"
}

object RxJava3 {
    private const val rxjavaGroup = "io.reactivex.rxjava3"

    const val rxjava = "$rxjavaGroup:rxjava:${Versions.rxjava3}"
    const val rxandroid = "$rxjavaGroup:rxandroid:${Versions.rxandroid}"
}

object Room {
    private const val roomGroup = "androidx.room"

    const val runtime = "$roomGroup:room-runtime:${Versions.room}"
    const val ktx = "$roomGroup:room-ktx:${Versions.room}"
    const val rxjava3 = "$roomGroup:room-rxjava3:${Versions.room}"
    const val compiler = "$roomGroup:room-compiler:${Versions.room}"
}

object Compose {
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val rxjava = "androidx.compose.runtime:runtime-rxjava3:${Versions.compose}"
    const val toolingDebug = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val compiler = "androidx.compose.compiler:compiler:${Versions.compose}"
    const val icons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
}

object Firebase {
    const val services = "com.google.gms:google-services:${Versions.firebaseServices}"
    const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val auth = "com.google.firebase:firebase-auth-ktx"
}