pipeline {
    agent any

    environment {
        REGISTRY = "localhost:5000"   // change to your DockerHub if needed
        PROJECT_NAME = "iot-devops-test"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "📦 Checking out source code"
                checkout scm
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'mvn -q clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def services = ['backend', 'frontend', 'mqtt-simulator', 'mqtt-consumer']
                    for (srv in services) {
                        sh """
                        docker build -t ${REGISTRY}/${PROJECT_NAME}-${srv}:latest ${srv}
                        """
                    }
                }
            }
        }

        stage('Push Images') {
            steps {
                script {
                    def services = ['backend', 'frontend', 'mqtt-simulator', 'mqtt-consumer']
                    for (srv in services) {
                        sh """
                        docker push ${REGISTRY}/${PROJECT_NAME}-${srv}:latest || true
                        """
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                sh """
                docker-compose down || true
                docker-compose up -d
                """
            }
        }
    }

    post {
        success {
            echo "✅ CI/CD pipeline completed successfully"
        }
        failure {
            echo "❌ Build or deployment failed"
        }
    }
}
