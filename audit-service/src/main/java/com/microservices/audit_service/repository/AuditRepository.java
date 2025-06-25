package com.microservices.audit_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.audit_service.document.Audit;
import java.util.List;

@Repository
public interface AuditRepository extends MongoRepository<Audit, String>{

    //@Query("{user_id}: {userId}")
    List<Audit> findByUserId(String userId);
}
