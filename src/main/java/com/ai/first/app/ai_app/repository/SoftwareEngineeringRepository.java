package com.ai.first.app.ai_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai.first.app.ai_app.model.SoftwareEngineering;

@Repository
public interface SoftwareEngineeringRepository extends JpaRepository<SoftwareEngineering, Long> {
}
