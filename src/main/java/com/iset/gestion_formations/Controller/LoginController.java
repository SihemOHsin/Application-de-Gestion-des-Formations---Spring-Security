
package com.iset.gestion_formations.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Afficher la page de connexion
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Afficher la page acces refuse
    @GetMapping("/403")
    public String accessDenied() {
        return "error/403";
    }
}
