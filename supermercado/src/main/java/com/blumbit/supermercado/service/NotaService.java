package com.blumbit.supermercado.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blumbit.supermercado.dto.request.MovimientoRequest;
import com.blumbit.supermercado.dto.request.NotaRequest;
import com.blumbit.supermercado.dto.response.nota.NotaResponse;
import com.blumbit.supermercado.entity.EntidadComercial;
import com.blumbit.supermercado.entity.Notas;
import com.blumbit.supermercado.entity.Usuario;
import com.blumbit.supermercado.repository.AlmacenProductoRepository;
import com.blumbit.supermercado.repository.MovimientoRepository;
import com.blumbit.supermercado.repository.NotaRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaService implements INotaService{
    private final NotaRepository notaRepository;
    private final MovimientoRepository movimientoRepository;
    private final AlmacenProductoRepository almacenProductoRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public NotaResponse createNota(NotaRequest notaRequest) {
        try {
            // validate total
            BigDecimal totalCalculado = calculateTotal(notaRequest.getMovimientos());
            if (totalCalculado != notaRequest.getTotalCalculado()) {
                throw new RuntimeException("Error al registrar la nota, los calculos no son correctos");
            }
            // CREATE NOTA
            Notas notaToCreate = NotaRequest.toEntity(notaRequest);
            notaToCreate.setFechaEmision(LocalDate.now());
            notaToCreate.setCodigoNota("null");
            notaToCreate.setEntidadComercial(entityManager.getReference(EntidadComercial.class, notaRequest.getEntidadComercialId()));
            notaToCreate.setUsuario(entityManager.getReference(Usuario.class, notaRequest.getUsuarioId()));

            Notas notaCreated = notaRepository.save(notaToCreate);

            // CREATE MOVIMIENTOS
            //validar stock
            for(MovimientoRequest movimientoRequest : notaRequest.getMovimientos()){
                
            }
            // UPDATE STOCK
        } catch (Exception e) {
            throw e;
        }

    }

    private BigDecimal calculateTotal(List<MovimientoRequest> movimientos){
        return new BigDecimal(0);
    }


}
