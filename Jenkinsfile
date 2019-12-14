def ret
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
    parameters {
        /*
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
        */
        //
        choice(name: 'folder', choices: ['PPOS', 'AMOS'], description: '项目位于PPOS/AMOS?')
        choice(name: 'choice', choices: ['Y', 'N'], description: '是否需要先构建amc-common和api-repository? 默认Y(需要先构建)')
        string(name: 'project', defaultValue: 'ppos-basics', description: '需要构建的项目名?')
    }
    stages {
        stage('Build starting  check parameters') {
            steps {
                script {
                    def list = ['A','B']
                     if(!list.find(it == ${project})){
                         echo "project input error : [${project}]"
                         ret = false
                     }else{
                         echo "will be build project : [${project}]"
                     }
                     ret = true
                }

            }
        }
        stage('Build starting amc-common') {
            when {
                allOf {
                expression({return params.choice == 'Y'});ret: true
                }
            }
            steps {
                echo "${ret}"
                echo 'Build starting ....'
                sh 'mvn clean package spring-boot:repackage -DskipTests'
                sh 'printenv'
            }
        }
        stage('Build stage 2') {
            when {
                allOf {
                expression({return params.choice == 'Y'}); equals expected: true, actual: ret
                }
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
        stage('TestParam2') {
            steps {
                echo "Hello ${params.PERSON}"
                echo "Biography: ${params.BIOGRAPHY}"
                echo "Toggle: ${params.TOGGLE}"
                echo "Choice: ${params.CHOICE}"
                echo "Password: ${params.PASSWORD}"
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
