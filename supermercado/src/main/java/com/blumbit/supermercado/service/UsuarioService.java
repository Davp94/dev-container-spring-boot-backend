package com.blumbit.supermercado.service;

import com.blumbit.supermercado.entity.Usuario;
import com.blumbit.supermercado.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements IUsuarioService{
    private final UsuarioRepository usuarioRepository;
    // private final RolRepository rolRepository;
    private final EntityManager entityManager;

    public UsuarioService(UsuarioRepository usuarioRepository, EntityManager entityManager) {
        this.usuarioRepository = usuarioRepository;
        this.entityManager = entityManager;
    }


    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }


    @Override
    public List<Usuario> findAll() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al devolver los datos");
            // TODO: handle exception
        }    
    }


    @Override
    public Usuario save(Usuario usuario) {
        // for() (Rol r : usuario.getRoles()) {
        //     if (r.getId() == null) {
        //         throw new IllegalArgumentException("El rol debe tener un ID asignado");
        //     }
        // }
        // rolRepository.save(rol);
        return usuarioRepository.save(usuario);
    }


    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
