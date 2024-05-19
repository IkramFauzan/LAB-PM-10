plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.tugasprak7"
    compileSdk = 34
    viewBinding {
        enable = true
    }
    buildFeatures {
        dataBinding = true
    }


    defaultConfig {
        applicationId = "com.example.tugasprak7"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-reactivestreams:2.4.0")
    implementation ("androidx.core:core:1.8.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}