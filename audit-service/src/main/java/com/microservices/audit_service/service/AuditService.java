package com.microservices.audit_service.service;

import java.util.List;
import java.util.Optional;

import com.microservices.audit_service.document.Audit;

public interface AuditService {

    List<Audit> findAllAudits();

    Audit createAudit(Audit audit);

    List<Audit> findByUserId(String userId);
}
