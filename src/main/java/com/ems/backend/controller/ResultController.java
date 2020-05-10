package com.ems.backend.controller;

import com.ems.backend.model.ResultModel;
import com.ems.backend.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/result", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResultController {
    private final ResultService resultService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultModel getResultById(@PathVariable String id) {
        return resultService.getResult(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResultModel> getAllResult() {
        return resultService.getAllResult();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResultModel updateResult(@Valid @RequestBody ResultModel resultModel) {
        return resultService.updateResult(resultModel);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResultModel createResult(@Valid @RequestBody ResultModel resultModel) {
        return resultService.createResult(resultModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteResult(@PathVariable String id) {
        resultService.delete(id);
    }
}
