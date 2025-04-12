library 'jenkins-utility'
library 'jenkins-notifier'

def REPOSITORY = 'springboot-utility-fx'
def NOTIFICATION_EMAIL = 'jparham@snjdigitalsolutions.com'

pipeline {
    agent { label 'Node 1' }
    stages {
        stage('Git'){
            steps {
                script {
                    notifier.build_start()
                    gitUtility.cloneBuildBranch('SNJ-Digital-Solutions/${REPOSITORY}')
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
                notifier.success('${NOTIFICATION_EMAIL}')
            }

        }
        failure {
            script {
                notifier.fail('${NOTIFICATION_EMAIL}')
            }

        }
    }
}
