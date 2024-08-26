# Demo

Este es un proyecto de ejemplo para una prueba tecnica que utiliza  Spring Boot, Hibernate, MapStruct, Lombok, y JaCoCo, para desarrollar y mantener una API de creacion de usuarios. 
El proyecto también incluye la implementación de Swagger para la documentación de la API, JWT para la autenticación, y H2 como base de datos en memoria para propósitos de prueba.

## Tecnologías Utilizadas

- **Spring Boot**: Marco de trabajo para crear aplicaciones de Java con microservicios, que facilita la configuración y el desarrollo de aplicaciones backend.
- **Hibernate**: Framework ORM (Object-Relational Mapping) que facilita la interacción con bases de datos, permitiendo trabajar con objetos Java en lugar de SQL directamente.
- **MapStruct**: Herramienta de mapeo de Java para la conversión de objetos entre diferentes capas de la aplicación de manera eficiente y segura.
- **Lombok**: Biblioteca que reduce el código repetitivo al generar automáticamente getters, setters, constructores, entre otros, a partir de anotaciones.
- **JaCoCo**: Herramienta de cobertura de pruebas de código para Java, que se integra con Gradle para generar informes detallados sobre el porcentaje de código cubierto por las pruebas.
- **JJWT**: Biblioteca para crear y validar tokens JWT (JSON Web Tokens) configurados a cada usuario.
- **SpringDoc OpenAPI**: Herramienta para la generación automática de la documentación de la API con Swagger.
- **H2 Database**: Base de datos en memoria utilizada para pruebas y desarrollo rápido, sin necesidad de configurar un servidor de base de datos externo.
- **JAXB**: Biblioteca para trabajar con XML en Java, utilizada para la manipulación de datos en formato XML.


## Configuración de Swagger

Swagger está habilitado para documentar y probar los endpoints de la API de manera interactiva. La configuración se encuentra en la clase `SwaggerConfig.java`, y la interfaz de Swagger está disponible en:

http://localhost:8080/swagger-ui.html


## Configuración del Proyecto

### 1. Clonar el Repositorio
### 2. Compilar y Ejecutar el Proyecto
./gradlew bootRun
### !Disclamer: 
Actualmente el proyecto se encuentra con una discrepancia al levantarlo por primera vez y su uso en veces posteriores, no afecta la funcionalidad completa del proyecto con este work arround: 
Una vez buildeado el proyecto, descomentar la linea 
   @Mapping(target = "id", expression = "java(getUuid())")
en la clase PhoneDataMapper y comentar las lineas en cada Mapper correspondiente. 
UserDataMapper INSTANCE = Mappers.getMapper( UserDataMapper.class );
PhoneDataMapper INSTANCE = Mappers.getMapper( PhoneDataMapper.class );
Debido al tiempo necesario para cumplir con la entrega , se deja esta solucion momentanea, estoy buscando activamente una solucion definitiva. 
### 3. Acceder a la Documentación de la API
http://localhost:8080/swagger-ui.html
### 4. Ejecución de Pruebas
./gradlew test jacocoTestReport
- Los informes de cobertura de JaCoCo se generarán en el directorio build/jacocoHtml, accesibles en index.html.
- Actualmente el proyecto está con cobertura de pruebas sobre el 90%

