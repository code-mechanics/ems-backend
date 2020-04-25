package com.ems.backend.controller;

import com.ems.backend.model.GroupModel;
import com.ems.backend.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/group", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupModel> getGroupById(@PathVariable String id) {
        return ResponseEntity.ok(groupService.getGroup(id));
    }

    @GetMapping
    public ResponseEntity<List<GroupModel>> getAllGroup() {
        return ResponseEntity.ok(groupService.getAllGroup());
    }

    @PutMapping
    public ResponseEntity<GroupModel> updateGroup(@RequestBody GroupModel groupModel) {
        return ResponseEntity.accepted().body(groupService.updateGroup(groupModel));
    }

    @PostMapping
    public ResponseEntity<GroupModel> createGroup(@RequestBody GroupModel groupModel) {
        return new ResponseEntity<>(groupService.createGroup(groupModel), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable String id) {
        groupService.delete(id);
    }
}
