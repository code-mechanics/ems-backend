package com.ems.backend.repository;

import com.ems.backend.Entities.QuestionEntity;
import com.ems.backend.model.QuestionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, String> {
}
