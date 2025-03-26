pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = "github-ssh-key"
        DOCKER_CREDENTIALS = credentials('docker-hub-credentials') // ID de las credenciales en Jenkins
        IMAGE_NAME = "apermar87/java-backwebservice2"
        IMAGE_TAG = "latest"
    }

    stages {
        stage('Clonar repositorio') {
            steps {
                git credentialsId: "${GIT_CREDENTIALS}", branch: 'master', url: 'git@github.com:Apermar87/java-backWebService2.git'
            }
        }

        stage('Compilar con Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Ejecutar pruebas unitarias') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Análisis de código con SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn sonar:sonar \
                            -Dsonar.projectKey=java-backWebService2 \
                            -Dsonar.projectName=java-backWebService2 \
                            -Dsonar.host.url=http://sonarqube:9000
                    '''
                }
            }
        }

        stage('Construir imagen Docker') {
            steps {
                script {
                    echo "🚀 Construyendo imagen Docker..."
                    sh "docker build -f deployment/Dockerfile -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage('Publicar imagen en Docker Hub') {
            steps {
                script {
                    echo "🔑 Autenticando en Docker Hub..."
                    sh "echo '${DOCKER_CREDENTIALS_PSW}' | docker login -u '${DOCKER_CREDENTIALS_USR}' --password-stdin"

                    echo "📤 Subiendo imagen a Docker Hub..."
                    sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }
    }

    post {
        always {
            echo "🧹 Limpiando sesión de Docker..."
            sh "docker logout || true"
        }
        success {
            echo "✅ Pipeline ejecutado con éxito."
        }
        failure {
            echo "❌ Error en el pipeline."
        }
    }
} 
