package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
