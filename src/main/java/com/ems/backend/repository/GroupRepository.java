package com.ems.backend.repository;

import com.ems.backend.Entities.GroupEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<GroupEntity, String> {
    Boolean existsByGroupId(String groupId);
}
