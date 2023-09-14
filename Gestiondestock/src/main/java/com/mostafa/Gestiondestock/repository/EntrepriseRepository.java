package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
}
