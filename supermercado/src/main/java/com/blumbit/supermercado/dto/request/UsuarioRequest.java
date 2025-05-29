package com.blumbit.supermercado.dto.request;

import java.time.LocalDate;

import com.blumbit.supermercado.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioRequest {

    private String email;

    private String fechaNacimiento;

    private String nombres;

    private String apellidos;

    private String genero;

    private String telefono;

    private String direccion;

    private String dni;

    private String tipoDocumento;

    private String nacionalidad;

    public static Usuario toEntity(UsuarioRequest usuarioRequest){
        return Usuario.builder()
                .email(usuarioRequest.getEmail())
                .fechaNacimiento(LocalDate.parse(usuarioRequest.getFechaNacimiento()))
                .nombres(usuarioRequest.getNombres())
                .apellidos(usuarioRequest.getApellidos())
                .genero(usuarioRequest.getGenero())
                .telefono(usuarioRequest.getTelefono())
                .direccion(usuarioRequest.getDireccion())
                .documentoIdentidad(usuarioRequest.getDni())
                .tipoDocumento(usuarioRequest.getTipoDocumento())
                .nacionalidad(usuarioRequest.getNacionalidad())
                .build();
    }

}
