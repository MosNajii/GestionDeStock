package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String mail;

    private String numTel;

    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client){
        if (client ==  null){

            return null;
            // todo throw exception

        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresse(AdresseDto.fromEntity(client.getAdresse()))
                .mail(client.getMail())
                .numTel(client.getNumTel())
                .build();
    }

    public  static Client toEntity(ClientDto clientDto){
        if (clientDto == null){
            return null;

            // TODO throw exception
        }

        return Client.builder()
                .nom(clientDto.getNom())
                .prenom(clientDto.getPrenom())
                .adresse(AdresseDto.toEntity(clientDto.getAdresse()))
                .mail(clientDto.getMail())
                .numTel(clientDto.getNumTel())
                .build();
    }

}
