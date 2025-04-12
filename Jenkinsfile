library 'jenkins-utility'
library 'jenkins-notifier'

pipeline {
    agent { label 'Node 1' }

    environment {

    }

    stages {
        stage('Initialize') {
          steps {
            script {
                def REPOSITORY = 'springboot-utility-fx'
                def NOTIFICATION_EMAIL = 'jparham@snjdigitalsolutions.com'
            }
          }
        }

        stage('Git'){
            steps {
                script {
                    notifier.build_start()
                    gitUtility.cloneBuildBranch('SNJ-Digital-Solutions/${env.REPOSITORY}')
                }
            }
        }

        stage('Build & Deploy Library'){
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true && ls'
            }
        }
    }
    post {
        success {
            script {
                notifier.success('${env.NOTIFICATION_EMAIL}')
            }

        }
        failure {
            script {
                notifier.fail('${env.NOTIFICATION_EMAIL}')
            }

        }
    }
}
