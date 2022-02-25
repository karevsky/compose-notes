plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.karevksy.notes"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    /**
     * MODULES
     */
    implementation(project(":core"))
    implementation(project(":domain"))

    /**
     * CORE
     */
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.activity)

    /**
     * UI
     */
    implementation(Android.material)
    implementation(Android.landscapist)
    implementation(Android.swipeRefreshLayout)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.toolingPreview)
    implementation(Compose.ui)
    implementation(Compose.livedata)
    implementation(Compose.rxjava)
    implementation(Compose.icons)

    /**
     * NAVIGATION
     */
    implementation(Navigation.navCompose)
    implementation(Navigation.navHilt)

    /**
     * DATABASE
     */
    implementation(Room.ktx)
    implementation(Room.runtime)
    implementation(Room.rxjava3)
    kapt(Room.compiler)

    /**
     * DI
     */
    implementation(Hilt.android)
    implementation(RxJava3.rxjava)
    implementation(RxJava3.rxandroid)
    kapt(Hilt.compiler)

    /**
     * FIREBASE
     */
    implementation(platform(Firebase.bom))
    implementation(Firebase.auth)

    debugImplementation(Compose.toolingDebug)
}
