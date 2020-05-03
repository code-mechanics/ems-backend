package com.ems.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultModel {
    @NotNull
    private String resultId;
    @NotNull(message = "UserId shouldn't be null in result entity")
    private String userId;
    @NotNull(message = "GroupId shouldn't be null in result entity")
    private String groupId;
    @NotNull(message = "CourseId shouldn't be null in result entity")
    private String courseId;
    @Min(value = 1)
    private List<Answer> answers;
}
