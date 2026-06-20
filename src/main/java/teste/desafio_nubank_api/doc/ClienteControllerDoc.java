package teste.desafio_nubank_api.doc;

import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import teste.desafio_nubank_api.dto.ClienteRequestDTO;
import teste.desafio_nubank_api.dto.ClienteResponseDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;

@Tag(
    name = "Clientes",
    description = "Operações relacionadas ao gerenciamento de clientes"
)
public interface ClienteControllerDoc {

    @Operation(
        summary = "Cadastrar cliente",
        description = "Cria um novo cliente no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    ClienteResponseDTO criar(@Valid @RequestBody ClienteRequestDTO dto);



    @Operation(
        summary = "Listar clientes",
        description = "Retorna todos os clientes cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso")
    List<ClienteResponseDTO> listar();

    @Operation(
        summary = "Listar contatos de clientes",
        description = "Retorna todos os contatos cadastrados de um cliente"
    )
    @ApiResponse(responseCode = "200", description = "Contatos retornados com sucesso")
    List<ContatoResponseDTO> buscarContatos(@PathVariable Long id);



    @Operation(
        summary = "Atualizar cliente",
        description = "Atualiza os dados de um cliente existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    ClienteResponseDTO atualizar(
            @RequestBody ClienteRequestDTO dto,
            @PathVariable Long id);



    @Operation(
        summary = "Excluir cliente",
        description = "Remove um cliente pelo id"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    void deletar(@PathVariable Long id);

}