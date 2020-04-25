package com.ems.backend.repository;

import com.ems.backend.Entities.ResultEntity;
import com.ems.backend.model.ResultModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends MongoRepository<ResultEntity, String> {
}
