FROM amazoncorretto:17.0.0-alpine
COPY target/main-1.0-SNAPSHOT.jar lib.jar
ENTRYPOINT ["java","-jar","lib.jar"]