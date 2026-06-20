package teste.desafio_nubank_api.service;

import org.springframework.stereotype.Service;

import teste.desafio_nubank_api.dto.ContatoRequestDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;
import teste.desafio_nubank_api.entity.Cliente;
import teste.desafio_nubank_api.entity.Contato;
import teste.desafio_nubank_api.exception.ResourceNotFoundException;
import teste.desafio_nubank_api.mapper.ContatoMapper;
import teste.desafio_nubank_api.repository.ClienteRepository;
import teste.desafio_nubank_api.repository.ContatoRepository;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ClienteRepository clienteRepository;

    public ContatoService(ContatoRepository contatoRepository, ClienteRepository clienteRepository){
        this.contatoRepository = contatoRepository;
        this.clienteRepository = clienteRepository;
    }

    public ContatoResponseDTO criarContato(ContatoRequestDTO dto){

        Cliente cliente = clienteRepository.findById(dto.clienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        Contato contato = new Contato();

        contato = ContatoMapper.toEntity(dto);
        contato.setCliente(cliente);

        contato = contatoRepository.save(contato);

        return ContatoMapper.fromEntity(contato);
        
    }



}
