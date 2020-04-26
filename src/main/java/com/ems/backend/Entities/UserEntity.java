package com.ems.backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@TypeAlias("user")
public class UserEntity {
    @Id
    private String userId;
    @Indexed
    private String orgId;
    private String userName;
    private String password;
    private String photo;
    private String userType;
}
