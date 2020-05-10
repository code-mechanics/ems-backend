package com.ems.backend.model;

import com.ems.backend.annotation.UniqueUsers;
import com.ems.backend.model.security.EmsRole;
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
    private String groupId;
    @NotNull(message = "Group name cannot be null")
    private String groupName;
    private String groupDescription;
    @UniqueUsers(userType = {EmsRole.ROLE_USER}, min = 1)
    private List<String> users;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    @UniqueUsers(userType = {EmsRole.ROLE_INSTRUCTOR , EmsRole.ROLE_ADMIN})
    private List<String> contributors;
    private GroupStatus status;

}
