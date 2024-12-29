package com.alura.literalura;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.service.LiteraturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    private final LiteraturaService literaturaService;
    private final Scanner scanner;

    @Autowired
    public LiterAluraApplication(LiteraturaService literaturaService) {
        this.literaturaService = literaturaService;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    buscarYGuardarLibro();
                    break;
                case 2:
                    mostrarTodosLosLibros();
                    break;
                case 3:
                    mostrarTodosLosAutores();
                    break;
                case 4:
                    buscarAutoresVivosEnAnio();
                    break;
                case 5:
                    mostrarEstadisticasIdiomas();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Por favor, intente nuevamente.");
            }
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n=== LiterAlura - Menú Principal ===");
        System.out.println("1. Buscar y guardar libro por título");
        System.out.println("2. Ver todos los libros");
        System.out.println("3. Ver todos los autores");
        System.out.println("4. Buscar autores vivos en un año específico");
        System.out.println("5. Ver estadísticas de idiomas");
        System.out.println("6. Salir");
    }

    private int obtenerOpcion() {
        System.out.print("Seleccione una opción: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void buscarYGuardarLibro() {
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("\nIngrese el título del libro a buscar: ");
        String titulo = scanner.nextLine();
        
        Libro libro = literaturaService.buscarLibroPorTitulo(titulo);
        if (libro != null) {
            literaturaService.guardarLibro(libro);
            System.out.println("\n¡Libro guardado exitosamente!");
        }
        System.out.println();
    }

    private void mostrarTodosLosLibros() {
        System.out.println("\n=== Libros Guardados ===");
        List<Libro> libros = literaturaService.obtenerTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados aún.");
        } else {
            for (Libro libro : libros) {
                System.out.println("\nTítulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Número de descargas: " + libro.getNumeroDescargas());
            }
        }
        System.out.println();
    }

    private void mostrarTodosLosAutores() {
        List<Autor> autores = literaturaService.obtenerTodosLosAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores guardados");
            return;
        }

        System.out.println("\n=== Autores Guardados ===");
        autores.forEach(autor -> {
            System.out.println("\nNombre: " + autor.getNombre());
            System.out.println("Año de nacimiento: " + 
                (autor.getAnioNacimiento() != null ? autor.getAnioNacimiento() : "Desconocido"));
            System.out.println("Año de fallecimiento: " + 
                (autor.getAnioFallecimiento() != null ? autor.getAnioFallecimiento() : "Desconocido"));
        });
    }

    private void buscarAutoresVivosEnAnio() {
        System.out.print("Ingrese el año a consultar: ");
        int anio = scanner.nextInt();
        
        List<Autor> autoresVivos = literaturaService.obtenerAutoresVivosEnAnio(anio);
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
            return;
        }

        System.out.println("\n=== Autores vivos en el año " + anio + " ===");
        autoresVivos.forEach(autor -> System.out.println("- " + autor.getNombre()));
    }

    private void mostrarEstadisticasIdiomas() {
        Map<String, Long> estadisticas = literaturaService.obtenerEstadisticasPorIdioma();
        if (estadisticas.isEmpty()) {
            System.out.println("No hay estadísticas disponibles");
            return;
        }

        System.out.println("\n=== Estadísticas por Idioma ===");
        estadisticas.forEach((idioma, cantidad) -> 
            System.out.println(idioma + ": " + cantidad + " libro(s)"));
    }
} 