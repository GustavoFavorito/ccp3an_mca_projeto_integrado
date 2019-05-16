package br.usjt.ccp3an_mca_projeto_integrado.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.Categoria;
import br.usjt.ccp3an_mca_projeto_integrado.model.Conteudo;
import br.usjt.ccp3an_mca_projeto_integrado.model.Tag;
import br.usjt.ccp3an_mca_projeto_integrado.model.TipoDeArquivo;
import br.usjt.ccp3an_mca_projeto_integrado.service.IArquivoService;
import br.usjt.ccp3an_mca_projeto_integrado.service.ICategoriaService;
import br.usjt.ccp3an_mca_projeto_integrado.service.IConteudoService;
import br.usjt.ccp3an_mca_projeto_integrado.service.ITagService;

@Controller
@RequestMapping("conteudo")
public class ConteudoController {

	@Autowired
	IArquivoService arquivoService;
	
	@Autowired
	ICategoriaService categoriaService;
	
	@Autowired
	ITagService tagService;
	
	@Autowired
	IConteudoService conteudoService;
	
	@GetMapping("criar")
    public ModelAndView criar() {
		ModelAndView mv = new ModelAndView ("conteudo/criar");
		
		Map<TipoDeArquivo, List<Arquivo>> arquivosPorTipoDeArquivo = arquivoService.carregarArquivosPorTipoDeArquivo();
		List<Categoria> categorias = categoriaService.carregarCategorias();
		List<Tag> tags = tagService.carregarTag();
		
		mv.addObject("arquivosPorTipoDeArquivo", arquivosPorTipoDeArquivo);
		mv.addObject("categorias", categorias);
		mv.addObject("tags", tags);
		
		return mv;
    }
	
	@PostMapping("criar")
	public String criar(@RequestParam("titulo") String titulo, @RequestParam("descricao") String descricao, 
							@RequestParam("arquivoId") Long arquivoId, @RequestParam("html") String html,
							@RequestParam("categoriaId") Long categoriaId, @RequestParam("listaTagsId") String listaTagsId,
							@RequestParam("tipoAcesso") Boolean tipoAcesso){
		
		Arquivo arquivo = new Arquivo();
		arquivo.setId(arquivoId);
		
		Categoria categoria = new Categoria();
		categoria.setId(categoriaId);
		
		List<Tag> listaTags = new ArrayList<Tag>();
		for(String item:listaTagsId.split(","))
		{
			Tag tag = new Tag();
			tag.setId(Long.parseLong(item));
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
		
		conteudoService.inserir(conteudo);
		
		return "conteudo/exibir";
	}
}
