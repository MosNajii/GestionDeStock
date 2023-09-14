package com.mostafa.Gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvtstk")
public class MvtStk extends AbstractEntity {

    @Column(name = "datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private String quantite;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "type_mvt_ID")
    private TypeMvtStk typeMvt;

}
