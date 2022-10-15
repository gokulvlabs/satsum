FROM openjdk:18
ADD target/springboot-satsum.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]