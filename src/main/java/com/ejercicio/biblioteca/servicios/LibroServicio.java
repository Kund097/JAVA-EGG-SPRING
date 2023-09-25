package com.ejercicio.biblioteca.servicios;

import com.ejercicio.biblioteca.entidades.Autor;
import com.ejercicio.biblioteca.entidades.Editorial;
import com.ejercicio.biblioteca.entidades.Libro;
import com.ejercicio.biblioteca.exceptiones.MiException;
import com.ejercicio.biblioteca.repositorios.AutorRepositorio;
import com.ejercicio.biblioteca.repositorios.EditorialRepositorio;
import com.ejercicio.biblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

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

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        //Es un objeto contenedor que puede o no contener un valor no nulo
        //Si el valor esta presente devuelve true
        Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {

            editorial = respuestaEditorial.get();
        }
        if (respuestaLibro.isPresent()) {

            Libro libro = respuestaLibro.get();

            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libroRepositorio.save(libro);
        }
    }

    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        if (isbn == null) {
            throw new MiException("El isbn no puede ser nulo");
        }

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede ser vacio o nulo");
        }
        
        if (ejemplares == null) {
            throw new MiException("Los ejemplares no pueden ser nulos");
        }
        
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El idAutor no puede ser vacio o nulo");
        }
        
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("El idEditorial no puede ser vacio o nulo");
        }
    }

}
