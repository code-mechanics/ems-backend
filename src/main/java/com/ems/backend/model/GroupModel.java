package com.ems.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupModel {
    @NotNull
    private String groupId;
    @NotNull
    private String groupName;
    private String groupDescription;
    private List<UserModel> users;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    private List<UserModel> contributors;

}
