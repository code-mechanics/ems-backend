package com.ems.backend.service;

import com.ems.backend.Entities.OrgEntity;
import com.ems.backend.exception.EntityNotFoundException;
import com.ems.backend.model.OrgModel;
import com.ems.backend.repository.OrgRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrgService {
    private final OrgRepository orgRepository;
    private final ModelMapper mapper = new ModelMapper();


    public List<OrgModel> getAllOrg() {
        return orgRepository.findAll()
                .stream()
                .map(course -> mapper.map(course, OrgModel.class))
                .collect(Collectors.toList());
    }

    public OrgModel getOrg(String id) {
        return orgRepository
                .findById(id)
                .map(org -> mapper.map(org, OrgModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Organization with id:%s not found", id)));
    }

    public OrgModel createOrg(OrgModel orgModel) {
        return Optional.of(orgModel)
                .map(org -> mapper.map(orgModel, OrgEntity.class))
                .map(orgEntity -> orgRepository.insert(orgEntity))
                .map(orgEntity -> mapper.map(orgEntity, OrgModel.class))
                .orElseThrow(() -> new EntityNotFoundException("Organization was not able to create"));
    }

    public OrgModel updateOrg(OrgModel orgModel) {
        return Optional.of(orgModel)
                .map(OrgModel::getOrgId)
                .map(this::getOrg)
                .map(org -> mapper.map(org, OrgEntity.class))
                .map(orgEntity -> orgRepository.save(orgEntity))
                .map(entity -> mapper.map(entity, OrgModel.class))
                .orElse(orgModel);
    }

    public void delete(String id) {
        Optional.of(id)
                .map(this::getOrg)
                .map(OrgModel::getOrgId)
                .ifPresent(orgRepository::deleteById);
    }
}
