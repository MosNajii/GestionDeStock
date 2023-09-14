package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
