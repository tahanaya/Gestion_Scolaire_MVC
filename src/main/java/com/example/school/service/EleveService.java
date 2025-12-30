package com.example.school.service;

import com.example.school.entity.DossierAdministratif;
import com.example.school.entity.Eleve;
import com.example.school.entity.Filiere;
import com.example.school.repository.EleveRepository;
import com.example.school.repository.FiliereRepository;
import com.example.school.repository.CoursRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EleveService {
    private final EleveRepository eleveRepository;
    private final FiliereRepository filiereRepository;
    private final CoursRepository coursRepository;

    public List<Eleve> findAll() {
        return eleveRepository.findAll();
    }

    public void delete(Long id) {
        eleveRepository.deleteById(id);
    }

    @Transactional
    public void inscrireEleve(Eleve eleve, Long filiereId) {

        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        eleve.setFiliere(filiere);
        DossierAdministratif dossier = new DossierAdministratif();
        dossier.setDateCreation(LocalDate.now());
        dossier.setEleve(eleve);
        eleve.setDossierAdministratif(dossier);


        eleve = eleveRepository.saveAndFlush(eleve);

        int annee = LocalDate.now().getYear();
        String numeroInscription = String.format("%s-%d-%d", filiere.getCode(), annee, eleve.getId());

        eleve.getDossierAdministratif().setNumeroInscription(numeroInscription);
        eleveRepository.save(eleve);
    }

    public Eleve findById(Long id) {
        return eleveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Élève introuvable"));
    }

    public void save(Eleve eleve) {
        eleveRepository.save(eleve);
    }

    @Transactional
    public void addCours(Long eleveId, Long coursId) {
        Eleve eleve = findById(eleveId);
        com.example.school.entity.Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new RuntimeException("Cours introuvable"));

        eleve.getCours().add(cours);
        eleveRepository.save(eleve);
    }

    @Transactional
    public void removeCours(Long eleveId, Long coursId) {
        Eleve eleve = findById(eleveId);
        com.example.school.entity.Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new RuntimeException("Cours introuvable"));

        eleve.getCours().remove(cours);
        eleveRepository.save(eleve);
    }
}
