
package com.ejercicio.biblioteca.servicios;

import com.ejercicio.biblioteca.entidades.Editorial;
import com.ejercicio.biblioteca.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    @Autowired
    EditorialRepositorio editorialRepositorio;
   
    @Transactional
    public void crearEditorial(String nombre) {
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
        
    }
    
    public List<Editorial> listarEditoriales() {
        
        List<Editorial> editoriales = new ArrayList<>();
        
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
        
    }
    
}
