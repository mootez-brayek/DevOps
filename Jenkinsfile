pipeline  {
     agent any
      tools{
          maven 'M2_HOME'
          jdk 'JAVA_HOME'
     }
     environment {
             SONAR_TOKEN = credentials('sonar-token')
             registry = 'auckfmine/devops'
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
         stage('Increment Version'){
             steps{
                 script{
                    echo 'Incrementing Artifact Version ...'
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[1][1]
                    echo '$version'
                    env.VERSION = "$version"
                 }
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
                        -Dversion=$VERSION \
                        -Dpackaging=jar \
                        -Dfile=./target/tpAchatProject-$VERSION.jar \
                        -DrepositoryId=esprit-devops \
                        -Durl=http://172.10.0.140:8081/repository/esprit-devops/'
              }
          }
          
          stage('Building docker images') { 
            steps { 
                script { 
                    dockerImage = docker.build registry + ":$VERSION" 
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
               sh "docker rmi $registry:$VERSION $registry:latest"
            }
        } 
        
        stage('deploy'){
            steps{
                sh 'docker-compose up -d'
            }
        }
        
        stage('commit version update'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'github_id', variable: 'github_id')]){
                        sh 'git config --global user.email "jenkins@jenkins.com"'
                        sh 'git config --global user.name "jenkins"'
                        sh "git remote set-url origin https://${github_id}@github.com/mootez-brayek/DevOps.git"
                        sh 'git add .'
                        sh 'git commit -m "ci: version update"'
                        sh 'git push origin HEAD:service/reglement'
                    }
                }
            }
        }
     }
     
     post {
         always {
            emailext attachLog: true,recipientProviders: [[$class: 'DevelopersRecipientProvider'],[$class: 'RequesterRecipientProvider']],subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!',body: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: \n Check console output at $BUILD_URL to view the results.'
        }
     }
 }
