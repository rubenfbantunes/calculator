pipeline
{
    agent {
        label "mvn-1"
    }
    parameters
    {
        // Tem que se ir ao Jenkins > Configure > This project is parameterized. 
        string(name: 'DOCKER_IMAGE_NAME', defaultValue: 'image_name', description: 'Docker image name')
        string(name: 'DOCKER_CONTAINER_NAME', defaultValue: 'container_name', description: 'Docker container name')
        string(name: 'DOCKER_PORT', defaultValue: '3000', description: 'Docker port')
    }
 
 
     stages
    {
 
        stage('Stage docker build')
        {
            steps
            {
				withCredentials([usernameColonPassword(credentialsId: 'nexusid', variable: 'login_nexus')]) {
                sh "docker rmi -f ${DOCKER_IMAGE_NAME}:v1.0"
                sh "docker build -t ${DOCKER_IMAGE_NAME}:v1.0 ."
                sh "javac *.java"
                sh "jar cfe calculator.jar calculator *.class"
				}
            }
        }
		stage('Enviar artefacto para Jenkins e Nexus')
        {
            steps
            {
				withCredentials([usernameColonPassword(credentialsId: 'nexusid', variable: 'login_nexus')]) {
                sh 'curl -v -u "$login_nexus" --upload-file upload-file /var/lib/jenkins/workspace/java-calculator-nexus/calculator.jar http://nexus:8081/repository/my-raw/'
				}
            }
        }
		stage("Push da imagem para o Nexus"){
			steps{
				withCredentials([usernamePassword(credentialsId: 'nexusid', passwordVariable: 'pass', usernameVariable: 'user')]) {
					sh "docker login -u '$user' -p '$pass' localhost:8082"
                	sh "docker tag ${DOCKER_IMAGE_NAME}:v1.0 localhost:8082/${DOCKER_IMAGE_NAME}:v1.0"
                	sh "docker push localhost:8082/${DOCKER_IMAGE_NAME}:v1.0"
				}
			}
		}
  
        // Apaga os dados do workspace.
        stage('Stage D - Clean up resources')
        {
            steps
            {
                cleanWs()
            }
        }
        
    }
}