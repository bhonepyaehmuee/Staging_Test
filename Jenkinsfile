pipeline {
    agent any

    tools {
        maven "maven3.9"
    }

    environment {
        DOCKER_REGISTRY = "bownoed/stagingtest"
        DOCKER_HOST_PORT = "9096"
    }

    stages {
        // Stage 1: Checkout code from Git
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/bhonepyaehmuee/Staging_Test.git'
            }
        }

        // Stage 2: Build Jar
        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        // Stage 3: Build the Docker Image
        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_REGISTRY}:v1.0 ."
                }
            }
        }

        // Stage 4: Push Docker Image to Docker Hub
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials',
                                                  usernameVariable: 'DOCKER_USER',
                                                  passwordVariable: 'DOCKER_PASS')]) {
                    script {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        sh "docker push ${DOCKER_REGISTRY}:v1.0"
                    }
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig']) {
                    sh 'kubectl apply -f deployment.yml'
                }
            }
        }
    }
}