package com.example.school.controller;

import com.example.school.entity.Cours;
import com.example.school.service.CoursService;
import com.example.school.service.FiliereService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cours")
@RequiredArgsConstructor
public class CoursController {
    private final CoursService coursService;
    private final FiliereService filiereService;

    @GetMapping
    public String listCours(Model model) {
        model.addAttribute("coursList", coursService.findAll());
        return "cours";
    }

    @GetMapping("/nouveau")
    public String formCours(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("filieres", filiereService.findAll());
        return "form_cours";
    }

    @GetMapping("/modifier/{id}")
    public String editCours(@PathVariable Long id, Model model) {
        model.addAttribute("cours", coursService.findById(id));
        model.addAttribute("filieres", filiereService.findAll());
        return "form_cours";
    }

    @PostMapping("/enregistrer")
    public String saveCours(@ModelAttribute Cours cours, @RequestParam("filiereId") Long filiereId) {
        coursService.save(cours, filiereId);
        return "redirect:/cours";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteCours(@PathVariable Long id) {
        coursService.delete(id);
        return "redirect:/cours";
    }
}
