package com.iset.gestion_formations.repository;

import com.iset.gestion_formations.model.ThemeFormation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeFormationRepository extends JpaRepository<ThemeFormation, Long> {
}