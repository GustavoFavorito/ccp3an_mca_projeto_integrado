package br.usjt.ccp3an_mca_projeto_integrado.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.ccp3an_mca_projeto_integrado.model.Conteudo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IConteudoRepository extends JpaRepository<Conteudo, Long>{

    @Query("SELECT c FROM Conteudo c WHERE descricao LIKE %?1%")
    public List<Conteudo> buscaPorDescricao(String frase);

    @Query("SELECT c FROM Conteudo c INNER JOIN c.categoria ca WHERE ca.descricao = ?1")
    public List<Conteudo> buscaPorCategoria(String categoria);
}
