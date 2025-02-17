pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

    environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_TOKEN = 'squ_809d3303c01c31e0d4cf5f249d09a30b61fea9a3'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Apermar87/java-backWebService2.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=webservice -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_TOKEN}'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t mi-webservice .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker run -d -p 8080:8080 mi-webservice'
            }
        }
    }
}
