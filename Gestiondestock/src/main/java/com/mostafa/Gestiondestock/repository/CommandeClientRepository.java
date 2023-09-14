package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.Article;
import com.mostafa.Gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient,Integer> {

    Optional<CommandeClient> findCommandeClientByCode(String code);
}
