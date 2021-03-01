pipeline
{
	agent {
		label "mvn-1"
	}
	environment {
        //NEXUS_VERSION = "nexus3"
        //NEXUS_PROTOCOL = "http"
        NEXUS_URL = "http://localhost:8081/repository/my-raw/"
        //NEXUS_REPOSITORY = "my-raw"
        NEXUS_CREDENTIAL_ID = credentials("nexusid")
    }
	parameters
	{
		// Tem que se ir ao Jenkins > Configure > This project is parameterized. 
		string(name: 'DOCKER_IMAGE_NAME', defaultValue: 'default_name', description: 'Docker image name')
		string(name: 'DOCKER_CONTAINER_NAME', defaultValue: 'default_name', description: 'Docker container name')
		string(name: 'DOCKER_PORT', defaultValue: '3000', description: 'Docker port')
	}

    stages
    {
		/*stage ('Stage maven')
		{
			steps
			{
				sh 'mvn clean package'
			}
		}*/
  
        stage('Stage docker build')
        {
            steps
            {
				sh "docker rmi -f ${DOCKER_IMAGE_NAME}"
				sh "docker build -t ${DOCKER_IMAGE_NAME}:v1.0 ."
				sh "docker login credentials("nexusid") localhost:8082"
				sh "docker tag ${DOCKER_IMAGE_NAME} localhost:8082/${DOCKER_IMAGE_NAME}:v1.0"
				sh "docker push localhost:8082/${DOCKER_IMAGE_NAME}:v1.0"
				sh "curl -v --user '${NEXUS_CREDENTIAL_ID}' --upload-file .*.jar ${NEXUS_URL}"
            }
        }

		stage('Stage docker run')
        {
            steps
            {
				sh "docker rm -f ${DOCKER_CONTAINER_NAME}"
				//sh "docker run -d -p ${DOCKER_PORT}:8080 --name ${DOCKER_CONTAINER_NAME} ${DOCKER_IMAGE_NAME}"
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
