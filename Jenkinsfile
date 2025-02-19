pipeline {
    agent {
        docker {
            image 'openjdk:22-slim'
        }
    }
    environment {
        MAVEN_HOME = '/usr/share/maven'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Apermar87/java-backWebService2'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
