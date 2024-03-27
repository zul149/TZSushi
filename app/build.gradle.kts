plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")

}

android {
    namespace = "com.example.tzsushi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tzsushi"
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
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }
    kotlinOptions {
        jvmTarget = "20"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0") //retrofit requisites retro+gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.google.android.gms:play-services-auth:21.0.0")
    implementation ("com.google.android.gms:play-services-drive:17.0.0")
    implementation ("com.google.android.gms:play-services-location:21.2.0")
    implementation ("com.squareup.okio:okio:3.2.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

}