plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

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
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.toolingPreview)
    implementation(Compose.ui)
    implementation(Compose.livedata)
    implementation(Compose.rxjava)
    implementation(Compose.icons)
    debugImplementation(Compose.toolingDebug)

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
    implementation(RxJava3.rxjava)
    implementation(RxJava3.rxandroid)
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * FIREBASE
     */
    implementation(platform(Firebase.bom))
    implementation(Firebase.auth)
}