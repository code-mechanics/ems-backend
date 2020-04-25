package com.ems.backend.service;

import com.ems.backend.Entities.UserEntity;
import com.ems.backend.exception.EntityNotFoundException;
import com.ems.backend.model.UserModel;
import com.ems.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<UserModel> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(course -> mapper.map(course, UserModel.class))
                .collect(Collectors.toList());
    }

    public UserModel getUser(String id) {
        return userRepository
                .findById(id)
                .map(group -> mapper.map(group, UserModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id:%s not found", id)));
    }

    public UserModel createUser(UserModel userModel) {
        return Optional.of(userModel)
                .map(user -> mapper.map(user, UserEntity.class))
                .map(userEntity -> userRepository.insert(userEntity))
                .map(userEntity -> mapper.map(userEntity, UserModel.class))
                .orElseThrow(() -> new EntityNotFoundException("Result was not able to create"));
    }

    public UserModel updateUser(UserModel userModel) {
        return Optional.of(userModel)
                .map(UserModel::getUserId)
                .map(this::getUser)
                .map(org -> mapper.map(org, UserEntity.class))
                .map(userEntity -> userRepository.save(userEntity))
                .map(entity -> mapper.map(entity, UserModel.class))
                .orElse(userModel);
    }

    public void delete(String id) {
        Optional.of(id)
                .map(this::getUser)
                .map(UserModel::getUserId)
                .ifPresent(userRepository::deleteById);
    }
}
