package com.ems.backend.repository;

import com.ems.backend.Entities.ResultEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends MongoRepository<ResultEntity, String>, QuerydslPredicateExecutor<ResultEntity> {
}
