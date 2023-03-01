package com.curso.ecommerce.cursoecomerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//Estamos mapeando la cadena administrador para en el navegador encuentre la dirección 
//del localhost principal
@RequestMapping("/administrador")
public class AdministradorController {

    //Redirección para el localhost 8085 hacia a la home
    @GetMapping("")
    public String home(){
        //Redirecciona al la vista principal
        return "administrador/home";
    }
    
}
