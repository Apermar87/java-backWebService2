pipeline {
    agent {
        docker {
            image 'openjdk:22-slim'
            args '--user root'
        }
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Apermar87/java-backWebService2'
            }
        }
        stage('Build') {
            steps {
                sh 'java -version' // Para verificar la versión de Java
                sh 'mvn clean package'
            }
        }
    }
}
