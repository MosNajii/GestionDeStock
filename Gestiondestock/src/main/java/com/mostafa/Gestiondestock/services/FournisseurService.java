package com.mostafa.Gestiondestock.services;

import com.mostafa.Gestiondestock.dto.EntrepriseDto;
import com.mostafa.Gestiondestock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    void delete(Integer id);

}
