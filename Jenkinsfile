pipeline {
    agent any

    environment {
        DOCKER_GROUP_ID = '976'  // Adjust to match host docker group
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "📦 Checking out code..."
                git branch: 'main', url: 'https://github.com/riazhasan101/nidle-iot-demo'
            }
        }

        stage('Build Backend Docker') {
            steps {
                echo "🚀 Building Backend Docker Image..."
                sh "docker build -t nidle-backend:latest ./backend"
            }
        }

        stage('Build Frontend Docker') {
            steps {
                echo "🚀 Building Frontend Docker Image..."
                sh "docker build -t nidle-frontend:latest ./frontend"
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                echo "📦 Deploying with Docker Compose..."
                sh """
                docker-compose -f docker-compose.yml down
                docker-compose -f docker-compose.yml up -d --build
                """
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed! Check logs."
        }
    }
}
