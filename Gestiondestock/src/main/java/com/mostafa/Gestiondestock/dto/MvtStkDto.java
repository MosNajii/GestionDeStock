package com.mostafa.Gestiondestock.dto;

import com.mostafa.Gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class MvtStkDto {

    private Integer id;

    private Instant dateMvt;

    private String quantite;

    private ArticleDto article;

    private TypeMvtStk typeMvt;

}
