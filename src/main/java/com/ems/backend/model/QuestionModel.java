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
public class QuestionModel {
    @NotNull
    private String questionId;
    @Min(value = 1)
    private List<String> options;
    private QuestionType questionType;
    //TODO : This may be an array.
    private String answer;
    @NotNull
    private String courseId;
    @NotNull
    private String questionText;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate lastModifiedAt;
    private LocalDate lastModifiedBy;
    private String explanation;
}
