package com.ems.backend.controller;

import com.ems.backend.model.ResultModel;
import com.ems.backend.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/result", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @GetMapping("/{id}")
    public ResponseEntity<ResultModel> getResultById(@PathVariable String id) {
        return ResponseEntity.ok(resultService.getResult(id));
    }

    @GetMapping
    public ResponseEntity<List<ResultModel>> getAllResult() {
        return ResponseEntity.ok(resultService.getAllResult());
    }

    @PutMapping
    public ResponseEntity<ResultModel> updateResult(@RequestBody ResultModel resultModel) {
        return ResponseEntity.accepted().body(resultService.updateResult(resultModel));
    }

    @PostMapping
    public ResponseEntity<ResultModel> createResult(@RequestBody ResultModel resultModel) {
        return new ResponseEntity<>(resultService.createResult(resultModel), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable String id) {
        resultService.delete(id);
    }
}
