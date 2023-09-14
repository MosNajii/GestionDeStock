package com.mostafa.Gestiondestock.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity {

    @Column(name = "codeArticle")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixUnitaireHt")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxTva")
    private BigDecimal tauxTva;

    @Column(name = "prixUnitaireTtc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;
    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;
}
