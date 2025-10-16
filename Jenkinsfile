pipeline {
    agent any

    environment {
        PROJECT_DIR = "iot-devops-test"
        BACKEND_DIR = "backend"
        FRONTEND_DIR = "frontend"
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
    }

    stages {
        stage('Checkout Code') {
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
                dir("${PROJECT_DIR}/${BACKEND_DIR}") {
                    echo "🧱 Building backend..."
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend (Angular)') {
            agent {
                docker {
                    image 'node:20'
                }
            }
            steps {
                dir("${PROJECT_DIR}/${FRONTEND_DIR}") {
                    echo "🌐 Building frontend..."
                    sh '''
                    if [ -f package.json ]; then
                        npm install
                        npm run build --prod
                    else
                        echo "⚠️ No Angular project found! Skipping..."
                    fi
                    '''
                }
            }
        }

        stage('Build & Deploy with Docker') {
            agent {
                docker {
                    image 'docker:24.0.5-dind'
                    args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                dir("${PROJECT_DIR}") {
                    echo "🐳 Building and deploying services..."
                    sh 'docker-compose down || true'
                    sh 'docker-compose up --build -d'
                }
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
