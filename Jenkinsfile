pipeline {
    agent any

    environment {
        BACKEND_IMAGE = "nidle-backend:latest"
        FRONTEND_IMAGE = "nidle-frontend:latest"
        COMPOSE_PROJECT_DIR = "${WORKSPACE}" // docker-compose.yml location
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "📦 Checking out code..."
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
                echo "📦 Deploying services via Docker Compose..."
                sh "docker-compose -f ${COMPOSE_PROJECT_DIR}/docker-compose.yml up -d --build"
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
