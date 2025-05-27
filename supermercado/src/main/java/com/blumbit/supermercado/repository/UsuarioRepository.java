package main.java.com.blumbit.supermercado.repository;

import java.util.Optional;

import main.java.com.blumbit.supermercado.entity.Usuario;

public interface UsuarioRepository extends ListCrudRepository<Usuario, Long> {
    
    Optional<Usuario> findByDocumentoIdentidad(String documentoIdentidad);

    @Query("SELECT u FROM Usuario u WHERE u.genero = 'FEMENINO'", nativeQuery = true)
    List<Usuario> findUsuariosCustom();
}

    
