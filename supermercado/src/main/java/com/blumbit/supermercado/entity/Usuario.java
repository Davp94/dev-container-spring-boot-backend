package main.java.com.blumbit.supermercado.entity;
import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Usuario {

    @Id
    private Long id;

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
