package com.mostafa.Gestiondestock.repository;

import com.mostafa.Gestiondestock.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RolesRepository extends JpaRepository <Roles, Integer>{
}
