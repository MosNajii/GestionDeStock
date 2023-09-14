package com.mostafa.Gestiondestock.services;

import com.mostafa.Gestiondestock.dto.ClientDto;
import com.mostafa.Gestiondestock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findById(Integer id);


    List<EntrepriseDto> findAll();

    void delete(Integer id);

}
