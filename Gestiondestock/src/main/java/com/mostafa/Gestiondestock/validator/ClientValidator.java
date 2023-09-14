package com.mostafa.Gestiondestock.validator;

import com.mostafa.Gestiondestock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto dto) {
        List<String> errors = new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez rensigner le nom du client");
            errors.add("Veuillez rensigner le prenom du client");
            errors.add("Veuillez rensigner le mail du client");
            errors.add("Veuillez rensigner le num de Telephone du client");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Veuillez rensigner le nom du client");
        }
        if (!StringUtils.hasLength(dto.getPrenom())) {
            errors.add("Veuillez rensigner le prenom du client");
        }
        if (!StringUtils.hasLength(dto.getMail())) {
            errors.add("Veuillez rensigner le mail du client");
        }
        if (!StringUtils.hasLength(dto.getNumTel())) {
            errors.add("Veuillez rensigner le num de Telephone du client");
        }
        return errors;
    }
}
