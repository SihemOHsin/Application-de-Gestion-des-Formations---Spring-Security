
package com.iset.gestion_formations.repository;

import com.iset.gestion_formations.model.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Rechercher un utilisateur par son username
    Optional<Utilisateur> findByUsername(String username);

    // Verifier si un username existe deja
    boolean existsByUsername(String username);

}
