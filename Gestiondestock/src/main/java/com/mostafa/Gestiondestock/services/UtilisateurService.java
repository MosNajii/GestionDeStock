package com.mostafa.Gestiondestock.services;

import com.mostafa.Gestiondestock.dto.EntrepriseDto;
import com.mostafa.Gestiondestock.dto.FournisseurDto;
import com.mostafa.Gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);


    List<UtilisateurDto> findAll();

    void delete(Integer id);

}
