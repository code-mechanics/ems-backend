package com.ems.backend.repository;

import com.ems.backend.Entities.GroupEntity;
import com.ems.backend.model.GroupStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends MongoRepository<GroupEntity, String>, QuerydslPredicateExecutor<GroupEntity> {
    Boolean existsByGroupId(String groupId);

    List<GroupEntity> findAllByUsersIn(String username);

    List<GroupEntity> findAllByUsersIs(String username);

    List<GroupEntity> findAllByUsersIsIn(String username);

    List<GroupEntity> findAllByUsersIsInAndStatus(String username, GroupStatus status);

    List<GroupEntity> findAllByUsersInAndStatus(String username, GroupStatus status);

    List<GroupEntity> findAllByUsersIsAndStatus(String username, GroupStatus status);
}
