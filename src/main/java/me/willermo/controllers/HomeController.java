/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.willermo.controllers;

import me.willermo.models.Producto;
import me.willermo.models.Usuario;
import me.willermo.repositories.ProductoRepository;
import me.willermo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author william
 */
@RestController
public class HomeController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @GetMapping(value = "/")
    public String home(){
        return "home";
    }
    @GetMapping(value = "/usuarios")
    public Iterable<Usuario> allUsuarios(){
        return usuarioRepository.findAll(); 
    }
    @GetMapping(value = "/usuario")
    public Usuario guardarUsuario(){
        Usuario u = new Usuario();
        u.setNombre("prueba");
        u.setPassword("asd");
        u = usuarioRepository.save(u);
        return u;
    }
    //buscar producto por ID
    @GetMapping(value = "/productos/{idProducto}")
    @PreAuthorize("hasRole('ROLE_USER') AND @authorizationService.isUserProduct(authentication,#idProducto)")
    public Producto productoById(@PathVariable(name = "idProducto") Long idProducto){
        return productoRepository.findOne(idProducto);
    }
}

