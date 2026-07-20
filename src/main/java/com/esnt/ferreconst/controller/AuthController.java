package com.esnt.ferreconst.controller;

import com.esnt.ferreconst.exception.AuthenticationException;
import com.esnt.ferreconst.model.Auth;
import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Auth auth, HttpSession session, Model model) {
        try {
            AuthResponse response = authService.login(auth);
            if (!response.getCompany().getIsParent()) {
                model.addAttribute("error", "Usuario no autorizado");
                model.addAttribute("auth", auth);
                return "login";
            }
            if (!response.getUser().getRole().equals("administrador")){
                model.addAttribute("error", "Usuario no autorizado");
                model.addAttribute("auth", auth);
                return "login";
            }
            session.setAttribute("auth", response);
            return "redirect:/dashboard";
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("auth", auth);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
