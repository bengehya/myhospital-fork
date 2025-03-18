plugins {
    id("com.android.application") version "8.1.0" // Updated AGP version
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
            isTestCoverageEnabled = true // Correct property name
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

jacoco {
    toolVersion = "0.8.7" // Specify Jacoco version
}

sonarqube {
    properties {
        property("sonar.projectKey", "myhospital")
        property("sonar.projectName", "MyHospital")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
        property("sonar.sources", "src/main/java, src/main/kotlin")
        property("sonar.language", "java")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.junit.reportPaths", "build/test-results/testDebugUnitTest/TEST-*.xml")
        property("sonar.jacoco.reportPaths", "build/jacoco/testDebugUnitTest.exec")
    }
}
