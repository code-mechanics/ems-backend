package com.ems.backend.model.security;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@TypeAlias("role")
@Data
@Builder
public class Role {
    @Id
    private String id;

    private EmsRole name;
}
