package com.blumbit.supermercado.service;

import com.blumbit.supermercado.dto.request.NotaRequest;
import com.blumbit.supermercado.dto.response.nota.NotaResponse;

public interface INotaService {

    NotaResponse createNota(NotaRequest notaRequest);
}
