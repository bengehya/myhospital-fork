pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN') // Récupère le token de Jenkins Credentials
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/bengehya/myhospital-fork.git'
            }
        }

        stage('Build and Test') {
            steps {
                bat './gradlew build'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarScanner') {
                    bat "./gradlew sonarqube -Dsonar.login=${SONAR_TOKEN}"
                }
            }
        }
    }
}
