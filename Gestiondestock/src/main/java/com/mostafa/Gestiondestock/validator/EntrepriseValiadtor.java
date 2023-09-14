package com.mostafa.Gestiondestock.validator;

import com.mostafa.Gestiondestock.dto.EntrepriseDto;
import com.mostafa.Gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValiadtor {

    public static List<String> validate(EntrepriseDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez rensigner le nom du entreprise");
            errors.add("Veuillez rensigner le description du entreprise");
            errors.add("Veuillez rensigner le mail du entreprise");
            errors.add("Veuillez rensigner le num de Telephone du entreprise");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Veuillez rensigner le nom du fournisseur");
        }
        if (!StringUtils.hasLength(dto.getDescription())) {
            errors.add("Veuillez rensigner le prenom du fournisseur");
        }
        if (!StringUtils.hasLength(dto.getEmail())) {
            errors.add("Veuillez rensigner le mail du fournisseur");
        }
        if (!StringUtils.hasLength(dto.getNumTel())) {
            errors.add("Veuillez rensigner le num de Telephone du fournisseur");
        }
        return errors;
    }
}
