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
                    def list = ['ppos-basics','B']
                     println(list.contains("${project}"))
                     if(!(list.find { it == "${project}" } == "${project}")){
                         echo "project input error : [${project}]"
                         ret = false
                     }else{
                         echo "will be build project : [${project}]"
                         ret = true
                     }
                }

            }
        }
        stage('Build amc-common starting') {
            when {
                allOf {
                expression({return params.choice == 'Y'}); equals expected: true, actual: ret
                }
            }
            steps {
                sh "cd ${env.WORKSPACE}"
                echo 'checkout amc-common ...........................................'
                sh 'printenv'
                checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, includedRegions: '', locations: [[cancelProcessOnExternalsFail: true, credentialsId: 'zhangtj28511', depthOption: 'infinity', ignoreExternalsOption: true, local: './PPOS/amc-common', remote: 'https://192.168.57.209/fund/dept2/PPOS2.0/trunk/Sources/PPOS/amc-common']], quietOperation: true, workspaceUpdater: [$class: 'UpdateUpdater']])
                //
                echo 'build amc-common ...........................................'
                echo "当前路径为: "
                sh 'pwd'
                sh "cd ${env.WORKSPACE}/PPOS/amc-common"
                sh 'pwd'
                sh 'mvn clean install -s /var/jenkins_home/setting/settings.xml -Dmaven.test.skip=true '

            }
        }
        stage('Build api-repository starting') {
            when {
                allOf {
                expression({return params.choice == 'Y'}); equals expected: true, actual: ret
                }
            }
            steps {
                sh "cd ${env.WORKSPACE}"
                echo 'checkout api-repository ...........................................'
                sh 'printenv'
                checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, includedRegions: '', locations: [[cancelProcessOnExternalsFail: true, credentialsId: 'zhangtj28511', depthOption: 'infinity', ignoreExternalsOption: true, local: './PPOS/api-repository', remote: 'https://192.168.57.209/fund/dept2/PPOS2.0/trunk/Sources/PPOS/api-repository']], quietOperation: true, workspaceUpdater: [$class: 'UpdateUpdater']])
                //
                //sh 'mvn clean package spring-boot:repackage -DskipTests'
                echo 'build api-repository ...........................................'

                echo "当前路径为: "
                sh 'pwd'
                sh "cd ${env.WORKSPACE}/PPOS/api-repository"
                sh 'pwd'
                sh 'mvn clean install -s /var/jenkins_home/setting/settings.xml -Dmaven.test.skip=true '
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
            echo 'pipeline post failed'
            // mail to: '1053946416@qq.com',subject: 'pipeline failed',body: 'pipeline failed body'
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '3')) // 历史构建记录的数量
        timeout(time: 30,unit: 'MINUTES') // 30分钟自动终止pipeline
    }
}
