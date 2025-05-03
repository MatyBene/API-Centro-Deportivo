# Requisitos no funcionales

1. Seguridad de la información

    RNF01. Las contraseñas deben encriptarse y guardarse en la base de datos de esta forma.

2. Autenticación y autorización robusta

    RNF02. El acceso a la API debe estar protegido mediante JWT (JSON Web Token), validando cada petición.

3. Documentación de la API

    RNF03. La API debe estar documentada para facilitar la comprensión de sus endpoints.

4. Arquitectura en capas

    RNF04. El sistema debe estar organizado en capas: Controller, Service, Repository, respetando la separación ded responsabilidades.

5. Estándares REST

    RNF05. Los endpoints deben cumplir con buenas prácticas REST: uso correcto de métodos HTTP, rutas semánticas, códigos de estado adecuados.

6. Validación de entrada

    RNF06. Las entradas del usuario deben validarse con anotaciones y responder con mensajes claros ante errores.

7. Manejo centralizado de excepciones

    RNF07. El sistema debe manejar excepciones de forma centralizada utilizando ```@ControllerAdvice```.

8. Persistencia de datos

    RNF08. Toda la información debe guardarse de forma persistente en una base de datos MySQL.