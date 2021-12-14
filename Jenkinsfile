pipeline {
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk11'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn install -DskipTests'
            }
        }
        stage ('Test') {
             steps {
                sh 'mvn test'
            }
        }

        stage ('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.8.0.2131:sonar'
                }
            }
        }

       stage ('Docker build') {
           steps {
              sh 'docker build -f eurekaServer/Dockerfile .'
              sh 'docker build -f zuulServer/Dockerfile .'
              sh 'docker build -f usersService/Dockerfile .'
              sh 'docker build -f tasksService/Dockerfile .'
           }
       }
       stage ('Docker run') {
           steps {
               sh 'docker-compose up -d redis'
               sh 'docker-compose up -d redis-commander'
               sh 'docker-compose up -d eureka-server'
               sh 'docker-compose up -d zuul-server'
               sh 'docker-compose up -d users-service'
               sh 'docker-compose up -d tasks-service'
           }
       }
    }
}
