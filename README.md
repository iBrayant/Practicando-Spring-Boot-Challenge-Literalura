# LiterAlura

LiterAlura es una aplicación de consola Java que permite buscar y gestionar información sobre libros del Proyecto Gutenberg utilizando su API pública (Gutendex).

## Características

- Búsqueda de libros por título
- Almacenamiento de libros y autores en base de datos PostgreSQL
- Visualización de libros guardados
- Listado de autores
- Búsqueda de autores por año
- Estadísticas de libros por idioma

## Requisitos Previos

- Java 17 o superior
- Maven
- PostgreSQL 17.2
- Base de datos 'literalura' creada en PostgreSQL

## Configuración

1. **Base de Datos**
   ```sql
   CREATE DATABASE literalura;
   ```

2. **Configuración de la Base de Datos**
   - Archivo: `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=postgres
   spring.datasource.password=tu_contraseña
   ```

## Instalación

1. Clonar el repositorio
2. Configurar la base de datos PostgreSQL
3. Actualizar las credenciales en `application.properties`
4. Ejecutar:
   ```bash
   mvn clean install
   ```

## Uso

Para iniciar la aplicación:
```bash
mvn spring-boot:run
```

### Menú Principal

1. **Buscar y guardar libro por título**
   - Busca libros en el Proyecto Gutenberg
   - Guarda la información en la base de datos

2. **Ver todos los libros**
   - Muestra los libros guardados
   - Incluye título, autor, idioma y descargas

3. **Ver todos los autores**
   - Lista los autores guardados
   - Muestra años de nacimiento y fallecimiento

4. **Buscar autores vivos en un año específico**
   - Encuentra autores que vivían en un año determinado

5. **Ver estadísticas de idiomas**
   - Muestra distribución de libros por idioma

## Tecnologías Utilizadas

- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Maven
- Gutendex API

## Estructura del Proyecto

```
src/main/java/com/alura/literalura/
├── LiterAluraApplication.java
├── dto/
│   ├── AutorDTO.java
│   ├── LibroDTO.java
│   └── GutendexResponse.java
├── model/
│   ├── Autor.java
│   └── Libro.java
├── repository/
│   ├── AutorRepository.java
│   └── LibroRepository.java
└── service/
    └── LiteraturaService.java
```

## Contribución

1. Fork el repositorio
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. 