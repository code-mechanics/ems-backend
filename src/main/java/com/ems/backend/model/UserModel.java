package com.ems.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    @NotNull
    private String userId;
    @NotNull
    private String orgId;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private String photo;
    @NotNull
    private String userType;
}
