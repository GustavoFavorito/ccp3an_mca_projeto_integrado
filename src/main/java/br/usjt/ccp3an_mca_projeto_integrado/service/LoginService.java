package br.usjt.ccp3an_mca_projeto_integrado.service;

import br.usjt.ccp3an_mca_projeto_integrado.model.Usuario;
import br.usjt.ccp3an_mca_projeto_integrado.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public String verificarPermissao(Usuario usuario) {
        return usuarioRepo.findByLoginQuery(usuario.getLogin()).getPermissao();
    }

    public boolean verificaLogin(Usuario usuario) {
        return usuarioRepo.existsByLogin(usuario.getLogin());
    }

    public boolean logar (Usuario usuario) {
        return usuarioRepo.findOneByLoginAndSenha(usuario.getLogin(),
                usuario.getSenha()) != null;
    }
}