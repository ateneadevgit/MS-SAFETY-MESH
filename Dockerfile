FROM openjdk:17-oracle
RUN mkdir data-shared
COPY target/ms-safety-mesh.jar ms-safety-mesh.jar
EXPOSE 8019
ENTRYPOINT ["java","-jar","/ms-safety-mesh.jar"]