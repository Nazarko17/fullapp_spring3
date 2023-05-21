FROM openjdk
ADD target/fullapp_spring3-0.0.1-SNAPSHOT.jar fullapp_spring3.jar
ENTRYPOINT ["java","-jar","fullapp_spring3.jar"]
EXPOSE 8080

#mvn install -DskipTests
#docker build -t fullapp_spring3 .