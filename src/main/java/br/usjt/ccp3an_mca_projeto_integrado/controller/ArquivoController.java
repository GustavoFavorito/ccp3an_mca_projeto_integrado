package br.usjt.ccp3an_mca_projeto_integrado.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.TipoDeArquivo;
import br.usjt.ccp3an_mca_projeto_integrado.service.IArquivoService;
import br.usjt.ccp3an_mca_projeto_integrado.service.ITipoDeArquivoService;

@Controller
@RequestMapping("arquivo")
public class ArquivoController {

	@Autowired
	IArquivoService arquivoService;
	
	@GetMapping("upload")
    public String upload() {
		return "arquivo/upload";
    }
	
	@PostMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException{

		Arquivo arquivo = arquivoService.guardar(file);
		
		arquivoService.inserir(arquivo);
		
        return "arquivo/upload";
    }
}
