package com.iset.gestion_formations.repository;

import com.iset.gestion_formations.model.SessionFormation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionFormationRepository
        extends JpaRepository<SessionFormation, Long> {
}