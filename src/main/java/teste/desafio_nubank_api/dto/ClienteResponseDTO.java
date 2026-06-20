    package teste.desafio_nubank_api.dto;

    import java.util.List;


    public record ClienteResponseDTO
    (Long id, String nome, String cpf, List<ContatoResponseDTO> contato) {

    }
