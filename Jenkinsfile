library 'jenkins-utility'
library 'jenkins-notifier'

pipeline {
    agent { label 'Node 1' }

    environment {
        REPOSITORY = 'springboot-utility-fx'
        NOTIFICATION_EMAIL = 'jparham@snjdigitalsolutions.com'
    }

    stages {
        stage('Git'){
            steps {
                script {
                    notifier.build_start()
                    gitUtility.cloneBuildBranch("SNJ-Digital-Solutions/${env.REPOSITORY}")
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
                notifier.success("${env.NOTIFICATION_EMAIL}")
            }

        }
        failure {
            script {
                notifier.fail("${env.NOTIFICATION_EMAIL}")
            }
        }
    }
}
