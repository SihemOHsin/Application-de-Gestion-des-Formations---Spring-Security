package com.iset.gestion_formations.service;

import com.iset.gestion_formations.model.ThemeFormation;
import com.iset.gestion_formations.repository.ThemeFormationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeFormationService {

    private final ThemeFormationRepository themeFormationRepository;

    public ThemeFormationService(ThemeFormationRepository themeFormationRepository) {
        this.themeFormationRepository = themeFormationRepository;
    }

    public List<ThemeFormation> findAll() {
        return themeFormationRepository.findAll();
    }

    public Optional<ThemeFormation> findById(Long id) {
        return themeFormationRepository.findById(id);
    }

    public ThemeFormation save(ThemeFormation themeFormation) {
        return themeFormationRepository.save(themeFormation);
    }

    public void deleteById(Long id) {
        themeFormationRepository.deleteById(id);
    }
}