package com.example.school.repository;

import com.example.school.entity.DossierAdministratif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierAdministratifRepository extends JpaRepository<DossierAdministratif, Long> {
}
