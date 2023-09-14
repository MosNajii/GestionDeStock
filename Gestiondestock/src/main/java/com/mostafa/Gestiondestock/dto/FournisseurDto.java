package com.mostafa.Gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mostafa.Gestiondestock.model.Adresse;

import com.mostafa.Gestiondestock.model.Entreprise;
import com.mostafa.Gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String mail;

    private String numTel;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if (fournisseur ==  null){

            return null;
            // todo throw exception

        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .build();
    }

    public  static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if (fournisseurDto == null){
            return null;

            // TODO throw exception
        }

        return Fournisseur.builder()
                .nom(fournisseurDto.getNom())
                .prenom(fournisseurDto.getPrenom())
                .adresse(AdresseDto.toEntity(fournisseurDto.getAdresse()))
                .mail(fournisseurDto.getMail())
                .numTel(fournisseurDto.getNumTel())
                .build();
    }

}
