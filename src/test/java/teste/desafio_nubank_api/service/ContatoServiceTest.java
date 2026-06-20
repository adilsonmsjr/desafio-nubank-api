package teste.desafio_nubank_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import teste.desafio_nubank_api.dto.ContatoRequestDTO;
import teste.desafio_nubank_api.dto.ContatoResponseDTO;
import teste.desafio_nubank_api.entity.Cliente;
import teste.desafio_nubank_api.entity.Contato;
import teste.desafio_nubank_api.exception.ResourceNotFoundException;
import teste.desafio_nubank_api.repository.ClienteRepository;
import teste.desafio_nubank_api.repository.ContatoRepository;


@ExtendWith(MockitoExtension.class) //habilita mockito
public class ContatoServiceTest {

    //criar os repositórios
@Mock
private ContatoRepository contatoRepository;

@Mock
private ClienteRepository clienteRepository;

//construtor
@InjectMocks
private ContatoService contatoService;

@Test
void deveCriarContatoQuandoClienteExistir() {

     /*ARRANGE
    preparar objetos
    configurar mocks */

    //criar request dto
    ContatoRequestDTO dto =
        new ContatoRequestDTO(
                "Maria",
                "99999-9999",
                1L);

    // criar cliente
    Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setCpf("123");

    // criar contato
    Contato contato = new Contato();
        contato.setId(1L);
        contato.setNomeContato("Maria");
        contato.setTelefone("99999-9999");
        contato.setCliente(cliente);

    
    //chamar o método que será testado 

    //configurar comportamento dos mocks
    when(clienteRepository.findById(1L))
        .thenReturn(Optional.of(cliente));

    when(contatoRepository.save(any(Contato.class)))
        .thenReturn(contato);  
        
    /* act
    verificar se o retorno está correto */

    // executar o método
    ContatoResponseDTO response =
        contatoService.criarContato(dto);
        
    /* assert
    verificar se os mocks foram utilizados corretamente */

    // verificar os resultados (assert)

    assertEquals(1L, response.id());

    assertEquals(
        "Maria",
        response.nomeContato());

    assertEquals(
        "99999-9999",
        response.telefone());

    // verificar interações com o banco (verify)
    
        verify(clienteRepository)
        .findById(1L);

        verify(contatoRepository)
        .save(any(Contato.class));
}

@Test
void deveLancarExceptionQuandoClienteNaoExistir(){

    //Arrange

    //criar request dto
    ContatoRequestDTO dto =
        new ContatoRequestDTO(
                "Maria",
                "99999-9999",
                1L);

    // Configurar o mock para simular que o cliente não existe
    when(clienteRepository.findById(1L))
            .thenReturn(Optional.empty());

    // ACT + ASSERT

    ResourceNotFoundException exception =
            assertThrows(
                    ResourceNotFoundException.class,
                    () -> contatoService.criarContato(dto)
            );

    // Verificar a mensagem da exceção
    assertEquals(
            "Cliente não encontrado.",
            exception.getMessage()
    );

    // Verificar se buscou o cliente
    verify(clienteRepository)
            .findById(1L);

    // Garantir que o save nunca foi chamado
    verify(contatoRepository, never())
            .save(any(Contato.class));



    
}
}
