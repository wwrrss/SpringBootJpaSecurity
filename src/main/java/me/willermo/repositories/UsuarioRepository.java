/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.willermo.repositories;

import me.willermo.models.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author william
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    public Usuario findByNombre(String nombre);
}
