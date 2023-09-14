package com.mostafa.Gestiondestock.services.impl;

import com.mostafa.Gestiondestock.dto.ClientDto;
import com.mostafa.Gestiondestock.dto.EntrepriseDto;
import com.mostafa.Gestiondestock.dto.FournisseurDto;
import com.mostafa.Gestiondestock.exception.ErrorCodes;
import com.mostafa.Gestiondestock.exception.InvalidEntityException;
import com.mostafa.Gestiondestock.repository.FournisseurRepository;
import com.mostafa.Gestiondestock.services.FournisseurService;
import com.mostafa.Gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository =fournisseurRepository;
    }


    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("category is not valid", dto);;
            throw new InvalidEntityException("fournisseur n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(
                        FournisseurDto.toEntity(dto)
                )
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return null;
    }

    @Override
    public List<FournisseurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
