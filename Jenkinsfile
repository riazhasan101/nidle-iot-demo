pipeline {
    agent any

    environment {
        PROJECT_DIR = "iot-devops-test"
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "📦 Checking out latest code..."
                checkout scm
            }
        }

        stage('Build & Deploy with Docker') {
            steps {
                dir("${PROJECT_DIR}") {
                    echo "🐳 Building and deploying all services with Docker Compose..."
                    // Stop previous containers if running
                    sh 'docker-compose down || true'
                    // Build images and start containers in detached mode
                    sh 'docker-compose up --build -d'
                }
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
