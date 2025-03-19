pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN') 
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/bengehya/myhospital-fork.git'
            }
        }

        stage('Build and Test') {
            steps {
                bat './gradlew build'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    echo "Testing if SONAR_TOKEN is loaded: ${SONAR_TOKEN}"
                }
                bat "echo Testing SONAR_TOKEN in Windows: %SONAR_TOKEN%"

                bat './gradlew sonar -Dsonar.token=%SONAR_TOKEN%'
            }
        }
    }
}
