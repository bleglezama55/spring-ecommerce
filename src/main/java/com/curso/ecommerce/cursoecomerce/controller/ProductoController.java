package com.curso.ecommerce.cursoecomerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
///Estamos mapeando la cadena productos para en el navegador encuentre la dirección 
//del localhost principal
@RequestMapping("/productos")
public class ProductoController {
    
    //Redirección para el localhost 8085 hacia al show
    @GetMapping("")
    public String show(){
        //Redirecciona al la vista de productos
        return "productos/show";
    }
}
