# Rapport de Projet : Application de Gestion Scolaire

**Réalisé par :** [Votre Nom]  
**Date :** 30 Décembre 2025

---

## 1. Introduction

Ce projet consiste en la conception et le développement d'une application Web de **Gestion Scolaire** basée sur le framework **Spring Boot**. L'objectif principal est de fournir une plateforme permettant de gérer efficacement les élèves, les filières, les cours et les dossiers administratifs d'un établissement scolaire.

## 2. Objectifs du Projet

Les objectifs techniques et fonctionnels du projet étaient les suivants :
*   Mettre en œuvre une architecture **MVC (Modèle-Vue-Contrôleur)** stricte.
*   Utiliser **Spring Boot 3** et **Java 17**.
*   Assurer la persistance des données avec **Spring Data JPA** et une base de données **H2 (In-Memory)**.
*   Créer des interfaces utilisateurs dynamiques avec le moteur de template **Thymeleaf** et le framework CSS **Bootstrap 5**.
*   Gérer les relations complexes entre entités (OneToMany, ManyToMany, OneToOne).
*   Implémenter une logique métier pour l'inscription automatique et la génération de numéros de dossier.

## 3. Choix Techniques

*   **Langage :** Java 17
*   **Framework Backend :** Spring Boot 3.2.1
*   **Gestion des Dépendances :** Maven
*   **Base de Données :** H2 Database (embarquée pour le développement)
*   **ORM (Object-Relational Mapping) :** Hibernate (via Spring Data JPA)
*   **Frontend :** Thymeleaf + Bootstrap 5.3
*   **Outils de Développement :** Lombok (pour réduire le code boilerplate)

## 4. Conception du Modèle de Données (Entités)

Le modèle de données s'articule autour de quatre entités principales :

1.  **Filiere** (`id`, `code`, `nom`)
    *   *Relation :* Une filière contient plusieurs élèves (OneToMany) et propose plusieurs cours (OneToMany).
2.  **Cours** (`id`, `code`, `intitule`)
    *   *Relation :* Un cours est rattaché à une filière (ManyToOne) et peut être suivi par plusieurs élèves (ManyToMany).
3.  **Eleve** (`id`, `nom`, `prenom`)
    *   *Relation :* Un élève appartient à une filière (ManyToOne), suit plusieurs cours (ManyToMany) et possède un unique dossier administratif (OneToOne).
4.  **DossierAdministratif** (`id`, `numeroInscription`, `dateCreation`)
    *   *Relation :* Lié indissociablement à un élève.

## 5. Architecture Logicielle

Le projet respecte l'architecture en couches standard de Spring Boot :

*   **Controller (`com.example.school.controller`)** : Gère les requêtes HTTP, prépare les données (Model) et sélectionne les vues (Thymeleaf).
*   **Service (`com.example.school.service`)** : Contient la logique métier (règles de gestion, calculs, orchestration).
*   **Repository (`com.example.school.repository`)** : Interface d'accès aux données (DAO) étendant `JpaRepository`.
*   **Entity (`com.example.school.entity`)** : Représente les tables de la base de données.

## 6. Fonctionnalités Implémentées

### 6.1 Gestion des Filières
*   Visualisation de la liste des filières.
*   Ajout d'une nouvelle filière.
*   Modification et suppression d'une filière existante.

### 6.2 Gestion des Cours
*   Visualisation de la liste des cours.
*   Ajout d'un cours en l'associant à une filière existante.
*   Modification et suppression des cours.

### 6.3 Gestion des Élèves (Cœur du Métier)
*   **Inscription :** Lors de l'ajout d'un élève, le système :
    1.  L'associe à une filière.
    2.  Crée automatiquement un `DossierAdministratif`.
    3.  Génère un numéro d'inscription unique sous le format : `CODE_FILIERE-ANNEE-ID` (ex: `INFO-2025-12`).
*   **CRUD :** Liste, Ajout, Modification, Suppression.
*   **Détails Élève :** Une vue dédiée affiche les informations personnelles, les détails du dossier administratif et la liste des cours suivis.
*   **Gestion des Cours :** Possibilité d'ajouter ou de retirer des cours spécifiques pour chaque élève depuis sa fiche détaillée.

### 6.4 Navigation et UX
*   Page d'accueil (`/`) avec tableau de bord (« Dashboard »).
*   Menu de navigation responsive (Bootstrap) permettant d'accéder facilement à toutes les modules (Accueil, Élèves, Filières, Cours).

## 7. Installation et Exécution

Pour lancer le projet localement :

1.  **Prérequis :** Avoir le JDK 17 installé.
2.  **Compilation :**
    ```bash
    ./mvnw clean package
    ```
3.  **Lancement :**
    ```bash
    ./mvnw spring-boot:run
    ```
4.  **Accès :** Ouvrir le navigateur à l'adresse [http://localhost:8081](http://localhost:8081).
5.  **Console Base de Données :** [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
    *   URL JDBC : `jdbc:h2:mem:school_db`
    *   User : `sa`
    *   Password : (vide)

## 8. Conclusion

Ce projet a permis de mettre en pratique les concepts fondamentaux du développement Web Java moderne. L'application résultante est modulaire, maintenable et répond aux exigences fonctionnelles de gestion scolaire, avec une interface utilisateur claire et une logique métier robuste.
