package com.mostafa.Gestiondestock.services;

import com.mostafa.Gestiondestock.dto.ArticleDto;
import com.mostafa.Gestiondestock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);

    CategoryDto findById(Integer id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    void delete(Integer id);
}
