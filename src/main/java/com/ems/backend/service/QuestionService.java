package com.ems.backend.service;

import com.ems.backend.Entities.QuestionEntity;
import com.ems.backend.exception.EntityNotFoundException;
import com.ems.backend.model.QuestionModel;
import com.ems.backend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<QuestionModel> getAllQuestion() {
        return questionRepository.findAll()
                .stream()
                .map(course -> mapper.map(course, QuestionModel.class))
                .collect(Collectors.toList());
    }

    public QuestionModel getQuestion(String id) {
        return questionRepository
                .findById(id)
                .map(group -> mapper.map(group, QuestionModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Question with id:%s not found", id)));
    }

    public QuestionModel createQuestion(QuestionModel questionModel) {
        return Optional.of(questionModel)
                .map(question -> mapper.map(question, QuestionEntity.class))
                .map(questionEntity -> questionRepository.insert(questionEntity))
                .map(questionEntity -> mapper.map(questionEntity, QuestionModel.class))
                .orElseThrow(() -> new EntityNotFoundException("Question was not able to create"));
    }

    public QuestionModel updateQuestion(QuestionModel questionModel) {
        return Optional.of(questionModel)
                .map(QuestionModel::getQuestionId)
                .map(this::getQuestion)
                .map(questionEntity -> mapper.map(questionEntity, QuestionEntity.class))
                .map(questionEntity -> questionRepository.save(questionEntity))
                .map(questionEntity -> mapper.map(questionEntity, QuestionModel.class))
                .orElse(questionModel);
    }

    public void delete(String id) {
        Optional.of(id)
                .map(this::getQuestion)
                .map(QuestionModel::getQuestionId)
                .ifPresent(questionRepository::deleteById);
    }
}
