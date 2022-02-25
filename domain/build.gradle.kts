plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    /**
     * CORE
     */
    implementation(AndroidX.coreKtx)

    /**
     * DATABASE
     */
    implementation(Room.ktx)
    implementation(Room.runtime)
    implementation(Room.rxjava3)
    implementation(Kotlin.serialization)
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
}