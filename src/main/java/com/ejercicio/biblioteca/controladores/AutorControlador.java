
package com.ejercicio.biblioteca.controladores;

import com.ejercicio.biblioteca.exceptiones.MiException;
import com.ejercicio.biblioteca.servicios.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        try {
            autorServicio.crearAutor(nombre);
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }
        System.out.println("Nombre: "+nombre);
        return "index.html";
    }
    
    
    
}
