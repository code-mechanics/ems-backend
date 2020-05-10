package com.ems.backend.controller;

import com.ems.backend.model.GroupModel;
import com.ems.backend.model.GroupStatus;
import com.ems.backend.service.GroupService;
import com.ems.backend.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/group", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupModel getGroupById(@PathVariable String id) {
        return groupService.getGroup(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupModel> getAllGroup() {
        return groupService.getAllGroup();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GroupModel updateGroup(@Valid @RequestBody GroupModel groupModel) {
        return groupService.updateGroup(groupModel);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel createGroup(@Valid @RequestBody GroupModel groupModel) {
        groupModel.setStatus(GroupStatus.ACTIVE);
        return groupService.createGroup(groupModel);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable String id) {
        groupService.delete(id);
    }

    @GetMapping(value = "getUserGroups")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN') or hasRole('USER')")
    public List<GroupModel> getUserGroups(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return groupService.getUserGroup(userDetails);
    }
}
