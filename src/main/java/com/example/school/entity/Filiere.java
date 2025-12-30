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
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String nom;

    @OneToMany(mappedBy = "filiere")
    @ToString.Exclude
    private List<Eleve> eleves = new ArrayList<>();

    @OneToMany(mappedBy = "filiere")
    @ToString.Exclude
    private List<Cours> cours = new ArrayList<>();

    public Filiere(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }
}
