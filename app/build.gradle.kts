plugins {
    alias(libs.plugins.android.application)
    id("org.sonarqube") version "4.3.0.3225"
    id("jacoco") // Add the Jacoco plugin
}

android {
    namespace = "com.example.myhospital"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myhospital"
        minSdk = 30
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Enable test coverage in the debug build type
    buildTypes {
        getByName("debug") {
            testCoverageEnabled = true // This enables test coverage
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.splashscreen)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Splash Screen API
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.google.android.material:material:1.4.0")
}

sonarqube {
    properties {
        property("sonar.projectKey", "myhospital")
        property("sonar.projectName", "MyHospital")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.login", System.getenv("SONAR_TOKEN")) // Use the environment variable for the token
        property("sonar.sources", "src/main/java, src/main/kotlin") // Java and Kotlin source directories
        property("sonar.language", "java")
        property("sonar.sourceEncoding", "UTF-8")
        // Add paths for test reports and coverage reports
        property("sonar.junit.reportPaths", "build/test-results/testDebugUnitTest/TEST-*.xml")
        property("sonar.jacoco.reportPaths", "build/jacoco/testDebugUnitTest.exec")
    }
}
