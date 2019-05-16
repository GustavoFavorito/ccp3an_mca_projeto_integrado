package br.usjt.ccp3an_mca_projeto_integrado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.ccp3an_mca_projeto_integrado.model.Conteudo;
import br.usjt.ccp3an_mca_projeto_integrado.model.repository.IConteudoRepository;

@Service
public class ConteudoService implements IConteudoService{

	@Autowired
	IConteudoRepository conteudoRepo; 
	
	public void inserir(Conteudo conteudo) {
		conteudoRepo.save(conteudo);
	}

}
