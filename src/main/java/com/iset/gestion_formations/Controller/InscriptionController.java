package com.iset.gestion_formations.Controller;


import com.iset.gestion_formations.model.Apprenant;
import com.iset.gestion_formations.model.InscriptionId;
import com.iset.gestion_formations.model.SessionFormation;
import com.iset.gestion_formations.service.ApprenantService;
import com.iset.gestion_formations.service.InscriptionService;
import com.iset.gestion_formations.service.SessionFormationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/inscriptions")
public class InscriptionController {

    private final InscriptionService inscriptionService;
    private final ApprenantService apprenantService;
    private final SessionFormationService sessionFormationService;

    public InscriptionController(
            InscriptionService inscriptionService,
            ApprenantService apprenantService,
            SessionFormationService sessionFormationService) {

        this.inscriptionService = inscriptionService;
        this.apprenantService = apprenantService;
        this.sessionFormationService = sessionFormationService;
    }

    @GetMapping
    public String listeInscriptions(Model model) {

        model.addAttribute(
                "inscriptions",
                inscriptionService.findAll()
        );

        return "inscriptions/liste";
    }

    @GetMapping("/nouvelle")
    public String nouvelleInscription(Model model) {

        model.addAttribute(
                "apprenants",
                apprenantService.findAll()
        );

        model.addAttribute(
                "sessions",
                sessionFormationService.findAll()
        );

        return "inscriptions/form";
    }

    @PostMapping("/save")
    public String saveInscription(
            @RequestParam Long apprenantId,
            @RequestParam Long sessionId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateInscription) {

        Apprenant apprenant = apprenantService
                .findById(apprenantId)
                .orElseThrow();

        SessionFormation session = sessionFormationService
                .findById(sessionId)
                .orElseThrow();

        inscriptionService.inscrire(
                apprenant,
                session,
                dateInscription
        );

        return "redirect:/inscriptions";
    }

    @PostMapping("/delete/{apprenantId}/{sessionId}")
    public String deleteInscription(
            @PathVariable Long apprenantId,
            @PathVariable Long sessionId) {

        InscriptionId id =
                new InscriptionId(apprenantId, sessionId);

        inscriptionService.deleteById(id);

        return "redirect:/inscriptions";
    }
}