#FROM openjdk:11
FROM amazoncorretto:17-alpine-jdk
COPY target/backend-carritoDeComprasApp-0.0.1-SNAPSHOT.jar  backend-inventario-app.jar
ENTRYPOINT [ "java", "-jar","/backend-inventario-app.jar" ]

#FROM openjdk:11-jdk-slim
#COPY /target/backend-carritoDeComprasApp-0.0.1-SNAPSHOT.jar backend-inventario-app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","backend-inventario-app.jar"]