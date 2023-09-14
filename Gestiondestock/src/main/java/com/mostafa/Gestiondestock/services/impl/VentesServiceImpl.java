package com.mostafa.Gestiondestock.services.impl;

import com.mostafa.Gestiondestock.dto.CommandeClientDto;
import com.mostafa.Gestiondestock.dto.CommandeFournisseurDto;
import com.mostafa.Gestiondestock.dto.LigneVenteDto;
import com.mostafa.Gestiondestock.dto.VentesDto;
import com.mostafa.Gestiondestock.exception.EntityNotFoundException;
import com.mostafa.Gestiondestock.exception.ErrorCodes;
import com.mostafa.Gestiondestock.exception.InvalidEntityException;
import com.mostafa.Gestiondestock.model.Article;
import com.mostafa.Gestiondestock.model.LigneVente;
import com.mostafa.Gestiondestock.model.Ventes;
import com.mostafa.Gestiondestock.repository.*;
import com.mostafa.Gestiondestock.services.VentesService;
import com.mostafa.Gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public VentesServiceImpl(VentesRepository ventesRepository,
                             LigneVenteRepository ligneVenteRepository,
                             ArticleRepository articleRepository){
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        dto.getLigneVente().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());

            if(article.isEmpty()){
                articleErrors.add("Aucun article avec l'id " + ligneVenteDto.getArticle().getId() + "n'a ete trouver dans la BDD");
            }
        });

        if(!articleErrors.isEmpty()){
            log.error("One or more article were not found in the DB, {}", errors);
            throw new InvalidEntityException("un ou  plusieurs articles n'ont ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

        dto.getLigneVente().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });


        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if(id == null) {
            log.error("Vente CODE is null");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente n'a ete trouve dans la BDD ", ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VentesDto findByCode(String code) {
        if(!StringUtils.hasLength(code)) {
            log.error("Commande client CODE is null");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente n'a ete trouve avec code = " + code , ErrorCodes.VENTE_NOT_VALID
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("vente Id is null");
        }
        ventesRepository.deleteById(id);
    }

}
