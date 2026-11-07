package com.iset.gestion_formations.repository;

import com.iset.gestion_formations.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
}