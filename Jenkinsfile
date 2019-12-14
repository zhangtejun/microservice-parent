pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    triggers {
        // 1. 5个字段 MINUTE(1小时内的分钟) HOUR(一天内的小时) DOM(一个月的某一天) MONTH(月份) DOW(星期)
        // cron('0 0 * * * ')
        // 2. 由上游任务触发
        upstream(upstreamProjects: 'test1', threshold: hudson.model.Result.SUCCESS)
    }
    stages {
        stage('Build') {
            steps {
                echo 'Build starting ....'
                sh 'mvn clean package spring-boot:repackage -DskipTests'
                sh 'printenv'
            }
            setps {
                script {
                    def list = ['A','B']
                    for(int i =0;i<list.size();i++){
                        echo "Testing the List: ${list[i]} "
                    }
                }
            }
        }
        stage('TestParam') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo 'TestParam starting ....'
                echo "Hello, ${PERSON}, nice to meet you."
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
pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    triggers {
        // 1. 5个字段 MINUTE(1小时内的分钟) HOUR(一天内的小时) DOM(一个月的某一天) MONTH(月份) DOW(星期)
        // cron('0 0 * * * ')
        // 2. 由上游任务触发
        upstream(upstreamProjects: 'test1', threshold: hudson.model.Result.SUCCESS)
    }
    stages {
        stage('Build') {
            steps {
                echo 'Build starting ....'
                sh 'mvn clean package spring-boot:repackage -DskipTests'
                sh 'printenv'
            }
            steps {
                script {
                    def list = ['A','B']
                    for(int i =0;i<list.size();i++){
                        echo "Testing the List: ${list[i]} "
                    }
                }
            }
        }
        stage('TestParam') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo 'TestParam starting ....'
                echo "Hello, ${PERSON}, nice to meet you."
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
