package com.ems.backend.service;

import com.ems.backend.Entities.CourseEntity;
import com.ems.backend.Entities.GroupEntity;
import com.ems.backend.exception.EntityNotFoundException;
import com.ems.backend.model.CourseModel;
import com.ems.backend.model.GroupStatus;
import com.ems.backend.repository.CourseRepository;
import com.ems.backend.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<CourseModel> getAllCourse() {
        return courseRepository.findAll()
                .stream()
                .map(course -> mapper.map(course, CourseModel.class))
                .collect(Collectors.toList());
    }

    public CourseModel getCourse(String id) {
        return courseRepository
                .findById(id)
                .map(group -> mapper.map(group, CourseModel.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Course with id:%s not found", id)));
    }

    public CourseModel createCourse(CourseModel courseModel) {
        return Optional.of(courseModel)
                .map(course -> mapper.map(course, CourseEntity.class))
                .map(courseEntity -> courseRepository.insert(courseEntity))
                .map(courseEntity -> mapper.map(courseEntity, CourseModel.class))
                .orElseThrow(() -> new EntityNotFoundException("Course was not able to create"));
    }

    public CourseModel updateCourse(CourseModel courseModel) {
        return Optional.of(courseModel)
                .map(CourseModel::getCourseId)
                .map(this::getCourse)
                .map(course -> mapper.map(course, CourseEntity.class))
                .map(courseEntity -> courseRepository.save(courseEntity))
                .map(courseEntity -> mapper.map(courseEntity, CourseModel.class))
                .orElse(courseModel);
    }

    public void delete(String id) {
        Optional.of(id)
                .map(this::getCourse)
                .map(CourseModel::getCourseId)
                .ifPresent(courseRepository::deleteById);
    }

    public List<CourseModel> getUserCourses(UserDetailsImpl userDetails) {
//        QCourseEntity query = new QCourseEntity("query"); //we can give any name
//        BooleanExpression inStockFilter = query.deliveryInfo.inStock.isTrue();
//        Predicate smallDeliveryFeeFilter = query.deliveryInfo.deliveryFee.lt(400);
//        Predicate hasGreatReviews = query.reviews.any().rating.eq(10);
//
//        Predicate bestBuysFilter = inStockFilter.and(smallDeliveryFeeFilter).and(hasGreatReviews);
//
//        return (List<LegoSet>)legoRepository.findAll(bestBuysFilter);
        String username = userDetails.getEmail();
        List<GroupEntity> activeUserGroup = groupRepository.findAllByUsersInAndStatus(username, GroupStatus.ACTIVE);
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setGroups(activeUserGroup
                .stream()
                .map(GroupEntity::getGroupId)
                .collect(Collectors.toList()));

        List<CourseEntity> all = courseRepository.findAll(Example.of(courseEntity, ExampleMatcher.matchingAny()));
        Type listType = new TypeToken<List<CourseModel>>(){}.getType();
        return mapper.map(all, listType);
    }
}
