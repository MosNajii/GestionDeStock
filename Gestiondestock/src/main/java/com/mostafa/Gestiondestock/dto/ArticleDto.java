package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;


    public static ArticleDto fromEntity(Article article){
        if (article ==  null){

            return null;
            // todo throw exception

        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .photo(article.getPhoto())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .tauxTva(article.getTauxTva())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }

    public static Article toEntity(ArticleDto articleDto){
        if (articleDto == null){
            return null;

            // TODO throw exception
        }
        return Article.builder()
                .codeArticle(articleDto.getCodeArticle())
                .designation(articleDto.getDesignation())
                .photo(articleDto.getPhoto())
                .prixUnitaireHt(articleDto.getPrixUnitaireHt())
                .prixUnitaireTtc(articleDto.getPrixUnitaireTtc())
                .tauxTva(articleDto.getTauxTva())
                .category(CategoryDto.toEntity(articleDto.getCategory()))
                .build();

    }

}
