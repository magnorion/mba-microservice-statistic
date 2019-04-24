FROM openjdk:8
ADD target/trabalho_final-0.0.1-SNAPSHOT.jar trabalho_final.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "trabalho_final.jar"]