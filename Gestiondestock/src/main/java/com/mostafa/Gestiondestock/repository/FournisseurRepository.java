package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}
