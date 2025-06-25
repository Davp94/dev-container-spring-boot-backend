package com.microservices.audit_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.audit_service.document.Audit;
import com.microservices.audit_service.repository.AuditRepository;

@Service
public class AuditServiceImpl implements AuditService{

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public List<Audit> findAllAudits() {
        return auditRepository.findAll();
    }

    @Override
    public Audit createAudit(Audit audit) {
       return auditRepository.save(audit);
    }

    @Override
    public List<Audit> findByUserId(String userId) {
        return auditRepository.findByUserId(userId);
    }

}
