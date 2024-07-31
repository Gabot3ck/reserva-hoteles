# IMAGEN MODELO
FROM eclipse-temurin:17.0.12_7-jdk

#DEFINIR ROOT DEL CONTENEDOR
WORKDIR /root

#COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#DESACARGAR LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline

#COPIAR EL CÓDIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src /root/src

#CONSTRUIR NUESTRA APLICACIÓN
RUN ./mvnw clean install -DskipTests

#LEVANTAR LA APP CUANDO INICIE EL CONTENEDOR
ENTRYPOINT [ "java", "-jar", "/root/target/ReservaHoteles-0.0.1-SNAPSHOT.jar" ]
