package teste.desafio_nubank_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import teste.desafio_nubank_api.dto.ContatoRequestDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;
import teste.desafio_nubank_api.service.ContatoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/contato")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController (ContatoService contatoService){
        this.contatoService = contatoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoResponseDTO criar(@RequestBody ContatoRequestDTO dto) {
        
        return contatoService.criarContato(dto);
        
    }
    

}
