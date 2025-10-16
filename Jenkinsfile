pipeline {
    agent any

    environment {
        BACKEND_IMAGE = "nidle-backend:latest"
        FRONTEND_IMAGE = "nidle-frontend:latest"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "📦 Checking out latest code..."
                checkout scm
            }
        }

        stage('Build Backend Docker') {
            steps {
                echo "🚀 Building Backend Docker Image..."
                sh "docker build -t ${BACKEND_IMAGE} ./backend"
            }
        }

        stage('Build Frontend Docker') {
            steps {
                echo "🚀 Building Frontend Docker Image..."
                sh "docker build -t ${FRONTEND_IMAGE} ./frontend"
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                echo "📦 Deploying Docker Compose..."
                sh "docker-compose up -d --build"
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Check logs!"
        }
    }
}
