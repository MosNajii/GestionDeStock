package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.Adresse;
import com.mostafa.Gestiondestock.model.Client;
import com.mostafa.Gestiondestock.model.Entreprise;
import com.mostafa.Gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Data
public class EntrepriseDto {

    private Integer id;

    private String nom;

    private String description;

    private AdresseDto adresse;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String steWeb;

    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if (entreprise ==  null){

            return null;
            // todo throw exception

        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .steWeb(entreprise.getSteWeb())
                .numTel(entreprise.getNumTel())
                .build();
    }

    public  static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if (entrepriseDto == null){
            return null;

            // TODO throw exception
        }

        return Entreprise.builder()
                .nom(entrepriseDto.getNom())
                .description(entrepriseDto.getDescription())
                .adresse(AdresseDto.toEntity(entrepriseDto.getAdresse()))
                .codeFiscal(entrepriseDto.getCodeFiscal())
                .photo(entrepriseDto.getPhoto())
                .steWeb(entrepriseDto.getSteWeb())
                .numTel(entrepriseDto.getNumTel())
                .build();
    }

}
