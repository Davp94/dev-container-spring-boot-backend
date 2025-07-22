package com.blumbit.supermercado.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blumbit.supermercado.common.dto.CustomResponse;
import com.blumbit.supermercado.dto.request.PermisoRequest;
import com.blumbit.supermercado.dto.request.RolRequest;
import com.blumbit.supermercado.dto.response.PermisoResponse;
import com.blumbit.supermercado.dto.response.RolResponse;
import com.blumbit.supermercado.service.IRolService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final IRolService rolService;

    @GetMapping
    public CustomResponse<List<RolResponse>> getRoles() {
        try {
            return CustomResponse.success(rolService.findAllRoles());
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping
    public CustomResponse<RolResponse> createRol(@RequestBody RolRequest rolRequest) {
        try {
            return CustomResponse.success(rolService.createRol(rolRequest));
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/permiso")
    public CustomResponse<PermisoResponse> createPermiso(@RequestBody PermisoRequest permisoRequest) {
        try {
            return CustomResponse.success(rolService.createPermiso(permisoRequest));
        } catch (Exception e) {
            throw e;
        }
    }
    
}
