package com.iset.gestion_formations.Controller;



import com.iset.gestion_formations.service.SessionFormationService;
import com.iset.gestion_formations.service.ThemeFormationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/formations")
public class FormationController {

    private final ThemeFormationService themeFormationService;
    private final SessionFormationService sessionFormationService;

    public FormationController(
            ThemeFormationService themeFormationService,
            SessionFormationService sessionFormationService) {

        this.themeFormationService = themeFormationService;
        this.sessionFormationService = sessionFormationService;
    }

    @GetMapping("/recherche")
    public String rechercher(
            @RequestParam(required = false) Long themeId,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateDebut,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateFin,

            Model model) {

        model.addAttribute(
                "themes",
                themeFormationService.findAll()
        );

        if (themeId != null
                && dateDebut != null
                && dateFin != null) {

            model.addAttribute(
                    "resultats",
                    sessionFormationService
                            .rechercherParThemeEtDates(
                                    themeId,
                                    dateDebut,
                                    dateFin
                            )
            );
        }

        model.addAttribute("themeId", themeId);
        model.addAttribute("dateDebut", dateDebut);
        model.addAttribute("dateFin", dateFin);

        return "formations/recherche";
    }
}