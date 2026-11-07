package com.iset.gestion_formations.repository;

import com.iset.gestion_formations.model.Inscription;
import com.iset.gestion_formations.model.InscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

// on a @EmbeddedId
// private InscriptionId id; donc n'utilise pas long
public interface InscriptionRepository
        extends JpaRepository<Inscription, InscriptionId> {
}
