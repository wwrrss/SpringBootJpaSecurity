/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.willermo.services;

import me.willermo.models.Producto;
import me.willermo.models.Usuario;
import me.willermo.repositories.ProductoRepository;
import me.willermo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author william
 */
@Service
public class AuthorizationService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public boolean isUserProduct(Authentication authentication,Long idProducto){
        Usuario usuario = usuarioRepository.findByNombre(authentication.getName());
        if(usuario==null){
            return false;
        }
        Producto producto = productoRepository.findOne(idProducto);
        if(producto==null){
            return false;
        }
        return usuario.getEmpresa().getId().equals(producto.getEmpresa().getId());
    }
}
