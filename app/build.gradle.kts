plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nkdroid.noteunittesting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nkdroid.noteunittesting"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    val room_version = "2.6.1"
    val hilt_version = "2.48"
    val truth_version = "1.4.2"
    val coroutines_version = "1.3.9"
    val arch_version = "2.2.0"
    val lifecycle_version = "2.7.0"
    val activity_version = "1.9.0"
    val fragment_version = "1.6.2"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")

    //activity
    implementation("androidx.activity:activity-ktx:$activity_version")

    //Fragment
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    //room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //Coroutine
    //Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-work:1.0.0")

    //workManager
    val work_version = "2.9.1"
    implementation("androidx.work:work-runtime-ktx:$work_version")

    testImplementation(libs.junit)
    testImplementation("com.google.truth:truth:$truth_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    testImplementation("androidx.room:room-testing:$room_version")
    testImplementation("androidx.arch.core:core-testing:$arch_version")
    testImplementation ("androidx.lifecycle:lifecycle-runtime-testing:$lifecycle_version")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation("androidx.arch.core:core-testing:$arch_version")
    androidTestImplementation("com.google.truth:truth:$truth_version")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    androidTestImplementation("androidx.room:room-testing:$room_version")
    androidTestImplementation("androidx.arch.core:core-testing:$arch_version")
    androidTestImplementation ("androidx.lifecycle:lifecycle-runtime-testing:$lifecycle_version")

    debugImplementation("androidx.fragment:fragment-testing:$fragment_version")
}