package com.blumbit.supermercado.service;

import com.blumbit.supermercado.dto.request.UsuarioRequest;
import com.blumbit.supermercado.dto.response.UsuarioResponse;
import com.blumbit.supermercado.entity.Usuario;
import com.blumbit.supermercado.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public UsuarioResponse findById(Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario == null) {
                throw new RuntimeException("No se encontro un usuario con el id ingresado");
            }
            return UsuarioResponse.fromEntity(usuario);
        } catch (Exception e) {
           throw e;
        }
    }

    @Override
    public List<UsuarioResponse> findAll() {
        try {
            //FORMA TRADICIONAL
            //  List<UsuarioResponse> usuariosResponse = new ArrayList();
            //  List<Usuario> usuarios = usuarioRepository.findAll();
            //  for (Usuario usuario : usuarios) {
            //     if(usuario.getNacionalidad().equals("Boliviano")){
            //         usuariosResponse.add(UsuarioResponse.fromEntity(usuario));
            //     }     
            //  }
            //  return usuariosResponse;
            //CON LAMBDAS Y STREAMS
            return usuarioRepository.findAll().stream()
                    .filter(usuario-> usuario.getNacionalidad().equals("Boliviano"))
                    .map(UsuarioResponse::fromEntity).collect(Collectors.toList());
        } catch (Exception e) {
             throw new RuntimeException("No se pudo recuperar los usuarios");
        }  
    }

    @Override
    public UsuarioResponse save(UsuarioRequest usuario) {
        try {
            Usuario usuarioToSave = UsuarioRequest.toEntity(usuario);

            //TODO add id to usuario;
            //TODO add password;
            return UsuarioResponse.fromEntity(usuarioRepository.save(usuarioToSave));
        } catch (Exception e) {
           throw new RuntimeException("Error al guardar el usuario");
        }
    }

    @Override
    public UsuarioResponse update(Long id, UsuarioRequest usuario) {
        try {
            Usuario usuarioRetrieved = usuarioRepository.findById(id).orElse(null);
            if (usuario == null) {
                throw new RuntimeException("No se encontro un usuario con el id ingresado");
            }
            return UsuarioResponse.fromEntity(usuarioRepository.save(usuarioRetrieved));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario");
        }
    }

}
