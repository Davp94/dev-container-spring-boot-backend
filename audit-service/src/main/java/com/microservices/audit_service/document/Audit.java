package com.microservices.audit_service.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "audits")
public class Audit {

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    private String action;

    private String resource;

    private String details;

    private LocalDateTime timestamp;

}
