package com.iset.gestion_formations.service;

import com.iset.gestion_formations.model.Formateur;
import com.iset.gestion_formations.repository.FormateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormateurService {

    private final FormateurRepository formateurRepository;

    public FormateurService(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }

    public List<Formateur> findAll() {
        return formateurRepository.findAll();
    }

    public Optional<Formateur> findById(Long id) {
        return formateurRepository.findById(id);
    }

    public Formateur save(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public void deleteById(Long id) {
        formateurRepository.deleteById(id);
    }
}
