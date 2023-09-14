package com.mostafa.Gestiondestock.services.impl;

import com.mostafa.Gestiondestock.dto.CategoryDto;
import com.mostafa.Gestiondestock.exception.EntityNotFoundException;
import com.mostafa.Gestiondestock.exception.ErrorCodes;
import com.mostafa.Gestiondestock.exception.InvalidEntityException;
import com.mostafa.Gestiondestock.repository.CategoryRepository;
import com.mostafa.Gestiondestock.services.CategoryService;
import com.mostafa.Gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CatregoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CatregoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("category is not valid", dto);;
            throw new InvalidEntityException("l'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(dto)));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if(id == null) {
            log.error("Article ID is null");
            return null;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() ->  new EntityNotFoundException(
                        "Aucun category avec id = " + id + " n'est trouve dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }

    @Override
    public CategoryDto findByCode(String code) {
        if(!StringUtils.hasLength((code))) {
            log.error("Article CODE is null");
            return null;
        }
        return categoryRepository.findCategoryByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                "Aucun category avec CODE = " + code + " n'est trouve dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Category ID is null");
            return;
        }
        categoryRepository.deleteById(id);
    }

}
