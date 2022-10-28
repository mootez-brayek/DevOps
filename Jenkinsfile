pipeline  {
     agent any
      tools{
          maven 'M2_HOME'
          jdk 'JAVA_HOME'
     }
     environment {
             SONAR_TOKEN = credentials('sonar-token')
             registry = "auckfmine/devops" 
             registryCredential = 'dockerhub_id' 
             dockerImage = ''
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
                 sh 'mvn clean compile'

             }
         }
          stage("SonarQube analysis") {
              steps{
                  withSonarQubeEnv('sonarServer') {
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://172.10.0.140:9000 -Dsonar.login=$SONAR_TOKEN'
                }
              }
              
          }
          stage("SonarQube Quality gate") {
            steps {
                waitForQualityGate(abortPipeline: false) 
                
            }
        }
          stage('build artifact'){
              steps{
                  sh 'mvn package'
              }
          }
          
          stage('deploy jar to nexus'){
              steps{
                  sh 'mvn deploy:deploy-file -DgroupId=com.esprit.examen \
                        -DartifactId=tpAchatProject \
                        -Dversion=1.0 \
                        -Dpackaging=jar \
                        -Dfile=./target/tpAchatProject-1.0.jar \
                        -DrepositoryId=esprit-devops \
                        -Durl=http://172.10.0.140:8081/repository/esprit-devops/'
              }
          }
          
          stage('Building docker images') { 
            steps { 
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                    latestDockerImage = docker.build registry + ":latest" 
                }
            } 
        }
        
        stage('Deploy docker images') { 
            steps { 
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                        latestDockerImage.push()
                   }
                } 
            }
        }
       
        stage('Cleaning up') { 
            steps { 
               sh "docker rmi $registry:$BUILD_NUMBER $registry:latest"
            }
        } 
     }
     post {
         always {
            emailext attachLog: true,recipientProviders: [[$class: 'DevelopersRecipientProvider'],[$class: 'RequesterRecipientProvider']],subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!',body: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: \n Check console output at $BUILD_URL to view the results.'
        }
     }
 }
