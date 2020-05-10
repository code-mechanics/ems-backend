package com.ems.backend.controller;

import com.ems.backend.model.QuestionModel;
import com.ems.backend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/question", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionModel getQuestionById(@PathVariable String id) {
        return questionService.getQuestion(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionModel> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public QuestionModel updateQuestion(@Valid @RequestBody QuestionModel questionModel) {
        return questionService.updateQuestion(questionModel);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionModel createQuestion(@Valid @RequestBody QuestionModel questionModel) {
        return questionService.createQuestion(questionModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestion(@PathVariable String id) {
        questionService.delete(id);
    }
}
