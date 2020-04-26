package com.ems.backend.controller;

import com.ems.backend.model.CourseModel;
import com.ems.backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/course", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseModel> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourse(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseModel>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseModel> updateCourse(@RequestBody CourseModel courseModel) {
        return ResponseEntity.accepted().body(courseService.updateCourse(courseModel));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseModel> createCourse(@RequestBody CourseModel courseModel) {
        return new ResponseEntity<>(courseService.createCourse(courseModel), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.delete(id);
    }
}
