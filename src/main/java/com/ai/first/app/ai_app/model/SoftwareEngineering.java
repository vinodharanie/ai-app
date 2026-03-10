package com.ai.first.app.ai_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SoftwareEngineering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String techstack;
    @Column(columnDefinition="TEXT")
    private String learningPath;

    public SoftwareEngineering() {
    }

    public SoftwareEngineering(String name, String techstack, String learningPath) {
        this.name = name;
        this.techstack = techstack;
        this.learningPath = learningPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechstack() {
        return techstack;
    }

    public void setTechstack(String techstack) {
        this.techstack = techstack;
    }

    public String getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(String learningPath) {
        this.learningPath = learningPath;
    }
}
