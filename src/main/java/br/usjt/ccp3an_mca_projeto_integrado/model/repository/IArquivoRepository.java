package br.usjt.ccp3an_mca_projeto_integrado.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;

public interface IArquivoRepository extends JpaRepository<Arquivo, Long>{

}
