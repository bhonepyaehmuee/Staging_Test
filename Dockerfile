FROM eclipse-temurin:21-jdk
WORKDIR /app
LABEL maintainer = "javaguides.net"
ADD target/staging-test-0.0.1-SNAPSHOT.jar stagingTest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","stagingTest.jar"]
