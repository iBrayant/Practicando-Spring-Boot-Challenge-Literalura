# LiterAlura

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white)
![Licencia](https://img.shields.io/badge/licencia-MIT-green)

Aplicación de consola en Java que busca libros en el catálogo del Proyecto Gutenberg a través de la API pública [Gutendex](https://gutendex.com/), los guarda en PostgreSQL y permite consultar los libros, los autores y estadísticas por idioma.

> Challenge **LiterAlura** del programa Oracle Next Education (Alura Latam), Back-end con Spring Boot.

<!-- TODO: captura de la consola con el menú (guardar en docs/img/) -->
<!-- ![Menú](docs/img/menu.png) -->

## Características

- **Búsqueda por título** en la API de Gutendex y guardado del libro con su autor en la base de datos.
- **Listado de libros guardados:** título, autor, idioma y número de descargas.
- **Listado de autores** con sus años de nacimiento y fallecimiento.
- **Autores vivos en un año:** consulta JPQL con parámetros sobre los autores guardados.
- **Estadísticas por idioma:** cantidad de libros guardados de cada idioma.

## Stack

Java 17 · Spring Boot 3.2.3 · Spring Data JPA · PostgreSQL · `java.net.http.HttpClient` · Jackson · Lombok · Maven

## Cómo funciona

```
Menú de consola → LiteraturaService → API Gutendex (HttpClient + Jackson → DTOs)
                         │
                         └→ Repositorios JPA → PostgreSQL (libros y autores)
```

## Estructura

```
src/main/java/com/alura/literalura/
├── LiterAluraApplication.java   -> menú de consola (CommandLineRunner)
├── service/LiteraturaService    -> consulta a Gutendex y lógica de negocio
├── dto/                         -> respuesta de la API (GutendexResponse, LibroDTO, AutorDTO)
├── model/                       -> entidades Libro y Autor
└── repository/                  -> repositorios Spring Data JPA
```

## Cómo correrlo

Requisitos: Java 17+, Maven y PostgreSQL.

1. Crea la base de datos:

   ```sql
   CREATE DATABASE literalura;
   ```

2. Define las variables de entorno. La contraseña no tiene valor por defecto:

   ```bash
   export DB_PASSWORD="tu_contraseña"
   # Opcionales (valores por defecto entre paréntesis)
   export DB_URL="jdbc:postgresql://localhost:5432/literalura"   # (ese mismo)
   export DB_USER="postgres"                                      # (postgres)
   ```

3. Ejecuta:

   ```bash
   mvn spring-boot:run
   ```

Las tablas se crean automáticamente al arrancar.

## Menú

```
1. Buscar y guardar libro por título
2. Ver todos los libros
3. Ver todos los autores
4. Buscar autores vivos en un año específico
5. Ver estadísticas de idiomas
6. Salir
```

## Autor

**Brayant Vásquez Gómez** · [GitHub](https://github.com/iBrayant) · [LinkedIn](https://www.linkedin.com/in/brayant-v%C3%A1squez-g%C3%B3mez-4b483a1a1)

Licencia [MIT](LICENSE).
