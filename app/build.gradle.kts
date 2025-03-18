plugins {
    alias(libs.plugins.android.application)
    id("org.sonarqube") version "4.3.0.3225"
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

    // Ajout pour générer la couverture des tests
    buildTypes {
        debug {
            testCoverageEnabled true
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
        property("sonar.login", System.getenv("SONAR_TOKEN")) // Utilisation de la variable d'environnement
        property("sonar.sources", "src/main/java, src/main/kotlin") // Sources Java et Kotlin
        property("sonar.language", "java")
        property("sonar.sourceEncoding", "UTF-8")
        // Ajout des chemins de rapports de tests et couverture
        property("sonar.junit.reportPaths", "build/test-results/testDebugUnitTest/TEST-*.xml")
        property("sonar.jacoco.reportPaths", "build/jacoco/testDebugUnitTest.exec")
    }
}
