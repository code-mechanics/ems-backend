package com.ems.backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@TypeAlias("result")
public class ResultEntity {
    @Indexed
    private String resultId;
    @Indexed
    private String userId;
    @Indexed
    private String groupId;
    @Indexed
    private String courseId;
    private List<Answer> answers;
}
