package com.blumbit.supermercado.service;

import com.blumbit.supermercado.common.dto.PageableRequest;
import com.blumbit.supermercado.common.dto.PageableResponse;
import com.blumbit.supermercado.dto.response.producto.ProductoFilterCriteria;
import com.blumbit.supermercado.dto.response.producto.ProductoResponse;

public interface IProductoService {
    PageableResponse<ProductoResponse> getProductsPagination(PageableRequest<ProductoFilterCriteria> pageableRequest);
}
