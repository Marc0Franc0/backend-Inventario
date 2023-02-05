
#
# Build stage
#
FROM openjdk:11 
VOLUME /tmp


# ENV PORT=8080
EXPOSE 8080
ADD ./target/backend-carritoDeComprasApp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]