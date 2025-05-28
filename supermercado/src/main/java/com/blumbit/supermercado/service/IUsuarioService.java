package com.blumbit.supermercado.service;

import java.util.List;
import java.util.Optional;

import com.blumbit.supermercado.entity.Usuario;

public interface IUsuarioService {
 public Optional<Usuario> findById(Long id);

    public List<Usuario> findAll() ;

    public Usuario save(Usuario usuario);

    public void deleteById(Long id);
}
