package com.ems.backend.controller;

import com.ems.backend.model.QuestionModel;
import com.ems.backend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.DomainEvents;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/question", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionModel> getQuestionById(@PathVariable String id) {
        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @GetMapping
    public ResponseEntity<List<QuestionModel>> getAllQuestion() {
        return ResponseEntity.ok(questionService.getAllQuestion());
    }

    @PutMapping
    public ResponseEntity<QuestionModel> updateQuestion(@RequestBody QuestionModel questionModel) {
        return ResponseEntity.accepted().body(questionService.updateQuestion(questionModel));
    }

    @PostMapping
    public ResponseEntity<QuestionModel> createQuestion(@RequestBody QuestionModel questionModel) {
        return new ResponseEntity<>(questionService.createQuestion(questionModel), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable String id) {
        questionService.delete(id);
    }
}
