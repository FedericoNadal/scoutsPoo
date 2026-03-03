# 🏕️ ScoutsPoo — Sistema de Gestión Scout

API REST desarrollada en **Spring Boot** para la gestión de scouts, actividades, participaciones, sedes, grupos y comunidades. Incluye autenticación mediante **JWT** y control de acceso por roles.

---

## 🛠️ Tecnologías

- **Java 17+**
- **Spring Boot 3**
- **Spring Security** (JWT stateless)
- **Spring Data JPA / Hibernate**
- **Base de datos relacional** (configurable: H2, MySQL, PostgreSQL)
- **Maven**
- **jjwt** (JSON Web Tokens)
- **BCrypt** (hash de contraseñas)

---

## ⚙️ Configuración y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/scoutspoo.git
cd scoutspoo
```

### 2. Configurar `application.properties`

```properties
# Base de datos (ejemplo H2 en memoria para desarrollo)
spring.datasource.url=jdbc:h2:mem:scoutsdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop

# JWT
jwt.secret=TU_CLAVE_BASE64_DE_AL_MENOS_32_BYTES
jwt.expiration=86400000
```

> Para producción, reemplazar por una base de datos real (MySQL/PostgreSQL) y una clave secreta segura.

### 3. Correr el proyecto

```bash
mvn spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

---

## 🗃️ Modelo de entidades

```
Sede ─────────────┐
                  ↓
Grupo ──────→ Scout ←──── Usuario (1:1)
                  ↑
Comunidad ────────┘
                  ↓
           Participacion ←──── Actividad
```

| Entidad | Descripción |
|---|---|
| `Scout` | Entidad central. Tiene apodo único, nombre, apellido, graduación y pertenece a un grupo, una sede y una comunidad |
| `Usuario` | Cuenta de acceso al sistema. Está vinculada a un Scout (o puede existir sin él) |
| `Actividad` | Evento con descripción y fecha (siempre futura al momento de creación) |
| `Participacion` | Relación entre un Scout y una Actividad, con observaciones opcionales |
| `Sede` | Lugar físico con nombre, dirección, provincia y localidad |
| `Grupo` | Agrupación de scouts con denominación |
| `Comunidad` | Agrupa sedes y tiene una actividad principal definida |

---

## 🎓 Graduaciones disponibles

```
CASTOR | LOBATO | LOBEZNA | CAMINANTE | ROVER | EDUCADOR
```

---

## 🔐 Sistema de roles

El rol de un usuario se **deduce automáticamente** de la graduación del scout asociado. Si la graduación cambia, el rol se actualiza.

| Graduación del Scout | Rol asignado |
|---|---|
| CASTOR, LOBATO, LOBEZNA, CAMINANTE | `SCOUT` |
| ROVER | `ROVER` |
| EDUCADOR | `EDUCADOR` |

### Permisos por rol

| Recurso | SCOUT | ROVER | EDUCADOR |
|---|---|---|---|
| Ver scouts | ✅ | ✅ | ✅ |
| Crear / editar / eliminar scouts | ❌ | ❌ | ✅ |
| Ver actividades | ✅ | ✅ | ✅ |
| Ver "mis actividades" | ✅ | ✅ | ❌ |
| Crear / editar / eliminar actividades | ❌ | ✅ | ✅ |
| Gestionar participaciones | ❌ | ✅ | ✅ |
| Gestionar sedes, grupos, comunidades | ❌ | ❌ | ✅ |

---

## 🔑 Flujo de autenticación

### 1. Registrar usuario
```http
POST /usuarios/registro
Content-Type: application/json

{
  "username": "apodo_del_scout",
  "password": "contraseña"
}
```
> Si el `username` coincide con el apodo de un Scout existente, el rol se asigna automáticamente. De lo contrario, incluir el campo `"rol"`.

### 2. Iniciar sesión
```http
POST /auth/login
Content-Type: application/json

{
  "username": "apodo_del_scout",
  "password": "contraseña"
}
```
**Responde:** token JWT

### 3. Usar el token en cada request
```http
Authorization: Bearer <token>
```

---

## 📡 Endpoints

### 🏕️ Scouts `/scouts`

| Método | Ruta | Descripción | Rol |
|---|---|---|---|
| GET | `/scouts` | Listar todos los scouts activos | SCOUT, ROVER, EDUCADOR |
| GET | `/scouts/{id}` | Obtener scout por ID | SCOUT, ROVER, EDUCADOR |
| GET | `/scouts/apodo/{apodo}` | Obtener scout por apodo | SCOUT, ROVER, EDUCADOR |
| POST | `/scouts` | Crear scout | EDUCADOR |
| PUT | `/scouts/{id}` | Actualizar scout | EDUCADOR |
| DELETE | `/scouts/{id}` | Baja lógica (soft delete) | EDUCADOR |
| DELETE | `/scouts/{id}/hard` | Baja física (elimina participaciones) | EDUCADOR |

#### `POST /scouts`
```json
{
  "apodo": "string",
  "nombre": "string",
  "apellido": "string",
  "graduacion": "CASTOR | LOBATO | LOBEZNA | CAMINANTE | ROVER | EDUCADOR",
  "idSede": 1,
  "idGrupo": 1,
  "idComunidad": 1
}
```

#### `PUT /scouts/{id}` *(campos opcionales, no se puede modificar id ni apodo)*
```json
{
  "nombre": "string",
  "apellido": "string",
  "graduacion": "CASTOR | LOBATO | LOBEZNA | CAMINANTE | ROVER | EDUCADOR"
}
```

> ⚠️ Cambiar la graduación actualiza automáticamente el rol del usuario asociado.

---

