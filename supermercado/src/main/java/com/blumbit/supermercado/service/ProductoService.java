package com.blumbit.supermercado.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.blumbit.supermercado.common.dto.PageableRequest;
import com.blumbit.supermercado.common.dto.PageableResponse;
import com.blumbit.supermercado.dto.response.producto.ProductoFilterCriteria;
import com.blumbit.supermercado.dto.response.producto.ProductoResponse;
import com.blumbit.supermercado.entity.Producto;
import com.blumbit.supermercado.repository.ProductoPaginationRepository;
import com.blumbit.supermercado.repository.ProductoRepository;
import com.blumbit.supermercado.repository.specification.ProductoSpecifications;

@Service
public class ProductoService implements IProductoService{

    private final ProductoPaginationRepository productoPaginationRepository;

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoPaginationRepository productoPaginationRepository,
            ProductoRepository productoRepository) {
        this.productoPaginationRepository = productoPaginationRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public PageableResponse<ProductoResponse> getProductsPagination(
            PageableRequest<ProductoFilterCriteria> pageableRequest) {
        
        Sort sort = pageableRequest.getSortOrder().equalsIgnoreCase("desc")
                ? Sort.by(pageableRequest.getSortField()).descending()
                : Sort.by(pageableRequest.getSortField()).ascending();
        Pageable pageable = PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize(), sort);
        Specification<Producto> spec= Specification.where(null);
        if(pageableRequest.getCriterials() != null){
            spec = ProductoSpecifications.createSpecification(pageableRequest.getCriterials());
        }
        Page<Producto> productPage = productoPaginationRepository.findAll(spec, pageable);

        return PageableResponse.<ProductoResponse>builder()
            .pageNumber(productPage.getNumber())
            .totalElements(productPage.getTotalElements())
            .pageSize(productPage.getSize())
            .totalPages(productPage.getTotalPages())
            .content(productPage.getContent().stream().map(ProductoResponse::fromEntity).collect(Collectors.toList())).build();  
    }

}
