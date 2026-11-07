
package com.iset.gestion_formations.Controller;

import com.iset.gestion_formations.model.Utilisateur;
import com.iset.gestion_formations.model.Role;
import com.iset.gestion_formations.repository.UtilisateurRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/utilisateurs")
@PreAuthorize("hasRole('ADMIN')")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurController(
            UtilisateurRepository utilisateurRepository,
            PasswordEncoder passwordEncoder) {

        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Afficher tous les utilisateurs
    @GetMapping
    public String liste(Model model) {

        model.addAttribute(
                "utilisateurs",
                utilisateurRepository.findAll()
        );

        return "utilisateurs/liste";
    }

    // 2. Afficher le formulaire d'ajout
    @GetMapping("/ajouter")
    public String formulaireAjout(Model model) {

        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("roles", Role.values());
        model.addAttribute("modeModification", false);

        return "utilisateurs/form";
    }

    // 3. Enregistrer un nouvel utilisateur
    @PostMapping("/ajouter")
    public String ajouter(
            @ModelAttribute Utilisateur utilisateur,
            RedirectAttributes redirectAttributes) {

        if (utilisateur.getUsername() == null ||
                utilisateur.getUsername().isBlank() ||
                utilisateur.getPassword() == null ||
                utilisateur.getPassword().isBlank() ||
                utilisateur.getRole() == null) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Veuillez remplir tous les champs."
            );

            return "redirect:/utilisateurs/ajouter";
        }

        String username = utilisateur.getUsername().trim();

        if (utilisateurRepository.existsByUsername(username)) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Ce nom d'utilisateur existe deja."
            );

            return "redirect:/utilisateurs/ajouter";
        }

        utilisateur.setId(null);
        utilisateur.setUsername(username);

        // Encoder le mot de passe avant stockage
        utilisateur.setPassword(
                passwordEncoder.encode(utilisateur.getPassword())
        );

        utilisateur.setEnabled(true);

        utilisateurRepository.save(utilisateur);

        redirectAttributes.addFlashAttribute(
                "succes",
                "Utilisateur ajoute avec succes."
        );

        return "redirect:/utilisateurs";
    }

    // 4. Afficher le formulaire de modification
    @GetMapping("/modifier/{id}")
    public String formulaireModification(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Utilisateur utilisateur = utilisateurRepository
                .findById(id)
                .orElse(null);

        if (utilisateur == null) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Utilisateur introuvable."
            );

            return "redirect:/utilisateurs";
        }

        // Ne jamais afficher le hash dans le formulaire
        utilisateur.setPassword("");

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("roles", Role.values());
        model.addAttribute("modeModification", true);

        return "utilisateurs/form";
    }

    // 5. Enregistrer les modifications
    @PostMapping("/modifier")
    public String modifier(
            @ModelAttribute Utilisateur formulaire,
            RedirectAttributes redirectAttributes) {

        if (formulaire.getId() == null) {
            return "redirect:/utilisateurs";
        }

        Utilisateur utilisateur = utilisateurRepository
                .findById(formulaire.getId())
                .orElse(null);

        if (utilisateur == null) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Utilisateur introuvable."
            );

            return "redirect:/utilisateurs";
        }

        if (formulaire.getUsername() == null ||
                formulaire.getUsername().isBlank() ||
                formulaire.getRole() == null) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Le nom et le role sont obligatoires."
            );

            return "redirect:/utilisateurs";
        }

        String username = formulaire.getUsername().trim();

        Utilisateur autre = utilisateurRepository
                .findByUsername(username)
                .orElse(null);

        if (autre != null &&
                !autre.getId().equals(utilisateur.getId())) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Ce nom d'utilisateur existe deja."
            );

            return "redirect:/utilisateurs";
        }

        utilisateur.setUsername(username);
        utilisateur.setRole(formulaire.getRole());
        utilisateur.setEnabled(formulaire.isEnabled());

        // Modifier le mot de passe seulement si fourni
        if (formulaire.getPassword() != null &&
                !formulaire.getPassword().isBlank()) {

            utilisateur.setPassword(
                    passwordEncoder.encode(formulaire.getPassword())
            );
        }

        utilisateurRepository.save(utilisateur);

        redirectAttributes.addFlashAttribute(
                "succes",
                "Utilisateur modifie avec succes."
        );

        return "redirect:/utilisateurs";
    }

    // 6. Supprimer un utilisateur
    @PostMapping("/supprimer/{id}")
    public String supprimer(
            @PathVariable Long id,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        Utilisateur utilisateur = utilisateurRepository
                .findById(id)
                .orElse(null);

        if (utilisateur == null) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Utilisateur introuvable."
            );

            return "redirect:/utilisateurs";
        }

        // Empêcher un administrateur de supprimer son compte
        if (utilisateur.getUsername().equals(
                authentication.getName())) {

            redirectAttributes.addFlashAttribute(
                    "erreur",
                    "Vous ne pouvez pas supprimer votre propre compte."
            );

            return "redirect:/utilisateurs";
        }

        utilisateurRepository.delete(utilisateur);

        redirectAttributes.addFlashAttribute(
                "succes",
                "Utilisateur supprime avec succes."
        );

        return "redirect:/utilisateurs";
    }
}
