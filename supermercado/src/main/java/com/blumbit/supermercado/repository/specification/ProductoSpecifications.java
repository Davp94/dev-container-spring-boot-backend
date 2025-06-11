package com.blumbit.supermercado.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.blumbit.supermercado.dto.response.producto.ProductoFilterCriteria;
import com.blumbit.supermercado.entity.Categoria;
import com.blumbit.supermercado.entity.Producto;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class ProductoSpecifications {

    public static Specification<Producto> createSpecification(ProductoFilterCriteria criteria){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(criteria.getNombre() != null && !criteria.getNombre().trim().isEmpty()){
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nombre")), 
                    "%"+criteria.getNombre().toLowerCase()+"%"));
            }
            if(criteria.getDescripcion() != null && !criteria.getDescripcion().trim().isEmpty()){
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("descripcion")), 
                    "%"+criteria.getDescripcion().toLowerCase()+"%"));
            }
            if(criteria.getCodigoBarra() != null && !criteria.getCodigoBarra().trim().isEmpty()){
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("codigoBarra")), 
                    "%"+criteria.getCodigoBarra().toLowerCase()+"%"));
            }
            if(criteria.getMarca() != null && !criteria.getMarca().trim().isEmpty()){
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("marca")), 
                    "%"+criteria.getMarca().toLowerCase()+"%"));
            }

            if(criteria.getNombreCategoria() != null && !criteria.getNombreCategoria().trim().isEmpty()){
                Join<Producto, Categoria> categoriaJoin = root.join("Categoria");
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(categoriaJoin.get("nombre")), 
                    "%"+criteria.getNombreCategoria().toLowerCase()+"%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
