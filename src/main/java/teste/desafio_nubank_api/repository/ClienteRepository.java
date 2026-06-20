package teste.desafio_nubank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.desafio_nubank_api.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpf(String cpf);

}
