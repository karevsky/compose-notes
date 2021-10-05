plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
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
        kotlinCompilerExtensionVersion = "1.0.3"
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
    implementation(project(":domain"))

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
    debugImplementation(Compose.toolingDebug)

    implementation(Navigation.navCompose)
    implementation(Navigation.navHilt)

    implementation(Room.ktx)
    implementation(Room.runtime)
    implementation(Room.rxjava3)
    kapt(Room.compiler)

    implementation(Hilt.android)
    kapt(Hilt.compiler)

    implementation(RxJava3.rxjava)
    implementation(RxJava3.rxandroid)
}