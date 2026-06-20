package teste.desafio_nubank_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teste.desafio_nubank_api.dto.ClienteRequestDTO;
import teste.desafio_nubank_api.dto.ClienteResponseDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;
import teste.desafio_nubank_api.entity.Cliente;
import teste.desafio_nubank_api.entity.Contato;
import teste.desafio_nubank_api.repository.ClienteRepository;
import teste.desafio_nubank_api.repository.ContatoRepository;


@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

@Mock
private ClienteRepository clienteRepository;

@Mock
private ContatoRepository contatoRepository;

@InjectMocks
private ClienteService clienteService;

@Test
void criarCliente(){

    ClienteRequestDTO dto = ClienteTestFactory.dtoValido();

    Cliente clienteSalvo = ClienteTestFactory.clienteValido();
    
    when(clienteRepository.save(any(Cliente.class)))
    .thenReturn(clienteSalvo);

    ClienteResponseDTO response = clienteService.criarCliente(dto);

    assertNotNull(response);
    assertEquals("Lukaku", response.nome());
    assertEquals("1234", response.cpf());
    assertEquals(1L, response.id());

    verify(clienteRepository)
        .save(any(Cliente.class));

}

@Test
void listarCliente(){

    // ARRANGE
    Cliente cliente1 = new Cliente();
    cliente1.setId(1L);
    cliente1.setNome("Lukaku");
    cliente1.setCpf("1234");
    cliente1.setContato(new ArrayList<>());

    Cliente cliente2 = new Cliente();
    cliente2.setId(2L);
    cliente2.setNome("Messi");
    cliente2.setCpf("9999");
    cliente2.setContato(new ArrayList<>());

    List<Cliente> listaClientes = List.of(cliente1, cliente2);

    when(clienteRepository.findAll())
            .thenReturn(listaClientes);

    // ACT
    List<ClienteResponseDTO> response = clienteService.listarCliente();

    // ASSERT
    assertNotNull(response);
    assertEquals(2, response.size());

    assertEquals("Lukaku", response.get(0).nome());
    assertEquals("1234", response.get(0).cpf());

    assertEquals("Messi", response.get(1).nome());
    assertEquals("9999", response.get(1).cpf());

    verify(clienteRepository).findAll();

}

@Test
void listarContato(){

    // ARRANGE
    Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Lukaku");
        cliente.setCpf("1234");
        cliente.setContato(new ArrayList<>());

    Contato contato1 = new Contato();
        contato1.setId(1L);
        contato1.setNomeContato("Maria");
        contato1.setTelefone("99999-9999");
        contato1.setCliente(cliente);

    Contato contato2 = new Contato();
        contato2.setId(2L);
        contato2.setNomeContato("Jose");
        contato2.setTelefone("99999-9999");
        contato2.setCliente(cliente);

    List<Contato> listaContatos = List.of(contato1, contato2);

    when(clienteRepository.findById(1L))
        .thenReturn(Optional.of(cliente));

    cliente.setContato(listaContatos);

    // ACT
    List<ContatoResponseDTO> response = clienteService.listarContato(1L);

    // ASSERT
    assertNotNull(response);
    assertEquals(2, response.size());

    assertEquals(1L, response.get(0).id());
    assertEquals("Maria", response.get(0).nomeContato());
    assertEquals("99999-9999", response.get(0).telefone());
    assertEquals(1L, response.get(0).clienteId());

    assertEquals(2L, response.get(1).id());
    assertEquals("Jose", response.get(1).nomeContato());
    assertEquals("99999-9999", response.get(1).telefone());
    assertEquals(1L, response.get(1).clienteId());

    verify(clienteRepository).findById(1L);

}

@Test
void deletarCliente(){

    Cliente clienteSalvo = ClienteTestFactory.clienteValido();
    
    when(clienteRepository.findById(1L))
        .thenReturn(Optional.of(clienteSalvo));

    clienteService.deletarCliente(1L);

    verify(clienteRepository).findById(1L);

    verify(clienteRepository)
        .deleteById(1L);
}

@Test
void atualizarCliente(){

    ClienteRequestDTO dto =
        new ClienteRequestDTO(
                "Messi",
                "9999");

    Cliente clienteExistente = ClienteTestFactory.clienteValido();

    Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(1L);
        clienteAtualizado.setNome(dto.nome());
        clienteAtualizado.setCpf(dto.cpf());
        clienteAtualizado.setContato(new ArrayList<>());
    
    when(clienteRepository.findById(1L))
        .thenReturn(Optional.of(clienteExistente));

    when(clienteRepository.save(any(Cliente.class)))
        .thenReturn(clienteAtualizado);

    ClienteResponseDTO response =
    clienteService.atualizarCliente(dto, 1L);

    assertNotNull(response);

    assertEquals(1L, response.id());
    assertEquals("Messi", response.nome());
    assertEquals("9999", response.cpf());
    
    verify(clienteRepository).findById(1L);

    verify(clienteRepository)
        .save(any(Cliente.class));
}

public class ClienteTestFactory {

    
    public static Cliente clienteValido() {
        Cliente c = new Cliente();
        c.setId(1L);
        c.setNome("Lukaku");
        c.setCpf("1234");
        c.setContato(new ArrayList<>());
        return c;
    }

    public static ClienteRequestDTO dtoValido() {
        return new ClienteRequestDTO("Lukaku", "1234");
    }
}

}
