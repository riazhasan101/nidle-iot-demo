pipeline {
    agent none

    stages {
        stage('Checkout Code') {
            agent any
            steps {
                echo "📦 Checking out latest code..."
                checkout scm
            }
        }

        stage('Build Backend (Java + Maven)') {
            agent {
                docker {
                    image 'maven:3.9.5-eclipse-temurin-17'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                dir('backend') {
                    echo "🧱 Building Java backend..."
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend (Angular)') {
            agent {
                docker {
                    image 'node:20'
                    args '-v $HOME/.npm:/root/.npm'
                }
            }
            steps {
                dir('frontend') {
                    echo "🌐 Building Angular frontend..."
                    sh '''
                    if [ -f package.json ]; then
                        npm install
                        npm run build --prod || npm run build
                    else
                        echo "⚠️ No frontend package.json found, skipping build..."
                    fi
                    '''
                }
            }
        }

        stage('Build & Deploy Docker') {
            agent any
            steps {
                echo "🐳 Building Docker images and deploying..."
                sh '''
                docker-compose build --no-cache
                docker-compose down || true
                docker-compose up -d
                docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
                '''
            }
        }
    }

    post {
        success {
            echo "✅ CI/CD pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed! Check logs for details."
        }
    }
}
