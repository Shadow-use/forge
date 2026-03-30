plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.shadow.forge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shadow.forge"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Створюємо поле в коді, яке заповниться під час збірки
        buildConfigField("String", "GH_TOKEN", "\"${System.getenv("GH_TOKEN") ?: ""}\"")
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}
