pipeline
{
    agent {
        label "mvn-1"
    }
    parameters
    {
        // Tem que se ir ao Jenkins > Configure > This project is parameterized. 
        string(name: 'DOCKER_IMAGE_NAME', defaultValue: 'calculadora_img', description: 'Docker image name')
        string(name: 'DOCKER_CONTAINER_NAME', defaultValue: 'calculadora_cont', description: 'Docker container name')
        string(name: 'DOCKER_PORT', defaultValue: '3000', description: 'Docker port')
    }
 
 
     stages
    {
 
        stage('Stage docker build')
        {
            steps
            {
                sh "docker rmi -f ${DOCKER_IMAGE_NAME}:v1.0"
                sh "docker build -t ${DOCKER_IMAGE_NAME}:v1.0 ."
                sh "javac *.java"
                sh "jar cfe calculator.jar Calculator *.class"
            }
        }
		stage('Enviar artefacto para Jenkins e Nexus')
        {
            steps
            {
				withCredentials([usernameColonPassword(credentialsId: 'nexusid', variable: 'login_nexus')]) {
                sh 'curl -v -u "$login_nexus" --upload-file /var/lib/jenkins/workspace/Calculator/calculator.jar http://nexus:8081/repository/my-raw/'
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
        stage ('Sonar') {
        def scannerHome = tool 'sonarqube';
        withSonarQubeEnv ('sonarqube') {
        sh "${scannerHome}/bin/sonar-scanner \
        -D sonar.login=10e7f044db1f3e3e262be3620838af122f8d2921 \
        -D sonar.projectKey=Teste \
        -D sonar.exclusions=vendor/**,resources/**,**/*.java \
        -D sonar.host.url=http://sonar:9000/"
        }
        }
        stage ('Quality Check') {
        timeout(time: 1, unit: 'HOURS') {
        def qualitygate = waitForQualityGate()
        if (qualitygate.status != "OK") {
        error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
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