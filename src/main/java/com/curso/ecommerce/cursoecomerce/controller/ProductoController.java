package com.curso.ecommerce.cursoecomerce.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    //Variable de objeto de la clase ProductoService para acceder a todos los métodos
    private ProductoService productoService;

    //Redirección para el localhost 8085 hacia al show
    @GetMapping("")
    //el objeto model obtienen info desde el backend hacia a la vista para obtener los productos
    public String show(Model model){
        //Obtenemos desde el objeto modelo el complemento producto service para obtener los productos
        //hacia a la vista show
        model.addAttribute("productos", productoService.findAll());
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
        //Mensaje por consola
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

    //Metodo que va a mapear la info desde el boton editar para que se edite en la BD
    //Pasamos por parametro el id del producto
    //PathVariable: va a mapear la variable  en la url y pasa a la variable a la contigua 
    //cognotación del id
    @GetMapping("/edit/{id}") 
    public String edit(@PathVariable Integer id, Model model){
        //PAsamos el objeto producto
        Producto producto = new Producto();
        //obtenemos la variable optionalProducto ya que nos devuelve la busqueada de un objeto
        //tipo producto
        Optional<Producto> optionalProducto = productoService.get(id);
        //obtenemos el producto que lo va a mandar a buscar
        producto = optionalProducto.get();
        //Manda mensaje por consola
        LOGGER.info("Producto buscado: {}",producto);
        //Nos va enviar a la vista todo el objeto que lo ha buscado
        model.addAttribute("producto", producto);
        //Redireccionamose a la vista edit
        return "productos/edit";
    }

    //Redirección para el localhost 8085 hacia la vista update
    @PostMapping("/update")
    public String update(Producto producto){
        //Le pasamos el metodo update para que nos obtenga el objeto producto y la info 
        productoService.update(producto);
        //Que nos redireccione a la vista productos
        return "redirect:/productos";
    }

    //Metodo que va a mapear la info desde el boton eliminar para que se edite en la BD
    //Pasamos por parametro el id del producto
    //PathVariable: va a mapear la variable id en la url
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        //Le pasamos el metodo delete para que nos obtenga el objeto producto y elimine
        productoService.delete(id);
        //Que nos redireccione a la vista productos
        return "redirect:/productos";
    }
}
