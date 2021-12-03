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
                sh 'mvn clean install' 
            }
        }
        stage ('SonarQube analysis') {
            steps {
                        withSonarQubeEnv('SonarQube') {
                            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.0:sonar'
                        }
            }
        }
    }
}
