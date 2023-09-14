package com.mostafa.Gestiondestock.validator;

import com.mostafa.Gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null){
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prenom d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
            errors.add("Veuillez renseigner l'adresse' d'utilisateur");

            return errors;
        }
        if (!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veuillez remplir le nom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veuillez remplir le prenom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez remplir l'email' d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("Veuillez remplir le nim d'utilisateur");
        }
        if (utilisateurDto.getDateDeNaissance() == null){
            errors.add("Veuillez rensiegner la date de naissance d'utilisateur");
        }
        if (utilisateurDto.getAdresse() == null){
            errors.add("Veuillez remplir le nim d'utilisateur");
        }else {
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())){
                errors.add("Veuillez neseigner l'adresse1");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())){
                errors.add("le champs 'ville' est obligatoir");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())){
                errors.add("le champs 'codePostale' est obligatoir");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())){
                errors.add("le champs 'pays' est obligatoir ");
            }
        }
        return  errors;
    }
}
