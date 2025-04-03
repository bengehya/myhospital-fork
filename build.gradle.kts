plugins {
    alias(libs.plugins.android.application) apply false
    id("org.sonarqube") version "4.3.0.3225" // Add SonarQube inside the same block
}

// Configure SonarQube properties
sonarqube {
    properties {
        property("sonar.projectKey", "your_project_key")
        property("sonar.organization", "your_organization") // Only needed for SonarCloud
        property("sonar.host.url", "http://your-sonarqube-server.com") // Update if self-hosted
        property("sonar.login", System.getenv("SONAR_TOKEN")) // Using environment variable
    }
}
