FROM openjdk:14.0
COPY /build/libs/sucursal-app-0.0.1-SNAPSHOT.jar sucursal-app.jar
ENTRYPOINT ["java","-jar","sucursal-app.jar"]