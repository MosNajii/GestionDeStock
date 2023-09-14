package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.LigneCommandeFournisseur;
import com.mostafa.Gestiondestock.model.LigneVente;
import com.mostafa.Gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneVenteDto {

    private Integer id;

    private Ventes vente;

    private ArticleDto article;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if (ligneVente ==  null){

            return null;
            // todo throw exception

        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .vente(ligneVente.getVente())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .build();
    }

    public  static LigneVente toEntity(LigneVenteDto dto){
        if (dto == null){
            return null;

            // TODO throw exception
        }

        return LigneVente.builder()
                .article(ArticleDto.toEntity(dto.getArticle()))
                .vente(dto.getVente())
                .quantite(dto.getQuantite())
                .prixUnitaire(dto.getPrixUnitaire())
                .build();
    }

}
