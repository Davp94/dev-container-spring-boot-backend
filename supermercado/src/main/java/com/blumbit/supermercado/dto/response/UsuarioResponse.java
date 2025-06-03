package com.blumbit.supermercado.dto.response;
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
public class UsuarioResponse {
    private Integer id;
    private String email;
    private String fechaNacimiento;
    private String nombreCompleto;
    private String genero;
    private String telefono;
    private String direccion;
    private String dni;
    private String tipoDocumento;
    private String nacionalidad;

    public static UsuarioResponse fromEntity(Usuario usuario){
        return UsuarioResponse.builder()
                    .id(usuario.getId())
                    .email(usuario.getEmail())
                    .fechaNacimiento(usuario.getFechaNacimiento() != null ? usuario.getFechaNacimiento().toString() : null)
                    .nombreCompleto(usuario.getNombres()+" "+usuario.getApellidos())
                    .genero(usuario.getGenero())
                    .telefono(usuario.getTelefono())
                    .direccion(usuario.getDireccion())
                    .dni(usuario.getDocumentoIdentidad())
                    .tipoDocumento(usuario.getTipoDocumento())
                    .nacionalidad(usuario.getNacionalidad())
                    .build();
    }
    
}
