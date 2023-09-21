
package com.ejercicio.biblioteca.servicios;

import com.ejercicio.biblioteca.entidades.Autor;
import com.ejercicio.biblioteca.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) {
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
     
        autorRepositorio.save(autor);
    }
    
    public List<Autor> listarAutores() {
        
        List<Autor> autores = new ArrayList<>();
        
        autores = autorRepositorio.findAll();
        
        return autores;
    }
    
}
