package com.esnt.ferreconst.controller;

import com.esnt.ferreconst.model.AuthResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(HttpSession session, Model model) {
        AuthResponse auth = (AuthResponse) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/login";
        }
        model.addAttribute("company", auth.getCompany());
        model.addAttribute("user", auth.getUser());
        return "dashboard";
    }
}
