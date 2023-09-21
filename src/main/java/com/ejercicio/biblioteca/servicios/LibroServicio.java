package com.ejercicio.biblioteca.servicios;

import com.ejercicio.biblioteca.entidades.Autor;
import com.ejercicio.biblioteca.entidades.Editorial;
import com.ejercicio.biblioteca.entidades.Libro;
import com.ejercicio.biblioteca.repositorios.AutorRepositorio;
import com.ejercicio.biblioteca.repositorios.EditorialRepositorio;
import com.ejercicio.biblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    //Indica al servidor de aplicaciones que esta variable va ser inicializada por el.
    //Inyección de dependencias
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) {

        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibros() {
        
        List<Libro> libros = new ArrayList();
        
        libros = libroRepositorio.findAll();
        
        return libros;
    }
    
}
