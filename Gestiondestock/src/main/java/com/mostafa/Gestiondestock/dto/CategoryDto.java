package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    private List<ArticleDto> articles;

    public static CategoryDto fromEntity(Category category){
        if (category ==  null){

            return null;
            // todo throw exception

        }
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .build();
    }

    public  static Category toEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            return null;

            // TODO throw exception
        }

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());

        return category;
    }

}
