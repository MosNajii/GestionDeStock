package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.Article;
import com.mostafa.Gestiondestock.model.LigneCommandeClient;
import com.mostafa.Gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur){
        if (ligneCommandeFournisseur ==  null){

            return null;
            // todo throw exception

        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .build();
    }

    public  static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto){
        if (dto == null){
            return null;

            // TODO throw exception
        }

        return LigneCommandeFournisseur.builder()
                .article(ArticleDto.toEntity(dto.getArticle()))
                .quantite(dto.getQuantite())
                .prixUnitaire(dto.getPrixUnitaire())
                .build();
    }

}
