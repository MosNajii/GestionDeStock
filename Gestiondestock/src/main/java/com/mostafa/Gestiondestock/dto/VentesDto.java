package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.CommandeClient;
import com.mostafa.Gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDto {

    private Integer id;

    private  String code;

    private Instant dateVente;

    private String commentaire;

    private List<LigneVenteDto> ligneVente;

    public static VentesDto fromEntity(Ventes ventes){
        if (ventes ==  null){
            return null;

        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .commentaire(ventes.getCommentaire())
                .dateVente(ventes.getDateVente())
                .build();
    }

    public  static Ventes toEntity(VentesDto dto){
        if (dto == null){
            return null;

        }
        return Ventes.builder()
                .code(dto.getCode())
                .commentaire(dto.getCommentaire())
                .dateVente(dto.getDateVente())
                .build();
    }

}
