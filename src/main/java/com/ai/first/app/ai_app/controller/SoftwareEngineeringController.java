package com.ai.first.app.ai_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.first.app.ai_app.model.SoftwareEngineering;
import com.ai.first.app.ai_app.service.SoftwareEngineeringService;

@RestController
@RequestMapping("/api/software-engineering")
public class SoftwareEngineeringController {

    private final SoftwareEngineeringService service;

    public SoftwareEngineeringController(SoftwareEngineeringService service) {
        this.service = service;
    }

    @GetMapping
    public List<SoftwareEngineering> all() {
        return service.getAllSoftwareEngineering();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoftwareEngineering> byId(@PathVariable Long id) {
        return service.getSoftwareEngineeringById(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SoftwareEngineering create(@RequestBody SoftwareEngineering se) {
        return service.createSoftwareEngineering(se);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoftwareEngineering> update(@PathVariable Long id,
                                                      @RequestBody SoftwareEngineering se) {
        return service.updateSoftwareEngineering(id, se)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteSoftwareEngineering(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

