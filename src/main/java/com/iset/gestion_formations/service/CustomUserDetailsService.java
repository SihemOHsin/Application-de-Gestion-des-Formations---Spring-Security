
package com.iset.gestion_formations.service;

import com.iset.gestion_formations.model.Utilisateur;
import com.iset.gestion_formations.repository.UtilisateurRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    // Injection du Repository
    public CustomUserDetailsService(
            UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // Charger un utilisateur depuis MySQL
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // Rechercher le compte dans la base de donnees
        Utilisateur utilisateur = utilisateurRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Utilisateur introuvable : " + username
                ));

        // Convertir notre utilisateur JPA en UserDetails
        return User.builder()
                .username(utilisateur.getUsername())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRole().name())
                .disabled(!utilisateur.isEnabled())
                .build();
    }
}
