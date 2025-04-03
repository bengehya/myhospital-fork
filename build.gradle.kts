// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.sonarqube") version "4.3.0.3225" // Apply SonarQube plugin
}

sonarqube {
    properties {
        property("sonar.projectKey", "terraform-pipeline") // Update with your actual project key
        property("sonar.host.url", "http://localhost:9000") // Update if your SonarQube runs elsewhere
        property("sonar.token", System.getProperty("sonar.token") ?: System.getenv("SONAR_TOKEN")) // Ensure token is set
    }
}





