package com.blumbit.supermercado.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.blumbit.supermercado.entity.Usuario;



public interface UsuarioRepository extends ListCrudRepository<Usuario, Long> {
    
    // Optional<Usuario> findByDocumentoIdentidad(String documentoIdentidad);

    // @Query("SELECT u FROM Usuario u WHERE u.genero = 'FEMENINO'", nativeQuery = true)
    // List<Usuario> findUsuariosCustom();
}

    
