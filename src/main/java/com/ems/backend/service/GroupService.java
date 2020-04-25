package com.ems.backend.service;

import com.ems.backend.Entities.GroupEntity;
import com.ems.backend.exception.EntityNotFoundException;
import com.ems.backend.model.GroupModel;
import com.ems.backend.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<GroupModel> getAllGroup() {
        return groupRepository.findAll()
                .stream()
                .map(course -> mapper.map(course, GroupModel.class))
                .collect(Collectors.toList());
    }

    public GroupModel getGroup(String id) {
        return groupRepository
                .findById(id)
                .map(group -> mapper.map(group, GroupModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Group with id:%s not found", id)));
    }

    public GroupModel createGroup(GroupModel groupModel) {
        return Optional.of(groupModel)
                .map(group -> mapper.map(group, GroupEntity.class))
                .map(groupEntity -> groupRepository.insert(groupEntity))
                .map(groupEntity -> mapper.map(groupEntity, GroupModel.class))
                .orElseThrow(() -> new EntityNotFoundException("Group was not able to create"));
    }

    public GroupModel updateGroup(GroupModel groupModel) {
        return Optional.of(groupModel)
                .map(GroupModel::getGroupId)
                .map(this::getGroup)
                .map(group -> mapper.map(group, GroupEntity.class))
                .map(groupEntity -> groupRepository.save(groupEntity))
                .map(groupEntity -> mapper.map(groupEntity, GroupModel.class))
                .orElse(groupModel);
    }

    public void delete(String id) {
        Optional.of(id)
                .map(this::getGroup)
                .map(GroupModel::getGroupId)
                .ifPresent(groupRepository::deleteById);
    }
}
