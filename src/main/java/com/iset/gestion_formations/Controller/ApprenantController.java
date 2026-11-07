
package com.iset.gestion_formations.Controller;


import com.iset.gestion_formations.model.Apprenant;
import com.iset.gestion_formations.service.ApprenantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apprenants")
public class ApprenantController {

    private final ApprenantService apprenantService;

    public ApprenantController(ApprenantService apprenantService) {
        this.apprenantService = apprenantService;
    }

    @GetMapping
    public String listeApprenants(Model model) {

        model.addAttribute(
                "apprenants",
                apprenantService.findAll()
        );

        return "apprenants/liste";
    }

    @GetMapping("/nouveau")
    public String nouveauApprenant(Model model) {

        model.addAttribute(
                "apprenant",
                new Apprenant()
        );

        return "apprenants/form";
    }

    @GetMapping("/edit/{id}")
    public String modifierApprenant(
            @PathVariable Long id,
            Model model) {

        Apprenant apprenant = apprenantService
                .findById(id)
                .orElseThrow();

        model.addAttribute("apprenant", apprenant);

        return "apprenants/form";
    }

    @PostMapping("/save")
    public String saveApprenant(
            @ModelAttribute Apprenant apprenant) {

        apprenantService.save(apprenant);

        return "redirect:/apprenants";
    }

    @PostMapping("/delete/{id}")
    public String deleteApprenant(@PathVariable Long id) {

        apprenantService.deleteById(id);

        return "redirect:/apprenants";
    }
}
