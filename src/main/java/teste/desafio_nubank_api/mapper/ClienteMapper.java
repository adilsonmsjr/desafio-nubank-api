package teste.desafio_nubank_api.mapper;

import teste.desafio_nubank_api.dto.ClienteRequestDTO;
import teste.desafio_nubank_api.dto.ClienteResponseDTO;
import teste.desafio_nubank_api.entity.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto){

        Cliente cliente = new Cliente();

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return cliente;

    }

    public static ClienteResponseDTO fromEntity(Cliente cliente){

        return new ClienteResponseDTO(
        cliente.getId(),
        cliente.getNome(),
        cliente.getCpf(),
        cliente.getContato()
               .stream()
               .map(ContatoMapper::fromEntity)
               .toList()
    );
    
    }
}
