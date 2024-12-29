package com.alura.literalura.service;

import com.alura.literalura.dto.GutendexResponse;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LiteraturaService {

    private final String BASE_URL = "https://gutendex.com/books";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LiteraturaService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        try {
            String encodedTitle = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String url = BASE_URL + "?search=" + encodedTitle;
            System.out.println("\nBuscando: " + titulo + "...");
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200 && response.body() != null && !response.body().isEmpty()) {
                GutendexResponse gutendexResponse = objectMapper.readValue(response.body(), GutendexResponse.class);
                if (gutendexResponse.getResults() != null && !gutendexResponse.getResults().isEmpty()) {
                    LibroDTO libroDTO = gutendexResponse.getResults().get(0);
                    Libro libro = convertirALibro(libroDTO);
                    System.out.println("¡Libro encontrado!");
                    System.out.println("Título: " + libro.getTitulo());
                    System.out.println("Autor: " + libro.getAutor().getNombre());
                    System.out.println("Idioma: " + libro.getIdioma());
                    System.out.println("Número de descargas: " + libro.getNumeroDescargas());
                    return libro;
                }
            }
            System.out.println("No se encontró el libro \"" + titulo + "\"");
        } catch (Exception e) {
            System.err.println("Error al buscar el libro: " + e.getMessage());
        }
        return null;
    }

    private Libro convertirALibro(LibroDTO dto) {
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitle());
        libro.setNumeroDescargas(dto.getNumeroDescargas());
        
        // Tomamos solo el primer idioma
        if (dto.getLanguages() != null && !dto.getLanguages().isEmpty()) {
            libro.setIdioma(dto.getLanguages().get(0));
        }

        // Tomamos solo el primer autor
        if (dto.getAuthors() != null && !dto.getAuthors().isEmpty()) {
            Autor autor = new Autor();
            autor.setNombre(dto.getAuthors().get(0).getName());
            autor.setAnioNacimiento(dto.getAuthors().get(0).getAnioNacimiento());
            autor.setAnioFallecimiento(dto.getAuthors().get(0).getAnioFallecimiento());
            libro.setAutor(autor);
        }

        return libro;
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    public Map<String, Long> obtenerEstadisticasPorIdioma() {
        List<Libro> libros = libroRepository.findAll();
        return libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getIdioma,
                        Collectors.counting()
                ));
    }
} 