# CineMax - Backend

Repositorio 2 de 3 del proyecto **Sistema de Taquilla de Cine** (Programación Basada en Componentes).
API REST hecha con **Spring Boot** que administra la cartelera y la venta de boletas, con arquitectura en capas
(**Controller - Service - Repository**).

Repositorios del proyecto:
1. [CineMax-BD](https://github.com/diegoescobararrieta-bit/CineMax-BD): scripts SQL (PostgreSQL).
2. **CineMax-Backend** (este): API REST con Spring Boot.
3. CineMax-Frontend: interfaz en Angular.

## Integrantes
- Diego Escobar Arrieta
- Nestor Andres Torres Mora

## Tecnologías
- Java 21
- Spring Boot 4.1.2 (Spring Web, Spring Data JPA, Validation)
- PostgreSQL (driver JDBC)
- Maven (se incluye `mvnw`, no hace falta instalar Maven)

## Requisitos previos
1. Tener instalado Java 21 (`java -version`).
2. Tener PostgreSQL en ejecución y la base de datos `cinemax` creada con los scripts del repositorio CineMax-BD
   (`01` a `07`; el `08` con datos de prueba es opcional).

## Configuración
La conexión está en `src/main/resources/application.properties` (base `cinemax`, usuario `postgres`, puerto `5432`).
La **contraseña no se sube al repositorio**. Cada persona debe crear el archivo
`src/main/resources/application-secret.properties` (ya está en `.gitignore`) con este contenido:

```properties
spring.datasource.password=SU_CONTRASEÑA
```

Hibernate trabaja en modo `validate`: solo comprueba que las entidades coincidan con las tablas y no modifica la base.

## Cómo ejecutarlo
```bash
./mvnw spring-boot:run
```
La API queda disponible en `http://localhost:8080`.

## Endpoints

### Películas: `/api/peliculas` (CRUD completo)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/peliculas` | Lista todas. Filtro opcional: `?titulo=texto` |
| GET | `/api/peliculas/{id}` | Consulta una por id |
| POST | `/api/peliculas` | Crea una película |
| PUT | `/api/peliculas/{id}` | Actualiza una película |
| DELETE | `/api/peliculas/{id}` | Elimina una película (409 si tiene funciones) |

### Funciones: `/api/funciones` (CRUD completo)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/funciones` | Lista todas. Filtro opcional: `?fecha=AAAA-MM-DD` |
| GET | `/api/funciones/{id}` | Consulta una por id (incluye su película) |
| POST | `/api/funciones` | Crea una función (se envía `peliculaId`) |
| PUT | `/api/funciones/{id}` | Actualiza una función |
| DELETE | `/api/funciones/{id}` | Elimina una función (409 si tiene compras) |

Ejemplo de cuerpo para crear una función:
```json
{
  "peliculaId": 1,
  "fecha": "2026-12-01",
  "hora": "18:30",
  "sala": "Sala 1",
  "formato": "2D",
  "idioma": "DOBLADA",
  "precio": 11000,
  "precioGeneralPlus": 13000,
  "cuposDisponibles": 60
}
```

### Compras: `/api/compras` (solo listar y crear, sin devoluciones)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/compras` | Historial completo. Filtros opcionales: `?desde=AAAA-MM-DD` (un día) y `&hasta=AAAA-MM-DD` (rango) |
| POST | `/api/compras` | Registra una compra |

Ejemplo de cuerpo para crear una compra:
```json
{
  "funcionId": 1,
  "nombreCliente": "Ana Ruiz",
  "cantidadBoletos": 2,
  "ubicacion": "GENERAL"
}
```
`ubicacion` puede ser `GENERAL` o `GENERAL_PLUS`.

## Reglas de negocio
1. **Cupos insuficientes:** si las boletas solicitadas superan los cupos disponibles de la función, la compra se
   rechaza con **409 Conflict** y no se modifica ningún dato.
2. **Compra exitosa:** se registra la compra, se calcula el valor total
   (`precio de la ubicación x cantidad + cargo por servicio de $1.500`) y se descuenta la cantidad de los cupos de la función.

## Códigos HTTP
| Código | Cuándo se usa |
|---|---|
| 200 OK | Operación realizada con éxito |
| 201 Created | Recurso creado |
| 400 Bad Request | Datos obligatorios faltantes o con formato incorrecto |
| 404 Not Found | Recurso no encontrado |
| 409 Conflict | Cupos insuficientes, o eliminación de un registro con datos asociados |

## Estructura del código
```
src/main/java/com/cinemax/backend/
├── controller/   Endpoints REST
├── service/      Lógica de negocio
├── repository/   Acceso a datos (Spring Data JPA)
├── model/        Entidades (Pelicula, Funcion, Compra)
├── dto/          Datos de entrada (FuncionRequest, CompraRequest)
├── exception/    Excepciones y manejo de errores
└── config/       Configuración de CORS para Angular (http://localhost:4200)
```
