package com.ejercicio.biblioteca.controladores;

import com.ejercicio.biblioteca.exceptiones.MiException;
import com.ejercicio.biblioteca.servicios.LibroServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    private LibroServicio libroServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) {
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
        } catch (MiException ex) {
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "libro_form.html";
        }
        return "index.html";
    }
}
