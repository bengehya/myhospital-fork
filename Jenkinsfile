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

        stage('Check Sonar Token') {
            steps {
                script {
                    if (SONAR_TOKEN) {
                        echo "✅ SONAR_TOKEN is set. First 5 chars: ${SONAR_TOKEN.take(5)}******"
                    } else {
                        error "❌ SONAR_TOKEN is NOT set! Check Jenkins credentials."
                    }
                }
                bat "echo SONAR_TOKEN Windows: ***** (hidden for security)"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarScanner') { // Assure-toi que 'SonarScanner' est bien défini dans Jenkins
                    script {
                        echo "🚀 Running SonarQube Analysis"
                    }
                   bat './gradlew sonar -Dsonar.token=squ_d0728f3999a68e05f52b8b8bd30e8700348f7f7f'

                }
            }
        }
    }
}
