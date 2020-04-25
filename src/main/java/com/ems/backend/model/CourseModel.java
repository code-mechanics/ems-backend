package com.ems.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseModel {
    @NotNull
    private String courseId;
    @Min(value = 1)
    private List<GroupModel> groups;
    private Integer duraton;
    private LocalDate opensAt;
    private LocalDate closesAt;
    private CourseStatus status;
    private boolean hasShuffle;
    private LocalDate createdAt;
    private String userId;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    private List<UserModel> contributors;
    private String passCriteria;
    private Integer attempts;
    private boolean hasTimed;
    private String layout;

}
