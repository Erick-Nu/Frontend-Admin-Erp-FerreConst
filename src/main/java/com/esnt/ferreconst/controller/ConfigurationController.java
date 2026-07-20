package com.esnt.ferreconst.controller;

import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/companies/{companyId}/configurations")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @GetMapping
    public String configurations(@PathVariable String companyId, HttpSession session, Model model) {
        AuthResponse auth = (AuthResponse) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", auth.getUser());
        model.addAttribute("companyId", companyId);

        try {
            model.addAttribute("configurations", configurationService.findByCompany(auth, companyId));
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "configurations/index";
    }

    @PostMapping
    public String createConfiguration(@PathVariable String companyId,
                                      @RequestParam String key,
                                      @RequestParam String value,
                                      HttpSession session, Model model) {
        AuthResponse auth = (AuthResponse) session.getAttribute("auth");
        if (auth == null) {
            return "redirect:/login";
        }

        try {
            configurationService.create(auth, companyId, key, value);
            return "redirect:/companies/" + companyId + "/configurations";
        } catch (RuntimeException e) {
            model.addAttribute("user", auth.getUser());
            model.addAttribute("companyId", companyId);
            model.addAttribute("configurationKey", key);
            model.addAttribute("configurationValue", value);
            model.addAttribute("error", e.getMessage());
        }
        return "configurations/index";
    }
}
