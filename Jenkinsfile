pipeline {
    agent {
        label 'docker' 
    }
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
        stage('Docker maven test') {
            agent {
                docker {
          // Set both label and image
                    label 'docker'
                    image 'redis'
                }
            }   
        }
    }
}
