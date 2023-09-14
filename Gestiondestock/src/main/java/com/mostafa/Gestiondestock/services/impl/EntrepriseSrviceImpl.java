package com.mostafa.Gestiondestock.services.impl;

import com.mostafa.Gestiondestock.dto.EntrepriseDto;
import com.mostafa.Gestiondestock.exception.EntityNotFoundException;
import com.mostafa.Gestiondestock.exception.ErrorCodes;
import com.mostafa.Gestiondestock.exception.InvalidEntityException;
import com.mostafa.Gestiondestock.model.Entreprise;
import com.mostafa.Gestiondestock.repository.EntrepriseRepository;
import com.mostafa.Gestiondestock.services.EntrepriseService;
import com.mostafa.Gestiondestock.validator.EntrepriseValiadtor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseSrviceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    @Autowired
    public EntrepriseSrviceImpl(EntrepriseRepository entrepriseRepository)
    {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {

        List<String> errors = EntrepriseValiadtor.validate(dto);
        if(!errors.isEmpty()){
            log.error("category is not valid", dto);;
            throw new InvalidEntityException("entreprise n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(dto)
                )
        );
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if(id == null) {
            log.error("entreprise ID is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);

        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun client avec id = " + id + " n'est trouve dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Category ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
