package com.blumbit.supermercado.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.blumbit.supermercado.entity.Producto;

public interface ProductoPaginationRepository extends PagingAndSortingRepository<Producto, Long>{

}
