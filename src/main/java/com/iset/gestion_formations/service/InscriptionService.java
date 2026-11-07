package com.iset.gestion_formations.service;

import com.iset.gestion_formations.model.Apprenant;
import com.iset.gestion_formations.model.Inscription;
import com.iset.gestion_formations.model.InscriptionId;
import com.iset.gestion_formations.model.SessionFormation;
import com.iset.gestion_formations.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findById(InscriptionId id) {
        return inscriptionRepository.findById(id);
    }

    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public void deleteById(InscriptionId id) {
        inscriptionRepository.deleteById(id);
    }

    public Inscription inscrire(
            Apprenant apprenant,
            SessionFormation sessionFormation,
            LocalDate dateInscription) {

        InscriptionId id = new InscriptionId(
                apprenant.getId(),
                sessionFormation.getId()
        );

        Inscription inscription = new Inscription(
                id,
                dateInscription,
                apprenant,
                sessionFormation
        );

        return inscriptionRepository.save(inscription);
    }
}
