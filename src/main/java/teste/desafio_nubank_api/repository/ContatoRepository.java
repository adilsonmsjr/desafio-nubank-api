package teste.desafio_nubank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.desafio_nubank_api.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
