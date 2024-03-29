plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdkVersion(32)

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    implementation(libs.bundles.android)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
