package teste.desafio_nubank_api.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
    @NotBlank(message = "Nome é obrigatório.") String nome, 
    @NotBlank(message ="CPF é obrigatório.") String cpf) {
   
}
