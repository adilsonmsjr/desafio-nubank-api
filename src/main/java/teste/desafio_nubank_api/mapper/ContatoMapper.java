package teste.desafio_nubank_api.mapper;

import teste.desafio_nubank_api.dto.ContatoRequestDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;
import teste.desafio_nubank_api.entity.Contato;

public class ContatoMapper {

    public static Contato toEntity(ContatoRequestDTO dto){

        Contato contato = new Contato();

        contato.setNomeContato(dto.nomeContato());
        contato.setTelefone(dto.telefone());
    
        return contato;
    }

    public static ContatoResponseDTO fromEntity(Contato contato){

        return new ContatoResponseDTO(
                contato.getId(),
                contato.getNomeContato(),
                contato.getTelefone(),
                contato.getCliente().getId()
            );

    }

}
