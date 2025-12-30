package com.example.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eleve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @ManyToMany
    @JoinTable(name = "eleve_cours", joinColumns = @JoinColumn(name = "eleve_id"), inverseJoinColumns = @JoinColumn(name = "cours_id"))
    @ToString.Exclude
    private List<Cours> cours = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dossier_administratif_id")
    private DossierAdministratif dossierAdministratif;
}
