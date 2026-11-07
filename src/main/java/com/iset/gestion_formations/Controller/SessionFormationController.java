package com.iset.gestion_formations.Controller;


import com.iset.gestion_formations.model.SessionFormation;
import com.iset.gestion_formations.model.ThemeFormation;
import com.iset.gestion_formations.service.SessionFormationService;
import com.iset.gestion_formations.service.ThemeFormationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sessions")
public class SessionFormationController {

    private final SessionFormationService sessionFormationService;
    private final ThemeFormationService themeFormationService;

    public SessionFormationController(
            SessionFormationService sessionFormationService,
            ThemeFormationService themeFormationService) {

        this.sessionFormationService = sessionFormationService;
        this.themeFormationService = themeFormationService;
    }

    @GetMapping
    public String listeSessions(Model model) {

        model.addAttribute(
                "sessions",
                sessionFormationService.findAll()
        );

        return "sessions/liste";
    }

    @GetMapping("/nouveau")
    public String nouvelleSession(Model model) {

        model.addAttribute(
                "sessionFormation",
                new SessionFormation()
        );

        model.addAttribute(
                "themes",
                themeFormationService.findAll()
        );

        return "sessions/form";
    }

    @GetMapping("/edit/{id}")
    public String modifierSession(
            @PathVariable Long id,
            Model model) {

        SessionFormation session = sessionFormationService
                .findById(id)
                .orElseThrow();

        model.addAttribute(
                "sessionFormation",
                session
        );

        model.addAttribute(
                "themes",
                themeFormationService.findAll()
        );

        return "sessions/form";
    }

    @PostMapping("/save")
    public String saveSession(
            @ModelAttribute SessionFormation sessionFormation,
            @RequestParam Long themeId) {

        ThemeFormation theme = themeFormationService
                .findById(themeId)
                .orElseThrow();

        sessionFormation.setThemeFormation(theme);

        sessionFormationService.save(sessionFormation);

        return "redirect:/sessions";
    }

    @PostMapping("/delete/{id}")
    public String deleteSession(@PathVariable Long id) {

        sessionFormationService.deleteById(id);

        return "redirect:/sessions";
    }
}
