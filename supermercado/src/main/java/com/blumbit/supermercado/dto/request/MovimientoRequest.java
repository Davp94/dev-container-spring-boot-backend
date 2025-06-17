package com.blumbit.supermercado.dto.request;

import java.math.BigDecimal;

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
public class MovimientoRequest {
    private Long notaId;
    private Short almacenId;
    private Long productoId;
    private Integer cantidad;
    //TODO create enum
    private String tipoMovimiento;
    private BigDecimal precioUnitarioCompra;
    private BigDecimal precioUnitarioVenta;
    private String observaciones;
}
