
package com.iset.gestion_formations.config;

import com.iset.gestion_formations.model.Role;
import com.iset.gestion_formations.model.Utilisateur;
import com.iset.gestion_formations.repository.UtilisateurRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner createAdmin(
            UtilisateurRepository utilisateurRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            String username = System.getenv("INITIAL_ADMIN_USERNAME");
            String password = System.getenv("INITIAL_ADMIN_PASSWORD");

            // Ne rien faire si les variables sont absentes
            if (username == null || username.isBlank()
                    || password == null || password.isBlank()) {
                return;
            }

            username = username.trim();

            // Ne pas recreer un compte existant
            if (utilisateurRepository.existsByUsername(username)) {
                System.out.println(
                    "Le compte administrateur existe deja."
                );
                return;
            }

            // Verifier la longueur du mot de passe
            if (password.length() < 12) {
                throw new IllegalArgumentException(
                    "Le mot de passe ADMIN doit contenir au moins 12 caracteres."
                );
            }

            // Encoder le mot de passe avec BCrypt
            String encodedPassword = passwordEncoder.encode(password);

            // Creer le compte administrateur
            Utilisateur admin = new Utilisateur();
            admin.setUsername(username);
            admin.setPassword(encodedPassword);
            admin.setRole(Role.ADMIN);
            admin.setEnabled(true);

            // Enregistrer dans MySQL
            utilisateurRepository.save(admin);

            System.out.println(
                "Compte administrateur cree avec succes : " + username
            );
        };
    }
}
