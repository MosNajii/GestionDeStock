package com.mostafa.Gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mostafa.Gestiondestock.model.Client;
import com.mostafa.Gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if (commandeClient ==  null){

            return null;
            // todo throw exception

        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .build();
    }

    public  static CommandeClient toEntity(CommandeClientDto dto){
        if (dto == null){
            return null;

            // TODO throw exception
        }
        return CommandeClient.builder()
                .code(dto.getCode())
                .dateCommande(dto.getDateCommande())
                .client(ClientDto.toEntity(dto.getClient()))
                .build();
    }
}
