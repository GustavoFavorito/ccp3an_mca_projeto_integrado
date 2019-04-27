package br.usjt.ccp3an_mca_projeto_integrado.model.repository;

import br.usjt.ccp3an_mca_projeto_integrado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    public Usuario findOneByLoginAndSenha (String login, String senha);
}