package br.usjt.ccp3an_mca_projeto_integrado.controller;

import br.usjt.ccp3an_mca_projeto_integrado.model.*;
import br.usjt.ccp3an_mca_projeto_integrado.service.IArquivoService;
import br.usjt.ccp3an_mca_projeto_integrado.service.ICategoriaService;
import br.usjt.ccp3an_mca_projeto_integrado.service.IConteudoService;
import br.usjt.ccp3an_mca_projeto_integrado.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
							@RequestParam("categoriaId") Long categoriaId, @RequestParam("listaTagsId") List<Long> listaTagsId,
							@RequestParam("tipoAcesso") Boolean tipoAcesso){
		
		html = conteudoService.gerarHtml(html, arquivoService.carregarArquivoId(arquivoId), descricao);
		
		Conteudo conteudo = conteudoService.encapsular(titulo, descricao, arquivoId, html, categoriaId, 
														listaTagsId, tipoAcesso);
		
		conteudoService.inserir(conteudo);
		
		return "conteudo/exibir";
	}

	@PostMapping("/busca-conteudo")
	public ModelAndView buscarConteudo(String descricao) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject(new Conteudo());
		List<Conteudo> conteudos = conteudoService.buscaPorDescricao(descricao);
		mv.addObject("conteudos", conteudos);
		return mv;
	}
}
