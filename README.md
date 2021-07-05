# java-challenge

Requisitos:

 * Java 11 
 * PostgreSQL 13 (opcional)
 * Apache Maven 3.6
 * Docker 20.10.6 (opcional)
 * docker-compose 1.21.2 (opcional)

Primero si decidimos ejecutar la aplicacion y sus pruebas utilizando una base de datos local debemos crear la base de datos y nuestro usuario y contraseña. Una vez creados debemos editar el archivo src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/cashonline <- reemplazar 'cashonline' por el nombre de la base de datos que hayamos creado.
Para las propiedades spring.datasource.username y spring.datasource.password las establecemos segun el usuario y contraseña que hayamos configurado.

Luego abrimos una terminal en la carpeta raiz del proyecto y corremos el siguiente comando:

mvn spring-boot:run

Para correr la aplicacion utlizando docker, abrir una terminal en este directorio y correr los siguientes comandos:

1) cd src/main/docker
2) docker-compose up

Para ejecutar las pruebas si no tenemos Postgre instalado en nuestra maquina podemos hacerlo con los containers que se levantaron anteriormente, una vez lenvatemos los contenedores ejecutamos (en la carpeta raiz) ejecutamos:

mvn clean test

Si tenemos Postgre instalado y configuramos correctamente el archivo application.properties con correr el comando se deberian ejecutar las pruebas.


Aclaracion de la solucion:

Agregue tambien un endpoint para crear loans para realizar algunas pruebas. Se encuentra en una nueva coleccion de postman en la carpeta raiz

