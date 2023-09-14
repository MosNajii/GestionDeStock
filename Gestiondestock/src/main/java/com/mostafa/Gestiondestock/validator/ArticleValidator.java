package com.mostafa.Gestiondestock.validator;

import com.mostafa.Gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto dto){
        List<String> errors = new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez rensigener le code de l'article");
            errors.add("Veuillez rensigener le designatio de l'article");
            errors.add("Veuillez rensigener le prix unitaire HT de l'article");
            errors.add("Veuillez rensigener le taux TVA de l'article");
            errors.add("Veuillez rensigener le prix unitaire TTCde l'article");
            errors.add("Veuillez rensigener une category de l'article");

            return errors;
        }
        if (!StringUtils.hasLength(dto.getCodeArticle())) {
            errors.add("Veuillez rensigener le code de l'article");
        }
        if (!StringUtils.hasLength(dto.getDesignation())) {
            errors.add("Veuillez rensigener le designatio de l'article");
        }
        if(dto.getPrixUnitaireHt() == null){
            errors.add("Veuillez rensigener le prix unitaire HT de l'article");
        }
        if(dto.getTauxTva() == null){
            errors.add("Veuillez rensigener le taux TVA de l'article");
        }
        if(dto.getPrixUnitaireTtc() == null){
            errors.add("Veuillez rensigener le prix unitaire TTCde l'article");
        }
        if(dto.getCategory() == null){
            errors.add("Veuillez rensigener une category de l'article");
        }


        return  errors;
    }
}
