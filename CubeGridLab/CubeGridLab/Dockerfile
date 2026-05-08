FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/CubeGridLab-0.0.1.jar
COPY ${JAR_FILE} app_CubeGridLab.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app_CubeGridLab.jar"]