package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.CommandeClient;
import com.mostafa.Gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
}
