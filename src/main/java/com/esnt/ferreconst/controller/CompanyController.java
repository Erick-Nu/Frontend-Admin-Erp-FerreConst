package com.esnt.ferreconst.controller;

import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public String companiesForm(@RequestParam(required = false) String query,
                                @RequestParam(defaultValue = "1") int page,
                                HttpSession session, Model model) {
        AuthResponse auth = (AuthResponse) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", auth.getUser());
        model.addAttribute("query", query);
        model.addAttribute("page", page);

        try {
            model.addAttribute("companyPage", companyService.search(auth, query, page));
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "companies/index";
    }

    @GetMapping("/companies/register")
    public String registerCompanyForm(HttpSession session, Model model) {
        AuthResponse auth = (AuthResponse) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", auth.getUser());
        return "companies/register";
    }

    @PostMapping("/companies")
    public String createCompany(@RequestParam String ruc,
                                 @RequestParam String businessName,
                                 @RequestParam String email,
                                 @RequestParam String code,
                                 HttpSession session, Model model) {
        AuthResponse auth = (AuthResponse) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", auth.getUser());

        ruc = ruc.trim();
        businessName = businessName.trim();
        email = email.trim();
        code = code.trim().toUpperCase(Locale.ROOT);
        model.addAttribute("ruc", ruc);
        model.addAttribute("businessName", businessName);
        model.addAttribute("email", email);
        model.addAttribute("code", code);

        boolean hasValidationError = false;
        if (!ruc.matches("\\d{13}")) {
            model.addAttribute("rucError", "El RUC debe contener exactamente 13 dígitos.");
            hasValidationError = true;
        }
        if (!code.matches("[A-Z]{2}\\d{2}")) {
            model.addAttribute("codeError", "El código debe tener dos letras mayúsculas y dos números.");
            hasValidationError = true;
        }
        if (businessName.isEmpty()) {
            model.addAttribute("businessNameError", "Ingresa la razón social de la empresa.");
            hasValidationError = true;
        }
        if (email.isEmpty()) {
            model.addAttribute("emailError", "Ingresa el correo corporativo de la empresa.");
            hasValidationError = true;
        }
        if (hasValidationError) {
            model.addAttribute("error", "Revisa los campos señalados para continuar.");
            return "companies/register";
        }

        try {
            companyService.create(auth, ruc, businessName, email, code);
            return "redirect:/companies";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "companies/register";
    }


}
