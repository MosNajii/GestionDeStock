package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {
}
