## 🐘 Cómo levantar la base de datos (PostgreSQL con Docker)

Esta sección explica paso a paso cómo iniciar la base de datos, incluso si nunca has usado Docker.

### 1. Requisito: tener Docker Desktop instalado

- Descárgarlo de la pagina oficial de docker
- Ábrelo y espera a que diga que está corriendo (ícono de la ballena en la barra de tareas, en verde/activo).

### 2. Levantar la base de datos

Parado en la raíz del proyecto (donde está el archivo `docker-compose.yml`, junto a `pom.xml`), corre:

```bash
docker compose up -d
```

- `up` = "levanta lo que está definido en `docker-compose.yml`".
- `-d` = "corre en segundo plano" (así no bloquea la terminal).

¡Se puede demorar un poco la primera vez!

Al terminar, Se deberia ver algo como:

```
[+] up 3/3
 ✔ Network lab-blueprints_default        Created
 ✔ Volume lab-blueprints_pgdata          Created
 ✔ Container blueprints-db               Started
```

### 3. Confirmar que quedó bien

```bash
docker ps
```

Busca la fila con `blueprints-db` en la columna `STATUS` debe decir `healthy` esperar unos 10-15 segundos si dice `starting`

Si quieres ver el detalle de que la base de datos se creó bien (tablas + datos de prueba cargados), revisa los logs:

```bash
docker logs blueprints-db
```

Se deberia ver líneas como `CREATE TABLE`, `INSERT 0 3`, `INSERT 0 10` 
eso confirma que el script `db/init.sql` corrió y cargó los datos de ejemplo 
(3 blueprints con sus puntos).

### 4. Correr la aplicación

Con la base de datos ya levantada, corre la app normalmente:

```bash
mvn spring-boot:run
```

Y prueba con:

```bash
curl http://localhost:8080/blueprints
```

Se deberian ver los 3 blueprints de ejemplo (`john/house`, `john/garage`, `jane/garden`) viniendo de PostgreSQL.

### 5. Apagar la base de datos (cuando termines de trabajar)

```bash
docker compose down
```

Esto apaga y borra el contenedor, conservando los datos guardados (quedan en un volumen de Docker) 
la próxima vez que hagas `docker compose up -d`, los vas a seguir teniendo.

### Si necesitas reiniciar todo desde cero

Si algo queda en mal estado (por ejemplo, cambiaste la contraseña o el usuario en `docker-compose.yml` y 
ya habías levantado el contenedor antes), borra también los datos guardados con:

```bash
docker compose down -v
docker compose up -d
```

El `-v` borra el volumen de datos, así que la próxima vez que levantes el contenedor, 
vuelve a correr `db/init.sql` desde cero (tablas + datos de prueba nuevos).

### Datos de conexión 

| Parámetro | Valor |
|---|---|
| Host | `localhost` |
| Puerto | `5433` |
| Base de datos | `blueprints` |
| Usuario | `blueprints` |
| Contraseña | `blueprints` |

> Nota: el puerto es `5433` y no el `5432` estándar de Postgres para evitar choques por puerto