package com.ai.first.app.ai_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ai.first.app.ai_app.client.AIClient;
import com.ai.first.app.ai_app.model.SoftwareEngineering;
import com.ai.first.app.ai_app.repository.SoftwareEngineeringRepository;

@Service
public class SoftwareEngineeringService {

    private final SoftwareEngineeringRepository repository;
    private final AIClient aIClient;

    public SoftwareEngineeringService(SoftwareEngineeringRepository repository, AIClient aIClient) {
        this.repository = repository;
        this.aIClient = aIClient;
    }

    public List<SoftwareEngineering> getAllSoftwareEngineering() {
        return repository.findAll();
    }

    public Optional<SoftwareEngineering> getSoftwareEngineeringById(Long id) {
        return repository.findById(id);
    }

    public SoftwareEngineering createSoftwareEngineering(SoftwareEngineering softwareEngineering) {
        // Generate learning path using AI service
        String prompt = "Based on the programming tech stack " + softwareEngineering.getTechstack() + 
                        " that " + softwareEngineering.getName() + 
                        " has given, provide a full learning path for this person";
        String learningPath = aIClient.chat(prompt);
        softwareEngineering.setLearningPath(learningPath);
        return repository.save(softwareEngineering);
    }

    public Optional<SoftwareEngineering> updateSoftwareEngineering(Long id, SoftwareEngineering softwareEngineeringDetails) {
        return repository.findById(id).map(existing -> {
            existing.setName(softwareEngineeringDetails.getName());
            existing.setTechstack(softwareEngineeringDetails.getTechstack());
            
            // Generate learning path using AI service if techstack is updated
            if (!existing.getTechstack().equals(softwareEngineeringDetails.getTechstack())) {
                String prompt = "Based on the programming tech stack " + softwareEngineeringDetails.getTechstack() + 
                                " that " + softwareEngineeringDetails.getName() + 
                                " has given, provide a full learning path for this person";
                String learningPath = aIClient.chat(prompt);
                existing.setLearningPath(learningPath);
            } else {
                existing.setLearningPath(softwareEngineeringDetails.getLearningPath());
            }
            return repository.save(existing);
        });
    }

    public boolean deleteSoftwareEngineering(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
