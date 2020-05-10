package com.ems.backend.controller;

import com.ems.backend.model.CourseModel;
import com.ems.backend.service.CourseService;
import com.ems.backend.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/course", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseModel getCourseById(@PathVariable String id) {
        return courseService.getCourse(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseModel> getAllCourses() {
        return courseService.getAllCourse();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CourseModel updateCourse(@Valid @RequestBody CourseModel courseModel) {
        return courseService.updateCourse(courseModel);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CourseModel createCourse(@Valid @RequestBody CourseModel courseModel) {
        return courseService.createCourse(courseModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable String id) {
        courseService.delete(id);
    }

    @GetMapping("/{getCourses}")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseModel> getCourses(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return courseService.getUserCourses(userDetails);
    }
}
