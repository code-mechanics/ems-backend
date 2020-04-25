package com.ems.backend.repository;

import com.ems.backend.Entities.CourseEntity;
import com.ems.backend.model.CourseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<CourseEntity, String> {
}
