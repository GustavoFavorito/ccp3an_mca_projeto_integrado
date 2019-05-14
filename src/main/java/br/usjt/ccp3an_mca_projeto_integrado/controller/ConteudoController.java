package br.usjt.ccp3an_mca_projeto_integrado.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.TipoDeArquivo;
import br.usjt.ccp3an_mca_projeto_integrado.service.IArquivoService;

@Controller
@RequestMapping("conteudo")
public class ConteudoController {

	@Autowired
	IArquivoService arquivoService;
	
	@GetMapping("criar")
    public ModelAndView upload() {
		ModelAndView mv = new ModelAndView ("conteudo/criar");
		
		Map<TipoDeArquivo, List<Arquivo>> arquivosPorTipoDeArquivo = arquivoService.carregarArquivosPorTipoDeArquivo();
		
		mv.addObject("arquivosPorTipoDeArquivo", arquivosPorTipoDeArquivo);
		
		return mv;
    }
}
