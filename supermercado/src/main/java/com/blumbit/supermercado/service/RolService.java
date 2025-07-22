package com.blumbit.supermercado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blumbit.supermercado.dto.request.PermisoRequest;
import com.blumbit.supermercado.dto.request.RolRequest;
import com.blumbit.supermercado.dto.response.PermisoResponse;
import com.blumbit.supermercado.dto.response.RolResponse;
import com.blumbit.supermercado.entity.PermisoRol;
import com.blumbit.supermercado.entity.Rol;
import com.blumbit.supermercado.repository.PermisoRepository;
import com.blumbit.supermercado.repository.PermisoRolRepository;
import com.blumbit.supermercado.repository.RolRepository;

@Service
public class RolService implements IRolService{

    private final RolRepository rolRepository;

    private final PermisoRolRepository permisoRolRepository;

    private final PermisoRepository permisoRepository;

    public RolService(RolRepository rolRepository, PermisoRolRepository permisoRolRepository, PermisoRepository permisoRepository) {
        this.rolRepository = rolRepository;
        this.permisoRolRepository = permisoRolRepository;
        this.permisoRepository = permisoRepository;
    }

    @Override
    public List<RolResponse> findAllRoles() {
        return rolRepository.findAllByEstado((short)1).stream().map(RolResponse::fromEntity).collect(Collectors.toList());
    }

    @Override
    public RolResponse findRolById(Short id) {
        try {
            RolResponse rolResponse = RolResponse.fromEntity(rolRepository.findById(id).orElseThrow(()->new RuntimeException("NO se encuentra el rol con el id proporcionado")));       
            rolResponse.setPermisosIds(permisoRolRepository.findByRol_Id(id).stream()
            .map(permisoRol->permisoRol.getPermiso().getId()).collect(Collectors.toList()));
            return rolResponse;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    @Transactional
    public RolResponse createRol(RolRequest rolRequest) {
        try {
            Rol rolToCreate = RolRequest.toEntity(rolRequest);
            Rol rolCreated = rolRepository.save(rolToCreate);
            for(Integer idPermiso : rolRequest.getPermisosIds()){
                permisoRolRepository.save(PermisoRol.builder()
                .permiso(permisoRepository.findById(idPermiso).orElse(null))
                .rol(rolCreated)
                .build());
            }
            return RolResponse.fromEntity(rolCreated);  
        } catch (Exception e) {
           throw new RuntimeException("Error al crear el rol");
        }
    }

    @Override
    public RolResponse updateRol(Short id, RolRequest rolRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRol'");
    }

    @Override
    public void deleteRol(Short id) {
        try {
            //BORRADO LOGICO
            Rol rolRetrieved = rolRepository.findById(id).orElseThrow(()->new RuntimeException("No se encuentra el rol con el identificador solicitado"));
            rolRetrieved.setEstado((short) 0);
            rolRepository.save(rolRetrieved);
        } catch (Exception e) {
            throw e;
        }  
    }

    @Override
    public PermisoResponse createPermiso(PermisoRequest permisoRequest) {
       return PermisoResponse.fromEntity(permisoRepository.save(PermisoRequest.toEntity(permisoRequest)));
    }

}
