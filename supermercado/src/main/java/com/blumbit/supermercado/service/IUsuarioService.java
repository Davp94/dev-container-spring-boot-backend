package com.blumbit.supermercado.service;

import java.util.List;

import com.blumbit.supermercado.dto.request.UsuarioRequest;
import com.blumbit.supermercado.dto.response.UsuarioResponse;

public interface IUsuarioService {
    public UsuarioResponse findById(Long id);

    public List<UsuarioResponse> findAll() ;

    public UsuarioResponse save(UsuarioRequest usuario);

    public UsuarioResponse update(Long id, UsuarioRequest usuario);

    public void deleteById(Long id);
}
