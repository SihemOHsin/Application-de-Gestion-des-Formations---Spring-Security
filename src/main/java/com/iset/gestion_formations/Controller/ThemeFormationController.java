package com.iset.gestion_formations.Controller;

import com.iset.gestion_formations.model.ThemeFormation;
import com.iset.gestion_formations.service.ThemeFormationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/themes")
public class ThemeFormationController {

    private final ThemeFormationService themeFormationService;

    public ThemeFormationController(ThemeFormationService themeFormationService) {
        this.themeFormationService = themeFormationService;
    }

    @GetMapping
    public String listeThemes(Model model) {
        model.addAttribute("themes", themeFormationService.findAll());
        return "themes/liste";
    }

    @GetMapping("/nouveau")
    public String nouveauTheme(Model model) {
        model.addAttribute("themeFormation", new ThemeFormation());
        return "themes/form";
    }

    @GetMapping("/edit/{id}")
    public String modifierTheme(@PathVariable Long id, Model model) {

        ThemeFormation theme = themeFormationService
                .findById(id)
                .orElseThrow();

        model.addAttribute("themeFormation", theme);

        return "themes/form";
    }

    @PostMapping("/save")
    public String saveTheme(
            @ModelAttribute ThemeFormation themeFormation) {

        themeFormationService.save(themeFormation);

        return "redirect:/themes";
    }

    @PostMapping("/delete/{id}")
    public String deleteTheme(@PathVariable Long id) {

        themeFormationService.deleteById(id);

        return "redirect:/themes";
    }
}