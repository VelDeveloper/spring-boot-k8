FROM openjdk:8
MAINTAINER VADIVELMURUGAN
EXPOSE 8051
ADD /target/spring-boot-k8-0.0.1-SNAPSHOT.jar spring-boot-k8-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","spring-boot-k8-0.0.1-SNAPSHOT.jar"]