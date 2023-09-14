package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
}
