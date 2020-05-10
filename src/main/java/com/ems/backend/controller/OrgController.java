package com.ems.backend.controller;

import com.ems.backend.model.OrgModel;
import com.ems.backend.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/org", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasRole('GROUP_ADMIN') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrgController {
    private final OrgService orgService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrgModel getOrgById(@PathVariable String id) {
        return orgService.getOrg(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrgModel> getAllOrg() {
        return orgService.getAllOrg();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrgModel updateOrg(@Valid @RequestBody OrgModel orgModel) {
        return orgService.updateOrg(orgModel);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrgModel createOrg(@Valid @RequestBody OrgModel orgModel) {
        return orgService.createOrg(orgModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrg(@PathVariable String id) {
        orgService.delete(id);
    }
}
