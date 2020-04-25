package com.ems.backend.repository;

import com.ems.backend.Entities.OrgEntity;
import com.ems.backend.model.OrgModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends MongoRepository<OrgEntity, String> {
}
