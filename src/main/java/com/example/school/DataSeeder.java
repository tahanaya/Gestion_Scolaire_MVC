package com.example.school;

import com.example.school.entity.Eleve;
import com.example.school.entity.Filiere;
import com.example.school.repository.FiliereRepository;
import com.example.school.service.EleveService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final FiliereRepository filiereRepository;
    private final EleveService eleveService;

    @Override
    public void run(String... args) throws Exception {
        if (filiereRepository.count() == 0) {
            System.out.println("Seeding Data...");


            Filiere info = filiereRepository.save(new Filiere("INFO", "Génie Logiciel"));
            Filiere eco = filiereRepository.save(new Filiere("ECO", "Économie"));


            Eleve e1 = new Eleve();
            e1.setNom("Naya");
            e1.setPrenom("Taha");
            eleveService.inscrireEleve(e1, info.getId());

            Eleve e2 = new Eleve();
            e2.setNom("Ouladm Maalem");
            e2.setPrenom("Ayoub");
            eleveService.inscrireEleve(e2, eco.getId());

            System.out.println("Seeding Complete: 2 Filières, 2 Élèves.");
        }
    }
}
