package com.curso.ecommerce.cursoecomerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.cursoecomerce.model.Producto;
import com.curso.ecommerce.cursoecomerce.repository.ProductoRepository;

//Se implementa service para hacer llamado a los métodos
@Service
public class ProductoServiceImple implements ProductoService{

    //Autowired: Indica que ya estamos inyectando a esta clase un objeto
    @Autowired
    //Vamosa declarar un objeto de tipo Repository para que esa inteface nos permita obtener
    //los métodos crud que ya nos proporciana JPA Repostory
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        //De vuelve el método guardar
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        //De vuelve el método optional para obtener el id
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        //Cuando obtenemos el método save en actualizar, es cuando al objeto le mandamos un id null
        //este lo va crear pero cuando el objeto le mandamos con un id que ya existe en la base
        //entonces lo va actualizar
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        //De vuelve el método eliminar y obtene el id
        productoRepository.deleteById(id);
    }
    
    
}
