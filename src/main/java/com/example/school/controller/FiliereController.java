package com.example.school.controller;

import com.example.school.entity.Filiere;
import com.example.school.service.FiliereService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filieres")
@RequiredArgsConstructor
public class FiliereController {
    private final FiliereService filiereService;

    @GetMapping
    public String listFilieres(Model model) {
        model.addAttribute("filieres", filiereService.findAll());
        return "filieres";
    }

    @GetMapping("/nouveau")
    public String formFiliere(Model model) {
        model.addAttribute("filiere", new Filiere());
        return "form_filiere";
    }

    @GetMapping("/modifier/{id}")
    public String editFiliere(@PathVariable Long id, Model model) {
        model.addAttribute("filiere", filiereService.findById(id));
        return "form_filiere";
    }

    @PostMapping("/enregistrer")
    public String saveFiliere(@ModelAttribute Filiere filiere) {
        filiereService.save(filiere);
        return "redirect:/filieres";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteFiliere(@PathVariable Long id) {
        filiereService.delete(id);
        return "redirect:/filieres";
    }
}
