package com.mostafa.Gestiondestock.services.impl;

import com.mostafa.Gestiondestock.dto.ClientDto;
import com.mostafa.Gestiondestock.exception.EntityNotFoundException;
import com.mostafa.Gestiondestock.exception.ErrorCodes;
import com.mostafa.Gestiondestock.exception.InvalidEntityException;
import com.mostafa.Gestiondestock.model.Client;
import com.mostafa.Gestiondestock.repository.ClientRepository;
import com.mostafa.Gestiondestock.services.ClientService;
import com.mostafa.Gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }


    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("category is not valid", dto);;
            throw new InvalidEntityException("Client n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(
                        ClientDto.toEntity(dto)
                )
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if(id == null) {
            log.error("Article ID is null");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun client avec id = " + id + " n'est trouve dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Category ID is null");
            return;
        }
        clientRepository.deleteById(id);
    }

}
