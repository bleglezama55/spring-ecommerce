package com.curso.ecommerce.cursoecomerce.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.cursoecomerce.model.Producto;
import com.curso.ecommerce.cursoecomerce.model.Usuario;
import com.curso.ecommerce.cursoecomerce.service.ProductoService;

@Controller
//Estamos mapeando la cadena productos para en el navegador encuentre la dirección 
//del localhost principal
@RequestMapping("/productos")
public class ProductoController {

    //Logger: Nos va hacer un chequeo de lo que esta ejecutandose dentro del programa
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    // anotación que permite inyectar unas dependencias con otras dentro de Spring
    //como una mega factoria de objetos. Cada clase se registra para instanciar objetos con 
    //alguna de las anotaciones @Controller ,@Service ,@repository o @RestController.
    @Autowired
    //Variable service para acceder a todos los métodos
    private ProductoService productoService;

    //Redirección para el localhost 8085 hacia al show
    @GetMapping("")
    public String show(){
        //Redirecciona al la vista de productos
        return "productos/show";
    }

    //Redirección para el localhost 8085 hacia al create
    @GetMapping("/create")
    public String create(){
        //Redirecciona al la vista de create
        return "productos/create";
    } 

    //Metodo que va a mapear la info desde el boton guardar para que se guarde en la BD
    //Pasamos por parametro objecto producto
    @PostMapping("/save")
    public String save(Producto producto) {
        //Mensaje
        LOGGER.info("Este es el producto {}",producto);
        //Va a crear un objeto usuario para darle valor al id del usuario y lo demas en vacio
        Usuario User = new Usuario(1,"","","","","","","");
        //Va colocar ese objeto usuario para que el producto sea creado 
        producto.setUsuario(User);
        //El producto del servicio va a guardar el producto
        productoService.save(producto);
        //Redireccionamose a la vista show
        return "redirect:/productos";
    }
}
