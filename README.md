# Proyecto de Facturación

Este proyecto es una aplicación básica de facturación desarrollada en **Java** utilizando **Spring Boot**, **JPA (Hibernate)** y una base de datos **MySQL**. La aplicación permite gestionar productos y generar detalles de facturas almacenadas en la base de datos.

## Tecnologías Utilizadas

- **Java 1.8**
- **Spring Boot**
- **Spring Data JPA** (Hibernate como proveedor)
- **MySQL**
- **Maven** para la gestión de dependencias

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

- **`src/main/java`**: Contiene el código fuente de la aplicación.
  - **Entities**: Clases que representan las tablas de la base de datos (por ejemplo, `Producto` y `DetalleFactura`).
  - **Repositories**: Interfaces que manejan las operaciones CRUD sobre las entidades.
  - **Main Application**: Clase principal que se encarga de iniciar la aplicación.

- **`src/main/resources`**: Contiene archivos de configuración como `application.properties` y `persistence.xml`.

## Requisitos Previos

- **Java JDK 1.8** o superior
- **Maven** instalado
- **MySQL** instalado y configurado

## Configuración de la Base de Datos

Asegúrate de tener una base de datos MySQL configurada. Debes crear una base de datos llamada `facturacion` (puedes cambiar el nombre en la configuración si lo prefieres).

### Crear la Base de Datos y Tablas

Usa el siguiente script para crear la base de datos y las tablas necesarias:

```sql
CREATE DATABASE facturacion;

USE facturacion;

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE detalle_factura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    producto_id INT,
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);
