package com.ems.backend.service;

import com.ems.backend.Entities.CourseEntity;
import com.ems.backend.exception.EntityNotFoundException;
import com.ems.backend.model.CourseModel;
import com.ems.backend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<CourseModel> getAllCourse() {
        return courseRepository.findAll()
                .stream()
                .map(course -> mapper.map(course, CourseModel.class))
                .collect(Collectors.toList());
    }

    public CourseModel getCourse(String id) {
        return courseRepository
                .findById(id)
                .map(group -> mapper.map(group, CourseModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Course with id:%s not found", id)));
    }

    public CourseModel createCourse(CourseModel courseModel) {
        return Optional.of(courseModel)
                .map(course -> mapper.map(course, CourseEntity.class))
                .map(courseEntity -> courseRepository.insert(courseEntity))
                .map(courseEntity -> mapper.map(courseEntity, CourseModel.class))
                .orElseThrow(() -> new EntityNotFoundException("Course was not able to create"));
    }

    public CourseModel updateCourse(CourseModel courseModel) {
        return Optional.of(courseModel)
                .map(CourseModel::getCourseId)
                .map(this::getCourse)
                .map(course -> mapper.map(course, CourseEntity.class))
                .map(courseEntity -> courseRepository.save(courseEntity))
                .map(courseEntity -> mapper.map(courseEntity, CourseModel.class))
                .orElse(courseModel);
    }

    public void delete(String id) {
        Optional.of(id)
                .map(this::getCourse)
                .map(CourseModel::getCourseId)
                .ifPresent(courseRepository::deleteById);
    }
}
