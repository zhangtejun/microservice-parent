pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Build starting ....'
                sh 'mvn clean package spring-boot:repackage -DskipTests'
                sh 'printenv'
            }
        }
    }
    post {
        changed {
            echo 'pipeline post changed'
        }
        always {
            echo 'pipeline post always'
        }
        success {
            echo 'pipeline post success'
        }
        failure {
            mail to: '1053946416@qq.com',subject: 'pipeline failed',body: 'pipeline failed body'
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '3')) // 历史构建记录的数量
        timeout(time: 30,unit: 'MINUTES') // 30分钟自动终止pipeline
    }
}
