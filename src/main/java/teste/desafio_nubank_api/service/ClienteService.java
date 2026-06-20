package teste.desafio_nubank_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import teste.desafio_nubank_api.dto.ClienteRequestDTO;
import teste.desafio_nubank_api.dto.ClienteResponseDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;
import teste.desafio_nubank_api.entity.Cliente;
import teste.desafio_nubank_api.exception.DuplicateException;
import teste.desafio_nubank_api.exception.ResourceNotFoundException;
import teste.desafio_nubank_api.mapper.ClienteMapper;
import teste.desafio_nubank_api.mapper.ContatoMapper;
import teste.desafio_nubank_api.repository.ClienteRepository;
import teste.desafio_nubank_api.repository.ContatoRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService (ClienteRepository clienteRepository, ContatoRepository contatoRepository){
        this.clienteRepository = clienteRepository;
            }

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto){

         if (clienteRepository.existsByCpf(dto.cpf())) {
        throw new DuplicateException("CPF já cadastrado.");
        }


        Cliente cliente = ClienteMapper.toEntity(dto);

        cliente = clienteRepository.save(cliente);

        return ClienteMapper.fromEntity(cliente);

    }

    public List<ClienteResponseDTO> listarCliente(){
        /* return clienteRepository.findAll()
                .stream()
                .map(cliente -> ClienteMapper.fromEntity(cliente))
                .toList(); */

        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteResponseDTO> response = clientes.stream() //.stream - lista em fluxo de elementos / percorre cada cliente da lista
                                            .map(cliente -> ClienteMapper.fromEntity(cliente)) // transforma cada elemento em outro
                                            .toList(); // converte de .stream para uma lista novamente

                return response;
    }

    public List<ContatoResponseDTO> listarContato(Long id){  
        
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
        
            return cliente.getContato()
                .stream()
                .map(ContatoMapper::fromEntity)
                .toList();

    }

    public void deletarCliente(Long id){

            clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

            clienteRepository.deleteById(id);

    }

    public ClienteResponseDTO atualizarCliente(ClienteRequestDTO data, Long id){

        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        cliente = ClienteMapper.toEntity(data);

        cliente = clienteRepository.save(cliente); 

        return ClienteMapper.fromEntity(cliente);
    }

}
