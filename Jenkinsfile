pipeline {
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk11'
    }
    stages {
        def eurekaServer
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
                sh 'mvn -B -DskipTests clean'
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
         stage('Docker Build') {
              steps {
                eurekaServer = docker.build("eurekaServer/Dockerfile:latest")
                eurekaServer.push()
//                 sh 'docker build -t redis:latest .'
//                 sh 'docker build -t rediscommander/redis-commander:latest .'
//                 sh 'docker build -t eurekaServer/Dockerfile:latest .'
//                 sh 'docker build -t zuulServer/Dockerfile:latest .'
//                 sh 'docker build -t usersService/Dockerfile:latest .'
//                 sh 'docker build -t tasksService/Dockerfile:latest .'
            }
         }
         stage('Docker Publish') {
              steps {
                docker.withRegistry('https://hub.docker.com/repository/docker/belmeha/test', 'belmeha') {
                  eurekaServer.push()
                }
              }
         }
    }
}
