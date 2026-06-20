package teste.desafio_nubank_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import teste.desafio_nubank_api.doc.ClienteControllerDoc;
import teste.desafio_nubank_api.dto.ClienteRequestDTO;
import teste.desafio_nubank_api.dto.ClienteResponseDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;
import teste.desafio_nubank_api.service.ClienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/cliente")
public class ClienteController implements ClienteControllerDoc{

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@Valid @RequestBody ClienteRequestDTO dto){

        return clienteService.criarCliente(dto);

    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return clienteService.listarCliente();
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO atualizar(@RequestBody ClienteRequestDTO dto, @PathVariable Long id){

        return clienteService.atualizarCliente(dto, id);

    }

    @GetMapping("/{id}/contatos")
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoResponseDTO> buscarContatos(@PathVariable Long id) {
        return clienteService.listarContato(id);
    }
    


}
