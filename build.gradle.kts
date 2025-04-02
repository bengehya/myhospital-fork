// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

plugins {
  id("org.sonarqube") version "6.0.1.5171"
}
    
sonar {
  properties {
    property("sonar.projectKey", "myhospital1")
    property("sonar.projectName", "myhospital1")
  }
}
