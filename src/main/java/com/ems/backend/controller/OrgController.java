package com.ems.backend.controller;

import com.ems.backend.model.OrgModel;
import com.ems.backend.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/org", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('GROUP_ADMIN') or hasRole('ADMIN')")
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

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OrgModel> updateOrg(@Valid @RequestBody OrgModel orgModel) {
        return ResponseEntity.accepted().body(orgService.updateOrg(orgModel));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OrgModel> createOrg(@Valid @RequestBody OrgModel orgModel) {
        return new ResponseEntity<>(orgService.createOrg(orgModel), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteOrg(@PathVariable String id) {
        orgService.delete(id);
    }
}
