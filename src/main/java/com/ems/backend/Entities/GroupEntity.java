package com.ems.backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
@TypeAlias("groups")
public class GroupEntity {
    @Id
    private String groupId;
    @TextIndexed
    private String groupName;
    private String groupDescription;
    private List<UserEntity> users;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate lastModifiedAt;
    private String lastModifiedBy;
    private List<UserEntity> contributors;

}
