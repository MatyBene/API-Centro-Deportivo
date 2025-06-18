# API REST para Centro Deportivo

Este proyecto es una API RESTful desarrollada con Spring Boot y Java para la gestión de un centro deportivo. La API permite administrar socios, instructores, actividades y sus respectivas inscripciones, implementando un sistema de roles y permisos para garantizar la seguridad y el correcto acceso a los datos.

El proyecto fue realizado como trabajo práctico final para la materia **Programación III** de la Universidad Tecnológica Nacional, Facultad Regional Mar del Plata.

## ✨ Características Principales

El sistema está diseñado para atender las necesidades de distintos tipos de usuarios, cada uno con funcionalidades específicas para su rol.

### Roles de Usuario

  * **Público (No autenticado):**

      * Consultar el listado de actividades disponibles.
      * Ver los detalles de cada actividad (horario, instructor, precio).
      * Registrarse como nuevo socio o iniciar sesión.

  * **Socio (MEMBER):**

      * Gestionar su perfil: editar información personal y eliminar su cuenta.
      * Buscar actividades por nombre o rango horario.
      * Inscribirse y darse de baja de las actividades.
      * Consultar el listado de clases en las que está inscripto.

  * **Instructor (INSTRUCTOR):**

      * Ver el listado de todas las actividades del sistema y en detalle las que tiene asignadas.
      * Listar y consultar información de todos los socios.
      * Inscribir y dar de baja a socios en las actividades que dicta.

  * **Administrador (ADMIN):**

      * Gestión total de usuarios: puede crear, listar, ver detalles y eliminar socios e instructores.
      * Inscribir o dar de baja a cualquier socio en cualquier actividad del sistema.
      * Control granular mediante `PermissionLevel`.
   
**Niveles de Permiso de Administrador (`PermissionLevel`)**

El rol de ADMIN se complementa con niveles de permiso para un control de acceso más detallado:

  * `USER_MANAGER`: Permisos para gestionar usuarios de menor jerarquía.
    * Puede crear y eliminar socios e instructores.
    * Puede gestionar las inscripciones de los socios.
  * `SUPER_ADMIN`: El nivel más alto de permisos.
    * Hereda todos los permisos de USER_MANAGER.
    * Adicionalmente, puede crear y eliminar otros administradores (excepto a otros SUPER_ADMIN).

## 🛠️ Tecnologías Utilizadas

La API está construida siguiendo los requisitos técnicos del proyecto, utilizando tecnologías modernas del ecosistema de Spring.

  * **Backend:** Java 21, Spring Boot 3.4.5
  * **Base de Datos:** MySQL
  * **Persistencia de Datos:** Spring Data JPA (Hibernate)
  * **Seguridad:** Spring Security, JSON Web Tokens (JWT)
  * **Servidor Web:** Spring Web (Tomcat embebido)
  * **Validaciones:** Jakarta Bean Validation
  * **Documentación de API:** OpenAPI 3 (Swagger)
  * **Gestión de Dependencias:** Maven
  * **Utilitarios:** Lombok

## ⚙️ Arquitectura

El proyecto sigue una **arquitectura en capas** para asegurar una correcta separación de responsabilidades y facilitar su mantenimiento y escalabilidad.

  * **Controller:** Expone los endpoints de la API, maneja las solicitudes HTTP y las respuestas.
  * **Service:** Contiene la lógica de negocio principal y orquesta las operaciones entre los controladores y los repositorios.
  * **Repository:** Gestiona la comunicación con la base de datos a través de Spring Data JPA.
  * **Model:** Define las entidades del dominio (User, Activity, etc.), DTOs y enumeraciones.

## 🚀 Instalación y Puesta en Marcha

Sigue estos pasos para configurar y ejecutar el proyecto en tu entorno local.

### Prerrequisitos

  * Java JDK 21 o superior.
  * Apache Maven 3.9 o superior.
  * Una instancia de MySQL en ejecución.

### Pasos

1.  **Clonar el repositorio:**

    ```sh
    git clone https://github.com/tu-usuario/API-Centro-Deportivo.git
    cd API-Centro-Deportivo/centro-deportivo-api
    ```

2.  **Configurar la base de datos:**

      * Crea una base de datos en MySQL llamada `API_centro_deportivo`.
      * Abre el archivo `src/main/resources/application.properties`.
      * Modifica las siguientes propiedades con tus credenciales de MySQL:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/API_centro_deportivo
        spring.datasource.username=tu_usuario_mysql
        spring.datasource.password=tu_contraseña_mysql
        ```

3.  **Configurar la clave secreta JWT:**

      * En el mismo archivo `application.properties`, se define la clave para firmar los tokens JWT.
      * Es recomendable configurar `JWT_SECRET_KEY` como una variable de entorno en tu sistema para mayor seguridad.
        ```properties
        jwt.secret=${JWT_SECRET_KEY}
        ```

## 📚 Documentación de la API

La API está completamente documentada utilizando **Swagger/OpenAPI**. Una vez que la aplicación esté en funcionamiento, puedes acceder a la interfaz de Swagger UI para explorar y probar todos los endpoints de forma interactiva.

  * **URL de Swagger UI:** `http://localhost:8080/swagger-ui/index.html`

## 👨‍💻 Autores

  * [Bassi Servant, Tomas](https://github.com/tomasbassi)
  * [Benedetti, Matias](https://github.com/MatyBene)
