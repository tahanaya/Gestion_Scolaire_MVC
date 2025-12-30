package com.example.school.controller;

import com.example.school.entity.Eleve;
import com.example.school.repository.FiliereRepository;
import com.example.school.service.EleveService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/eleves")
@RequiredArgsConstructor
public class EleveController {
    private final EleveService eleveService;
    private final FiliereRepository filiereRepository;

    @GetMapping
    public String listEleves(Model model) {
        model.addAttribute("eleves", eleveService.findAll());
        return "eleves";
    }

    @GetMapping("/nouveau")
    public String formEleve(Model model) {
        model.addAttribute("eleve", new Eleve());
        model.addAttribute("filieres", filiereRepository.findAll());
        return "form_eleve";
    }

    @PostMapping("/enregistrer")
    public String saveEleve(@ModelAttribute Eleve eleve, @RequestParam("filiereId") Long filiereId) {
        eleveService.inscrireEleve(eleve, filiereId);
        return "redirect:/eleves";
    }

    @GetMapping("/modifier/{id}")
    public String editEleve(@PathVariable Long id, Model model) {
        model.addAttribute("eleve", eleveService.findById(id));
        model.addAttribute("filieres", filiereRepository.findAll());
        return "form_eleve";
    }

    @GetMapping("/details/{id}")
    public String detailsEleve(@PathVariable Long id, Model model) {
        Eleve eleve = eleveService.findById(id);
        model.addAttribute("eleve", eleve);
        model.addAttribute("allCours", filiereRepository.findById(eleve.getFiliere().getId()).get().getCours());
        return "details_eleve";
    }

    @GetMapping("/{eleveId}/cours/ajouter")
    public String addCoursToEleve(@PathVariable Long eleveId, @RequestParam("coursId") Long coursId) {
        eleveService.addCours(eleveId, coursId);
        return "redirect:/eleves/details/" + eleveId;
    }

    @GetMapping("/{eleveId}/cours/supprimer/{coursId}")
    public String removeCoursFromEleve(@PathVariable Long eleveId, @PathVariable Long coursId) {
        eleveService.removeCours(eleveId, coursId);
        return "redirect:/eleves/details/" + eleveId;
    }

    @GetMapping("/supprimer/{id}")
    public String deleteEleve(@PathVariable Long id) {
        eleveService.delete(id);
        return "redirect:/eleves";
    }
}
