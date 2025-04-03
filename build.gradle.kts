// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.sonarqube") version "4.3.0.3225" // Add SonarQube inside the same block
}

sonarqube {
    properties {
        property("sonar.projectKey", "terraform-pipeline") 
        property("sonar.host.url", "http://localhost:9000") 
        property("sonar.token", System.getenv("SONAR_TOKEN")) 
    }
}




