pipeline  {
     agent any
     environment {
             SONAR_TOKEN = credentials('sonar-token')
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
           stage("build jar file") {
            steps {
                sh 'mvn package'
            }
          }
          stage("deploy to nexus") {
            steps {
                sh 'mvn deploy:deploy-file -DgroupId=com.esprit.examen \
                    -DartifactId=tpAchatProject \
                    -Dversion=1.0 \
                    -Dpackaging=jar \
                    -Dfile=./target \
                    -DrepositoryId=esprit-devops \
                    -Durl=http://172.10.0.140:8081/repository/esprit-devops/'
            }
          }
     }

 }
