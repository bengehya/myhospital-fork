plugins {
    id("com.android.application") version "8.8.0" // Updated AGP version
    id("org.sonarqube") version "4.3.0.3225"
    id("jacoco")
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
        debug {
         enableUnitTestCoverage = true
        enableAndroidTestCoverage = true
         // Correct property name
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.core:core-splashscreen:1.0.1") 
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.11.0") 
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}


jacoco {
    toolVersion = "0.8.11" // Utiliser la dernière version compatible avec Java 21
}


sonarqube {
    properties {
        property("sonar.projectKey", "myhospital")
        property("sonar.projectName", "MyHospital")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.token", System.getenv("SONAR_TOKEN"))
        property("sonar.sources", "src/main/java") // Vérifie si src/main/kotlin est nécessaire
        property("sonar.language", "java")
        property("sonar.sourceEncoding", "UTF-8")
        
        // Vérifie l'existence des fichiers avant d'activer ces options
        property("sonar.junit.reportPaths", "build/test-results/testDebugUnitTest/TEST-*.xml")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/testDebugUnitTest/jacocoTestReport.xml")

    }
}

