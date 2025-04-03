pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN') // Ensure this is set correctly in Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                git(
                    branch: 'main',
                    credentialsId: 'c8a69c20-2f7d-4313-a435-1674823f3503', 
                    url: 'https://github.com/bengehya/myhospital-fork.git'
                )
            }
        }

        stage('Build and Test') {
            steps {
                bat './gradlew build'
            }
        }

        stage('Check Sonar Token') {
            steps {
                script {
                    if (SONAR_TOKEN) {
                        echo "✅ SONAR_TOKEN is set. First 5 chars: ${SONAR_TOKEN.take(5)}******"
                    } else {
                        error "❌ SONAR_TOKEN is NOT set! Check Jenkins credentials."
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarScanner') {
                    script {
                        echo "🚀 Running SonarQube Analysis!"
                    }
                    bat './gradlew sonar -Dsonar.login=${SONAR_TOKEN}' // Updated to use sonar.login instead of sonar.token
                }
            }
        }
    }
}
