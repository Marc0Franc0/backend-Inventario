FROM openjdk:11
COPY target/backend-carritoDeComprasApp-0.0.1-SNAPSHOT.jar  backend-inventario-app.jar
ENTRYPOINT [ "java", "-jar","/backend-inventario-app.jar" ]