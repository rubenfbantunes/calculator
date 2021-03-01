# calculator

Versao do java
openjdk:16-jdk-alpine

Criar imagem através do dockerfile e correr container:
docker build -t calculadoraimg .
docker run -d -p port:port

Correr os seguintes comandos na powershell dentro da pasta calculator:
1. javac Calculator.java
2. jar cfe calculadora.jar Calculator *.class
3. Correr o comando java -jar calculator.jar