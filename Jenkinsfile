// 젠킨스 파이프라인 설정
pipeline {
    agent any

    environment {
        DOCKER_REPO = "devktest/taskManagementApp"
        DOCKER_CREDENTIAL = "dockerhub"
        JENKINS_AT_STAGING = "jenkins@staging.taskManagementApp.com"
    }

    stages {
        stage("Build package") {
            steps {
                echo "Git commit: ${env.GIT_COMMIT}"
                sh "mvn clean package"
            }
        }

        stage("Build Docker image") {
            steps {
                sh "cp taskManagementApp/app-0.0.1-SNAPSHOT.jar docker/app.jar"
                sh "docker build -t ${DOCKER_REPO}:${env.GIT_COMMIT} docker/"
            }
        }

        stage("Push Docker build image") {
            steps {
                withDockerRegistry([ credentialsId: DOCKER_CREDENTIAL, url: '' ]) {
                  sh "docker push ${DOCKER_REPO}:${env.GIT_COMMIT}"
                }
            }
        }

        stage("Deploy to staging") {
            steps {
                sh "ssh ${JENKINS_AT_STAGING} rm -fr /app/env.list /app/start.sh"
                sh "scp ./docker/env.list ./docker/start.sh ${JENKINS_AT_STAGING}:/app"
                sh "ssh ${JENKINS_AT_STAGING} \"cd /app && ./start.sh ${env.GIT_COMMIT}\""
            }
        }

    }
}