### 📅 Actividades `/actividades`

| Método | Ruta | Descripción | Rol |
|---|---|---|---|
| GET | `/actividades` | Listar todas las actividades | SCOUT, ROVER, EDUCADOR |
| GET | `/actividades/{id}` | Obtener actividad por ID | SCOUT, ROVER, EDUCADOR |
| GET | `/actividades/misActividades` | Actividades del usuario autenticado | SCOUT, ROVER |
| GET | `/actividades/{id}/participaciones` | Participaciones de una actividad | SCOUT, ROVER, EDUCADOR |
| POST | `/actividades` | Crear actividad | ROVER, EDUCADOR |
| PUT | `/actividades/{id}` | Actualizar descripción | ROVER, EDUCADOR |
| DELETE | `/actividades/{id}` | Eliminar actividad | ROVER, EDUCADOR |

#### `POST /actividades`
```json
{
  "descripcion": "string",
  "fecha": "YYYY-MM-DD"
}
```
> ⚠️ La fecha debe ser **futura** al momento de la creación.

#### `PUT /actividades/{id}`
```json
{
  "descripcion": "string"
}
```

---

### 🤝 Participaciones `/participaciones`

| Método | Ruta | Descripción | Rol |
|---|---|---|---|
| GET | `/participaciones` | Listar todas | ROVER, EDUCADOR |
| GET | `/participaciones/{id}` | Obtener por ID | ROVER, EDUCADOR |
| GET | `/participaciones/scout/{scoutId}` | Participaciones de un scout (detalle completo) | ROVER, EDUCADOR |
| POST | `/participaciones` | Inscribir scout en actividad | ROVER, EDUCADOR |
| PUT | `/participaciones/{id}` | Actualizar observaciones | ROVER, EDUCADOR |
| DELETE | `/participaciones/{id}` | Eliminar participación | ROVER, EDUCADOR |

#### `POST /participaciones`
```json
{
  "scoutId": 1,
  "actividadId": 1,
  "observaciones": "string"
}
```
> ⚠️ Un scout no puede inscribirse dos veces en la misma actividad.

#### `PUT /participaciones/{id}`
```json
{
  "observaciones": "string"
}
```

---

### 🏢 Sedes `/sedes`

| Método | Ruta | Descripción | Rol |
|---|---|---|---|
| GET | `/sedes` | Listar todas | Autenticado |
| GET | `/sedes/{codigo}` | Obtener por código | Autenticado |
| POST | `/sedes` | Crear sede | EDUCADOR |
| PUT | `/sedes/{codigo}` | Actualizar sede | EDUCADOR |
| DELETE | `/sedes/{codigo}` | Eliminar sede | EDUCADOR |

#### `POST /sedes` y `PUT /sedes/{codigo}`
```json
{
  "nombre": "string",
  "direccion": "string",
  "provincia": "string",
  "localidad": "string"
}
```

---

### 👥 Grupos `/grupo`

| Método | Ruta | Descripción | Rol |
|---|---|---|---|
| GET | `/grupo` | Listar todos | EDUCADOR |
| GET | `/grupo/{id}` | Obtener por ID | EDUCADOR |
| POST | `/grupo` | Crear grupo | EDUCADOR |
| PUT | `/grupo/{id}` | Actualizar denominación | EDUCADOR |
| DELETE | `/grupo/{id}` | Eliminar grupo | EDUCADOR |

#### `POST /grupo`
```json
{
  "denominacion": "string"
}
```

#### `PUT /grupo/{id}`
```json
{
  "descripcion": "string"
}
```

---

### 🌐 Comunidades `/comunidades`

| Método | Ruta | Descripción | Rol |
|---|---|---|---|
| GET | `/comunidades` | Listar todas | EDUCADOR |
| GET | `/comunidades/{numero}` | Obtener por número | EDUCADOR |
| POST | `/comunidades` | Crear comunidad | EDUCADOR |
| PUT | `/comunidades/{numero}` | Actualizar actividad principal | EDUCADOR |
| DELETE | `/comunidades/{numero}` | Eliminar comunidad | EDUCADOR |
| POST | `/comunidades/{numero}/sedes/{codigoSede}` | Asociar sede a comunidad | EDUCADOR |
| DELETE | `/comunidades/{numero}/sedes/{codigoSede}` | Desasociar sede de comunidad | EDUCADOR |

#### `POST /comunidades` y `PUT /comunidades/{numero}`
```json
{
  "actividadPrincipal": "string"
}
```

> Los endpoints `POST` y `DELETE` de `/comunidades/{numero}/sedes/{codigoSede}` no requieren body, los IDs van en la URL.

---

### 🎓 Graduaciones `/graduaciones`

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/graduaciones` | Lista todos los valores del enum de graduaciones |

---

## 📌 Reglas de negocio destacadas

- Las **actividades** solo pueden crearse con fecha futura.
- Un scout **no puede participar dos veces** en la misma actividad.
- El **rol del usuario** se deduce y actualiza automáticamente según la graduación del scout.
- El **soft delete** de scouts marca `activo = false` sin eliminar datos; el **hard delete** elimina también todas sus participaciones.
- El endpoint `GET /actividades/misActividades` utiliza el token JWT para identificar al usuario y devolver únicamente sus actividades.

---

## 📁 Estructura del proyecto

```
src/main/java/edu/scoutsPoo/webApp/
├── controllers/      # Controladores REST
├── services/         # Lógica de negocio
├── repositories/     # Interfaces JPA
├── entities/         # Entidades JPA (Scout, Actividad, etc.)
├── DTOs/             # Objetos de transferencia de datos
└── security/         # Configuración JWT y Spring Security
```
