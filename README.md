# Proyecto E-commerce

¡Bienvenido al proyecto E-commerce! Esta es una aplicación basada en Scala construida con Cats Effect, Doobie, Http4s y MySQL, diseñada para gestionar categorías, productos, usuarios, órdenes y medios. Este README proporciona instrucciones paso a paso para configurar, compilar, ejecutar, probar y desplegar el proyecto usando VSCode con Metals.

Última actualización: 03:04 PM -05, martes 17 de junio de 2025

## Requisitos Previos

Antes de comenzar, asegúrate de tener instaladas las siguientes herramientas en tu sistema:

- **Java Development Kit (JDK)**: Versión 11 o superior (recomendado: 17). Verifica con `java -version`.
- **Scala Build Tool (SBT)**: Versión 1.9.7 o superior. Instálalo vía [SDKMAN](https://sdkman.io/) con `sdk install sbt 1.9.7`.
- **Servidor MySQL**: Asegúrate de que esté en ejecución y accesible (por ejemplo, `sudo systemctl start mysql`).
- **VSCode**: Con las siguientes extensiones:
  - [Scala (Metals)](https://marketplace.visualstudio.com/items?itemName=scalameta.metals) para soporte de Scala.
  - [Settings Sync](https://marketplace.visualstudio.com/items?itemName=Shan.code-settings-sync) (opcional, para sincronizar configuraciones).
- **Git**: Para clonar el repositorio.

## Estructura del Proyecto

- `build.sbt`: Define las dependencias y configuraciones del proyecto.
- `src/main/scala/`: Contiene el código fuente en Scala (modelos, repositorios, servicios, etc.).
- `src/main/resources/`: Archivos de configuración (por ejemplo, `application.conf`).
- `.env`: Variables de entorno para la configuración de la base de datos.
- `project/`: Archivos de configuración de SBT.
- `README.md`: Este archivo.

## Instrucciones de Configuración

### 1. Clonar el Repositorio

```bash
git clone <url-del-repositorio>
cd ecommerce
```

### 2. Crear un archivo env con estas variables:

- `DATABASE_URL=jdbc:mysql://localhost:3306/<db_name>`
- `DATABASE_USER=<user_name>`
- `DATABASE_PASSWORD=<password>`
- `DATABASE_DRIVER=com.mysql.cj.jdbc.Driver`

### 3. Crear la base de datos (archivo sql.sql)

### 4. Ejecutar:

- `sbt compile`
- `sbt run`
