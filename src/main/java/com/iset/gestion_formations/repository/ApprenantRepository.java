package com.iset.gestion_formations.repository;


import com.iset.gestion_formations.model.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
}