package com.ems.backend.repository;

import com.ems.backend.Entities.QuestionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, String>, QuerydslPredicateExecutor<QuestionEntity> {
}
