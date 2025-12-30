package com.example.school.service;

import com.example.school.entity.Filiere;
import com.example.school.repository.FiliereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FiliereService {
    private final FiliereRepository filiereRepository;

    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    public Filiere findById(Long id) {
        return filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
    }

    public Filiere save(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    public void delete(Long id) {
        filiereRepository.deleteById(id);
    }
}
