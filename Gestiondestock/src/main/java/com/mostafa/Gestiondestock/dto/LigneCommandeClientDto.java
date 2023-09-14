package com.mostafa.Gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mostafa.Gestiondestock.model.Article;
import com.mostafa.Gestiondestock.model.CommandeClient;
import com.mostafa.Gestiondestock.model.Fournisseur;
import com.mostafa.Gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient){
        if (ligneCommandeClient ==  null){

            return null;
            // todo throw exception

        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .build();
    }

    public  static LigneCommandeClient toEntity(LigneCommandeClientDto dto){
        if (dto == null){
            return null;

            // TODO throw exception
        }

        return LigneCommandeClient.builder()
                .article(ArticleDto.toEntity(dto.getArticle()))
                .quantite(dto.getQuantite())
                .prixUnitaire(dto.getPrixUnitaire())
                .build();
    }

}
