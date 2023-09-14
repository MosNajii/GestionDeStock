package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.CommandeFournisseur;
import com.mostafa.Gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

    Optional<Ventes> findVentesByCode(String code);
}
