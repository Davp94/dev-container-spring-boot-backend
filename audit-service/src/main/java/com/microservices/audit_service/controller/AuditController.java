package com.microservices.audit_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.audit_service.document.Audit;
import com.microservices.audit_service.service.AuditService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
@RequestMapping("/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<List<Audit>> getAllAudits(){
        try {
            List<Audit> audits = auditService.findAllAudits();
            return new ResponseEntity<>(audits, HttpStatus.OK);
        } catch (Exception e) {
            log.error("EXCEPTION", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{:id}")
    public ResponseEntity<List<Audit>> getAuditsByUser(@PathVariable String id) {
        try {
            List<Audit> audits = auditService.findByUserId(id);
            return new ResponseEntity<>(audits, HttpStatus.OK);
        } catch (Exception e) {
            log.error("EXCEPTION", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Audit> postMethodName(@RequestBody Audit audit) {
        try {
            Audit auditCreated = auditService.createAudit(audit);
            return new ResponseEntity<>(auditCreated, HttpStatus.OK);
        } catch (Exception e) {
            log.error("EXCEPTION", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
