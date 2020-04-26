package com.ems.backend.Entities;

import com.ems.backend.model.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@TypeAlias("question")
public class QuestionEntity {
    @Id
    private String questionId;
    private List<String> options;
    private QuestionType questionType;
    //TODO : This may be an array.
    private String answer;
    @Indexed
    private String courseId;
    private String questionText;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate lastModifiedAt;
    private LocalDate lastModifiedBy;
    private String explanation;
}
