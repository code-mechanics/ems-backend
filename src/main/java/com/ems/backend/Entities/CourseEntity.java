package com.ems.backend.Entities;

import com.ems.backend.model.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@TypeAlias("course")
public class CourseEntity {
    @Indexed
    private String courseId;
    @TextIndexed
    private String courseName;
    private List<GroupEntity> groups;
    private Integer duraton;
    private LocalDate opensAt;
    private LocalDate closesAt;
    private CourseStatus status;
    private boolean hasShuffle;
    private LocalDate createdAt;
    @Indexed
    private String userId;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    private List<UserEntity> contributors;
    private String passCriteria;
    private Integer attempts;
    private boolean hasTimed;
    private String layout;

}
