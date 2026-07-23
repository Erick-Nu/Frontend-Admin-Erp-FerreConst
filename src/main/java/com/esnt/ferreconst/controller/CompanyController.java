package com.esnt.ferreconst.controller;

import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
        model.addAttribute("ruc", ruc);
        model.addAttribute("businessName", businessName);
        model.addAttribute("email", email);
        model.addAttribute("code", code);

        try {
            companyService.create(auth, ruc, businessName, email, code);
            return "redirect:/companies";
        } catch (RuntimeException e) {
            model.addAttribute("modalError", e.getMessage());
            model.addAttribute("showRegisterModal", true);
            model.addAttribute("companyPage", companyService.search(auth, null, 1));
            return "companies/index";
        }
    }

    @GetMapping("/companies/{companyId}")
    public String readCompany(@PathVariable String companyId, HttpSession session, Model model) {
        AuthResponse auth = (AuthResponse) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", auth.getUser());
        try {
            model.addAttribute("company", companyService.searchById(auth, companyId));
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "companies/detail";
    }
}
