package com.ems.backend.service;

import com.ems.backend.Entities.ResultEntity;
import com.ems.backend.exception.EntityNotFoundException;
import com.ems.backend.model.ResultModel;
import com.ems.backend.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<ResultModel> getAllResult() {
        return resultRepository.findAll()
                .stream()
                .map(course -> mapper.map(course, ResultModel.class))
                .collect(Collectors.toList());
    }

    public ResultModel getResult(String id) {
        return resultRepository
                .findById(id)
                .map(group -> mapper.map(group, ResultModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Result with id:%s not found", id)));
    }

    public ResultModel createResult(ResultModel resultModel) {
        return Optional.of(resultModel)
                .map(result -> mapper.map(result, ResultEntity.class))
                .map(resultEntity -> resultRepository.insert(resultEntity))
                .map(resultEntity -> mapper.map(resultEntity, ResultModel.class))
                .orElseThrow(() -> new EntityNotFoundException("Result was not able to create"));
    }

    public ResultModel updateResult(ResultModel orgModel) {
        return Optional.of(orgModel)
                .map(ResultModel::getResultId)
                .map(this::getResult)
                .map(result -> mapper.map(result, ResultEntity.class))
                .map(resultEntity -> resultRepository.save(resultEntity))
                .map(resultEntity -> mapper.map(resultEntity, ResultModel.class))
                .orElse(orgModel);
    }

    public void delete(String id) {
        Optional.of(id)
                .map(this::getResult)
                .map(ResultModel::getResultId)
                .ifPresent(resultRepository::deleteById);
    }
}
