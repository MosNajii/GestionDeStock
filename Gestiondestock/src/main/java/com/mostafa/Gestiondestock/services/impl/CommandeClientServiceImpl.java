package com.mostafa.Gestiondestock.services.impl;

import com.mostafa.Gestiondestock.dto.ClientDto;
import com.mostafa.Gestiondestock.dto.CommandeClientDto;
import com.mostafa.Gestiondestock.dto.LigneCommandeClientDto;
import com.mostafa.Gestiondestock.exception.EntityNotFoundException;
import com.mostafa.Gestiondestock.exception.ErrorCodes;
import com.mostafa.Gestiondestock.exception.InvalidEntityException;
import com.mostafa.Gestiondestock.model.Article;
import com.mostafa.Gestiondestock.model.Client;
import com.mostafa.Gestiondestock.model.CommandeClient;
import com.mostafa.Gestiondestock.model.LigneCommandeClient;
import com.mostafa.Gestiondestock.repository.ArticleRepository;
import com.mostafa.Gestiondestock.repository.ClientRepository;
import com.mostafa.Gestiondestock.repository.CommandeClientRepository;
import com.mostafa.Gestiondestock.repository.LigneCommandeClientRepository;
import com.mostafa.Gestiondestock.services.CommandeClientService;
import com.mostafa.Gestiondestock.validator.CommandeClientValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;

    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository, ArticleRepository articleRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository){
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors = CommandeClientValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (!client.isPresent()){
            log.warn("Client with Id {} was not found in the DB", dto.getClient().getId());
            throw new EntityNotFoundException("Acun client avec l'ID" + dto.getClient().getId() + "n'a ete trouver dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("l'article avec l'ID" + ligCmdClt.getArticle().getId() + "n'existe pas");
                    }
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);

        }

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if (dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {

        if(id == null) {
            log.error("Commande client ID is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a ete trouve avec id = " + id , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)) {
            log.error("Commande client CODE is null");
            return null;
        }
            return commandeClientRepository.findCommandeClientByCode(code)
                    .map(CommandeClientDto::fromEntity)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Aucune commande client n'a ete trouve avec code = " + code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                    ));
        }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
    if (id == null){
        log.error("Commande client Id is null");
    }
    commandeClientRepository.deleteById(id);
    }
}
