package com.example.school.service;

import com.example.school.entity.Cours;
import com.example.school.entity.Filiere;
import com.example.school.repository.CoursRepository;
import com.example.school.repository.FiliereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursService {
    private final CoursRepository coursRepository;
    private final FiliereRepository filiereRepository;

    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    public Cours findById(Long id) {
        return coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours introuvable"));
    }

    public Cours save(Cours cours, Long filiereId) {
        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
        cours.setFiliere(filiere);
        return coursRepository.save(cours);
    }

    public void delete(Long id) {
        coursRepository.deleteById(id);
    }
}
