package com.mostafa.Gestiondestock.validator;

import com.mostafa.Gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto){
        List<String> errors = new ArrayList<>();

        if (categoryDto ==null || !StringUtils.hasLength(categoryDto.getCode())){
            errors.add("Veuillez rensigener le caode dans le category");
        }

            return  errors;
    }
}
