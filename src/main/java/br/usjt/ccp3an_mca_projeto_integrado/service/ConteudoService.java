package br.usjt.ccp3an_mca_projeto_integrado.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.Categoria;
import br.usjt.ccp3an_mca_projeto_integrado.model.Conteudo;
import br.usjt.ccp3an_mca_projeto_integrado.model.Tag;
import br.usjt.ccp3an_mca_projeto_integrado.model.repository.IConteudoRepository;

@Service
public class ConteudoService implements IConteudoService{

	@Autowired
	IConteudoRepository conteudoRepo; 
	
	public void inserir(Conteudo conteudo) {
		conteudoRepo.save(conteudo);
	}

	public Conteudo encapsular(String titulo, String descricao, Long arquivoId, String html, Long categoriaId,
			List<Long> listaTagsId, Boolean tipoAcesso) {
		
		Arquivo arquivo = new Arquivo();
		arquivo.setId(arquivoId);
		
		Categoria categoria = new Categoria();
		categoria.setId(categoriaId);
		
		List<Tag> listaTags = new ArrayList<Tag>();
		for(Long item:listaTagsId)
		{
			Tag tag = new Tag();
			tag.setId(item);
			listaTags.add(tag);
		}
		
		Conteudo conteudo = new Conteudo();
		conteudo.setTitulo(titulo);
		conteudo.setDescricao(descricao);
		conteudo.setArquivo(arquivo);
		conteudo.setHtml(html);
		conteudo.setCategoria(categoria);
		conteudo.setTags(listaTags);
		conteudo.setIsPublico(tipoAcesso);
		conteudo.setData(LocalDateTime.now());
		
		return conteudo;
	}

}
