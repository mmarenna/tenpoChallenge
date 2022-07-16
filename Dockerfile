FROM openjdk:11
VOLUME /tmp
ADD target/tenpoapp-0.0.1-SNAPSHOT.jar tenpoapp.jar
ENTRYPOINT ["java","-jar","/tenpoapp.jar"]