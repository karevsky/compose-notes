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

    //project
    implementation(project(":core"))
    implementation(project(":database"))
    implementation(project(":api"))
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.activity)
    implementation(Android.material)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.toolingPreview)
    implementation(Compose.ui)
    implementation(Compose.livedata)
    implementation(Compose.rxjava)
    implementation(Compose.icons)
    implementation(Navigation.navCompose)
    implementation(Navigation.navHilt)
    implementation(Room.ktx)
    implementation(Room.runtime)
    implementation(Room.rxjava3)
    implementation(Hilt.android)
    implementation(RxJava3.rxjava)
    implementation(RxJava3.rxandroid)
    implementation(platform(Firebase.bom))
    implementation(Firebase.auth)

    debugImplementation(Compose.toolingDebug)

    kapt(Room.compiler)
    kapt(Hilt.compiler)
}
