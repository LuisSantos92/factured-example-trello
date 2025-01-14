
# Sistema de Gestión de Tableros y Tareas

## Descripción del Problema y Contexto

Este proyecto es un sistema básico de gestión de tableros y tareas, inspirado en herramientas como Trello. Permite a los usuarios gestionar tableros, asignarles tareas y generar reportes con métricas clave.

### Problema
- Los usuarios necesitan administrar tableros y tareas asociadas.
- Requieren reportes para analizar el estado y la distribución de tareas con grandes volúmenes de datos.

### Solución Propuesta
Se implementará un sistema con las siguientes características principales:
1. **Entidades**:
    - **Tableros**: `id`, `nombre`, `descripcion`, `fecha_creacion`.
    - **Tareas**: `id`, `titulo`, `descripcion`, `estado` (pendiente, en progreso, completada), `fecha_creacion`, `id_tablero` (relación con el tablero).

2. **Funcionalidades**:
    - CRUD para tableros y tareas.
    - Cambiar el estado de las tareas.
    - Generar reportes con estadísticas básicas.

---

## Tecnologías Utilizadas

- **Backend**: Python con FastAPI.
- **Bases de datos**:
    - Escritura: PostgreSQL.
    - Lectura: MongoDB.
- **Contenedores**: Docker para portabilidad.
- **Gestión de dependencias**: Poetry.
- **Seguridad**: Aplicación de principios OWASP (sanitización de entradas y autenticación segura con JWT).

---

## Arquitectura

El sistema sigue una arquitectura basada en CQRS (Command Query Responsibility Segregation), con componentes separados para comandos (escritura) y consultas (lectura).

---

## Funcionalidades Principales

1. **CRUD**:
    - Tableros: Crear, leer, actualizar, eliminar.
    - Tareas: Crear, leer, actualizar, eliminar.
2. **Cambio de Estado**: Endpoint para mover tareas entre estados (pendiente, en progreso, completada).
3. **Reporte**: Endpoint que genera un reporte con estadísticas como conteo y proporción de tareas por estado y tablero.
4. **Datos Masivos**: Generación de 100,000 registros aleatorios para pruebas de rendimiento.

---

## Seguridad

- **Sanitización de entradas**: Prevenir inyecciones SQL.
- **Autenticación segura**: Implementación de JWT para proteger endpoints sensibles.

---

## Dockerización

Este proyecto incluye un archivo `docker-compose.yml` que permite levantar la aplicación y los servicios necesarios (PostgreSQL y MongoDB).

### Instrucciones para ejecutar
1. Clonar este repositorio:
   ```bash
   git clone <URL-DEL-REPOSITORIO>
   ```
2. Acceder al directorio del proyecto:
   ```bash
   cd <directorio-del-proyecto>
   ```
3. Iniciar los servicios:
   ```bash
   docker-compose up --build
   ```
4. Acceder a la API en [http://localhost:8000](http://localhost:8000).

---

## Repositorio

Este proyecto está versionado con Git y se encuentra disponible en el siguiente enlace:
[URL del repositorio](https://gitlab.com/unicomer/digital-factory-financial-services)

---

## Documentación Adicional

### Patrones de Diseño
- **CQRS**: Separación de responsabilidades para mejorar el rendimiento y escalabilidad.
- **Patrón Repositorio**: Abstracción del acceso a datos para facilitar mantenibilidad.

### Principios SOLID
- **Inyección de Dependencias**: Mejora la testabilidad y el mantenimiento del sistema.
- **Clases segregadas**: Cada clase tiene una única responsabilidad.

### Práctica OWASP
- Validaciones robustas en todos los datos recibidos por los endpoints.
- Uso de JWT para proteger recursos sensibles.

---

## Frontend (Opcional)

Se incluye un cliente básico desarrollado en **React.js** para interactuar con el sistema. Si no se utiliza, se proporciona documentación para probar la API con Postman.

---

## Reporte Generado

El sistema genera un reporte con:
- Conteo de tareas por estado (pendiente, en progreso, completada).
- Proporción de tareas completadas por tablero.

**¡Prueba este sistema para optimizar la gestión de tareas!**
