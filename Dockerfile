FROM openjdk:16-jdk-alpine
COPY *.jar /app/calculadora.jar
ENTRYPOINT ["java","-jar","/app/calculadora.jar"]