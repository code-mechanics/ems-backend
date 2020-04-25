package com.ems.backend.controller;

import com.ems.backend.model.OrgModel;
import com.ems.backend.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/org", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrgController {
    private final OrgService orgService;

    @GetMapping("/{id}")
    public ResponseEntity<OrgModel> getOrgById(@PathVariable String id) {
        return ResponseEntity.ok(orgService.getOrg(id));
    }

    @GetMapping
    public ResponseEntity<List<OrgModel>> getAllOrg() {
        return ResponseEntity.ok(orgService.getAllOrg());
    }

    @PutMapping
    public ResponseEntity<OrgModel> updateOrg(@RequestBody OrgModel orgModel) {
        return ResponseEntity.accepted().body(orgService.updateOrg(orgModel));
    }

    @PostMapping
    public ResponseEntity<OrgModel> createOrg(@RequestBody OrgModel orgModel) {
        return new ResponseEntity<>(orgService.createOrg(orgModel), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteOrg(@PathVariable String id) {
        orgService.delete(id);
    }
}
