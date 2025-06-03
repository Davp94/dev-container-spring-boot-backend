package com.blumbit.supermercado.entity;
import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String email;

    private String password;

    private LocalDate fechaNacimiento;

    private String nombres;

    private String apellidos;

    private String genero;

    private String telefono;

    private String direccion;

    private String documentoIdentidad;

    private String tipoDocumento;

    private String nacionalidad;

}
