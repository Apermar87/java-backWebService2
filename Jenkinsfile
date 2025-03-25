pipeline {
    agent {
        docker {
            image 'mi-jenkins' // Usa la imagen personalizada
            args '--network=mi_red'
        }
    }
    environment {
        MONGO_URI = 'mongodb://admin:admin@mongodb:27017/mi_base_de_datos?authSource=admin'
        SONAR_HOST_URL = 'http://sonarqube:9000'
        SONAR_LOGIN = 'admin'
        SONAR_PASSWORD = 'Aj669024316+'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Code Analysis') {
            steps {
                script {
                    timeout(time: 2, unit: 'MINUTES') {
                        waitUntil {
                            def response = sh(script: "curl -s -o /dev/null -w '%{http_code}' $SONAR_HOST_URL/api/system/status", returnStdout: true).trim()
                            return response == '200'
                        }
                    }
                }
                sh 'mvn sonar:sonar -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN -Dsonar.password=$SONAR_PASSWORD'
            }
        }
    }
    post {
        always {
            sh 'docker ps -aq --filter "ancestor=maven:3.9.8" | xargs -r docker rm -f'
        }
    }
}
