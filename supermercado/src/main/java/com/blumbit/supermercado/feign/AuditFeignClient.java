package com.blumbit.supermercado.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blumbit.supermercado.dto.feign.Audit;

@FeignClient(name = "audit-service", url = "${feign.client.url}")
public interface AuditFeignClient {

    @GetMapping(value = "/audit-service/audits")
    ResponseEntity<List<Audit>> getAllAudits();

    @PostMapping(value = "/audit-service/audits")
    ResponseEntity<Audit> createAudit(Audit audit);
}
