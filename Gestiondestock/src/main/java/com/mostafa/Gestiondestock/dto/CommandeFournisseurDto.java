package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.CommandeClient;
import com.mostafa.Gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> lignecommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){
        if (commandeFournisseur ==  null){

            return null;
            // todo throw exception

        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .build();
    }

    public  static CommandeFournisseur toEntity(CommandeFournisseurDto dto){
        if (dto == null){
            return null;

            // TODO throw exception
        }
        return CommandeFournisseur.builder()
                .code(dto.getCode())
                .dateCommande(dto.getDateCommande())
                .fournisseur(FournisseurDto.toEntity(dto.getFournisseur()))
                .build();
    }
}
