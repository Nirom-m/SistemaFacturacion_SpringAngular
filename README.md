<br />
<div align="center">
<h3 align="center">Sistema de Facturacion con Spring 3 y Angular 16</h3>
  <p align="center">
    Este fue un proyecto que se realizo para el aprendizaje del framework de Angular y la conexion de esta a un API rest realizada en Java mediante el framework de Sring con Springboot
  </p>
</div>

### Contruido con

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Apache Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
* ![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)

<!-- Tenga en cuenta -->
## Tenga en cuenta

Para que el proyecto funcione lea y siga los siguientes puntos:

### Pre-requisitos

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Maven [https://maven.apache.org/](https://maven.apache.org/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)
* Angular 16 [https://angular.io/guide/setup-local](https://angular.io/guide/setup-local)

### Herramientas recomendadas
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)
* Visual Studio Code [https://code.visualstudio.com/](https://code.visualstudio.com/) : Pdt: Instale las extensiones de Angular Language Service y Angular Snippets

### Instalación

1. Clone el respositorio.
2. Tendra 2 proyectos, Una llamada Angular, que es la parte frontEnd del proyecto, y SpringBoot_API, que es la API rest creada en java con Springboot como backEnd.
3. Abra la carpeta Angular con Visual Studio Code.
4. Abra la carpeta SpringBoot_API con IntelliJ.
5. Deje cargar o cargue las dependencias que estan contenidas en ambos proyectos.
6. BackEnd:
    1. Dirijase al src y ubique el archivo "application.propierties" y cambie los datos a partir de sus nececidades, principalmente el puerto y la url de la base de datos:
    ```yml
    server.port=8081
    spring.datasource.url=jdbc:mysql://localhost:3306/db_angularspring_backend
    spring.datasource.username=root
    spring.datasource.password=1234
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=create
    spring.jpa.defer-datasource-initialization=true
    spring.sql.init.mode=always
    spring.jackson.time-zone=America/Bogota
    spring.jackson.locale=es_CO
    spring.servlet.multipart.max-file-size=10MB
    spring.servlet.multipart.max-request-size=10MB
    spring.servlet.multipart.resolve-lazily=true
    ```
    2. Ponga a correr o ejecute la aplicacion.
    3. En dado caso que haya tenido problemas con el archivo que contiene los datos basicos de carga (src/main/resources/data.sql), configurelo para que este se ejecute de forma correcta, o configurelo para que pueda ejecutarlo de forma manual. Si ha decidido hacerlo manualmente elimine las 2 siguientes lineas del "application.propierties":
    ```yml
    spring.jpa.defer-datasource-initialization=true
    spring.sql.init.mode=always
    ```
7. FrontEnd:
    1. Dirijase al src y ubique la carpeta "config" la cual contendra un archivo llamado config.ts, dentro de este: observara una variable que contiene la direccion con la que se comunica al backEnd, configurela a partir del puerto que haya asignado anterioemente en el backEnd.


<!-- Uso -->
## Uso

1. En IntelliJ (BackEnd): Ejecute la aplicacion SpBackendApiRestApplication
2. En Visual Studio Code (FrontEnd): Abra una consola y ejecute el comando "ng serve -o".
3. Se abrira una pestalla llamada [http://localhost:4200/clientes](http://localhost:4200/clientes) en su navegador

