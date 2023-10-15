FROM openjdk
WORKDIR /home
COPY build/libs/LMS-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","LMS-0.0.1-SNAPSHOT.jar"]