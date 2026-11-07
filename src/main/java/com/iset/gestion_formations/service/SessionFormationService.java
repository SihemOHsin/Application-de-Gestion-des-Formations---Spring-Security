package com.iset.gestion_formations.service;

import com.iset.gestion_formations.model.SessionFormation;
import com.iset.gestion_formations.repository.SessionFormationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SessionFormationService {

    private final SessionFormationRepository sessionFormationRepository;

    public SessionFormationService(SessionFormationRepository sessionFormationRepository) {
        this.sessionFormationRepository = sessionFormationRepository;
    }

    public List<SessionFormation> findAll() {
        return sessionFormationRepository.findAll();
    }

    public Optional<SessionFormation> findById(Long id) {
        return sessionFormationRepository.findById(id);
    }

    public SessionFormation save(SessionFormation sessionFormation) {
        return sessionFormationRepository.save(sessionFormation);
    }

    public void deleteById(Long id) {
        sessionFormationRepository.deleteById(id);
    }

    public List<SessionFormation> rechercherParThemeEtDates(
            Long themeId,
            LocalDate dateDebut,
            LocalDate dateFin) {

        return sessionFormationRepository.findAll()
                .stream()
                .filter(session ->
                        session.getThemeFormation() != null
                        && session.getThemeFormation().getId().equals(themeId)
                        && !session.getDateDebut().isBefore(dateDebut)
                        && !session.getDateFin().isAfter(dateFin))
                .toList();
    }
}