package com.ems.backend.model;

import com.ems.backend.annotation.UniqueGroups;
import com.ems.backend.annotation.UniqueUsers;
import com.ems.backend.model.security.EmsRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseModel {
    @NotNull
    private String courseId;
    @Size(min = 1, message = "Course should be assigned to atleast groups")
    @UniqueGroups(min = 1)
    private List<String> groups;
    private Integer duraton;
    @Future
    private LocalDate opensAt;
    @Future
    private LocalDate closesAt;
    private CourseStatus status;
    private boolean hasShuffle;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    @UniqueUsers(userType = {EmsRole.ROLE_INSTRUCTOR , EmsRole.ROLE_ADMIN})
    private List<String> contributors;
    private String passCriteria;
    private Integer attempts;
    private boolean hasTimed;
    private String layout;

}
