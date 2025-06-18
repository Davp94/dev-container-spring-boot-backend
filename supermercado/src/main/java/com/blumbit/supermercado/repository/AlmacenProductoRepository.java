package com.blumbit.supermercado.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.blumbit.supermercado.entity.AlmacenProducto;

public interface AlmacenProductoRepository extends ListCrudRepository<AlmacenProducto, Long>{
   
    Optional<AlmacenProducto> findByAlmacen_IdAndProducto_Id(Short idAlmacen, Long productoId);

}
