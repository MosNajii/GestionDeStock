package com.mostafa.Gestiondestock.services.impl;

import com.mostafa.Gestiondestock.dto.CommandeFournisseurDto;
import com.mostafa.Gestiondestock.dto.LigneCommandeFournisseurDto;
import com.mostafa.Gestiondestock.exception.EntityNotFoundException;
import com.mostafa.Gestiondestock.exception.ErrorCodes;
import com.mostafa.Gestiondestock.exception.InvalidEntityException;
import com.mostafa.Gestiondestock.model.Article;
import com.mostafa.Gestiondestock.model.CommandeFournisseur;
import com.mostafa.Gestiondestock.model.Fournisseur;
import com.mostafa.Gestiondestock.model.LigneCommandeFournisseur;
import com.mostafa.Gestiondestock.repository.ArticleRepository;
import com.mostafa.Gestiondestock.repository.CommandeFournisseurRepository;
import com.mostafa.Gestiondestock.repository.FournisseurRepository;
import com.mostafa.Gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.mostafa.Gestiondestock.services.CommandeFournisseurService;
import com.mostafa.Gestiondestock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;

    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository, ArticleRepository articleRepository){
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("Commande fournisseur n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (!fournisseur.isPresent()){
            log.warn("fournisseur with Id {} was not found in the DB", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Acun fournisseur avec l'ID" + dto.getFournisseur().getId() + "n'a ete trouver dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (dto.getLignecommandeFournisseurs() != null){
            dto.getLignecommandeFournisseurs().forEach(ligCmdFrn -> {
                if (ligCmdFrn.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdFrn.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("l'article avec l'ID" + ligCmdFrn.getArticle().getId() + "n'existe pas");
                    }
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);

        }

        CommandeFournisseur savedCmdfrn = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if (dto.getLignecommandeFournisseurs() != null){
            dto.getLignecommandeFournisseurs().forEach(ligCmdFrn -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrn);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdfrn);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdfrn);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if(id == null) {
            log.error("Commande client ID is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a ete trouve avec id = " + id , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if(!StringUtils.hasLength(code)) {
            log.error("Commande client CODE is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a ete trouve avec code = " + code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("Commande fournisseur Id is null");
        }
        commandeFournisseurRepository.deleteById(id);
    }

}
