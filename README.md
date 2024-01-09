# Spring Test Project

Este proyecto es una aplicación de muestra construida con Spring Boot y diseñada para gestionar precios de productos. A continuación, se proporciona una breve descripción de la estructura del proyecto y cómo ejecutar y probar la aplicación.

## Tecnologías
Este proyecto fue creado con:

* **Java:** 17
* **Spring Boot:** 3.1.4
* **JUnit:** 5.9.3
* **Mockito:** 5.3.1
* **H2 Database:** 2.1.214

## Estructura del Proyecto

El proyecto sigue una arquitectura limpia, dividiendo las responsabilidades en distintos paquetes.
- **application:** Contiene las clases responsables de interactuar con las capas externas de la aplicación.
  - *gateways:* Abstracciones para interactuar con servicios externos.
  - *usecases:* Lógica de negocio de la aplicación.
- **domain:** Define las entidades clave del dominio.
  - *entity:* Objetos de dominio que representan las entidades de la aplicación.
- **infrastructure:** Implementación concreta de la aplicación, incluyendo la capa de persistencia.
  - *controller:* Controladores REST para gestionar las solicitudes HTTP.
  - *gateways:* Implementaciones concretas de las abstracciones en el paquete application.
  - *persistence:* Capa de acceso a la base de datos.
- **exception**
  - Manejo de excepciones específicas de la aplicación.
- **main** 
  - Configuración de inyección de dependencias.
## Configuración y Ejecución

Para ejecutar la aplicación, asegúrate de tener Java y Maven instalados. Luego, puedes usar el siguiente comando:

```bash
mvn spring-boot:run
```
La aplicación comenzará a ejecutarse en <http://localhost:8080>

## Pruebas

El proyecto incluye pruebas unitarias y de integración. Puedes ejecutar las pruebas utilizando el siguiente comando:

```bash
mvn test
```

## Explorar APIs REST

La aplicación define la siguiente API.

### Prices

| Método  | Url | Descripción      | Ejemplo de Solicitud Válida |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/v1/prices/applicationDate/{applicationDate}/product/{idProduct}/brand/{idBrand} | Obtener Precio | [Ejemplo](http://localhost:8080/api/v1/prices/applicationDate/2020-06-14T10:00:00/product/35455/brand/1) 
 |

## Pruebas en Postman

1. Abre Postman.
2. Crea una nueva solicitud GET con la URL mencionada anteriormente.
3. Agrega los parámetros de consulta necesarios (applicationDate, idProduct, idBrand).
4. Envía la solicitud y verifica la respuesta.
### Ejemplo de Respuesta

#### Respuesta Exitosa (200 OK)

```json
{
  "idProduct": 35455,
  "idBrand": 1,
  "idPriceRate": 4,
  "startDate": "2020-06-15T16:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 38.95
}
```

#### Respuesta de Error (404 Not Found)

```json
{
  "datetime": "2023-12-14T12:11:12.4929908",
  "message": "No records found",
  "details": "uri=/api/v1/prices",
  "errors": null
}
```