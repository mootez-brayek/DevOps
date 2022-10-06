pipeline  {
     agent any
     environment {
             SONAR_TOKEN = credentials('sonarqube-token')
         }
     stages{
          stage('Chekout GIT'){
             steps{
                 echo 'Pulling...';
                 git branch: 'service/reglement' ,
                 url : 'https://github.com/mootez-brayek/DevOps.git'
             }
         }
          stage('cleaning java Project'){
             steps{
                 sh 'mvn clean'

             }
         }
         stage('compiling  java Project'){
             steps{
                 sh 'mvn compile'

             }
         }
          stage("build & SonarQube analysis") {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://172.10.0.140:9000 -Dsonar.login=$SONAR_TOKEN'
            }
          }
     }

 }