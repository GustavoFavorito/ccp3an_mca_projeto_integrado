package br.usjt.ccp3an_mca_projeto_integrado.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.TipoDeArquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.repository.IArquivoRepository;

@Service
public class ArquivoService implements IArquivoService{

	@Autowired
	IArquivoRepository arquivoRepo;
	
	@Autowired
	ITipoDeArquivoService tipoDeArquivoService;
	
	public Arquivo guardar(MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		String nomeOriginal = multipartFile.getOriginalFilename();
		String caminhoArquivo = "/home/vinribs/Documents/fileServer/";
		
		multipartFile.transferTo(new File(caminhoArquivo+nomeOriginal));
		
		String[] nomeDividido = nomeOriginal.replace(".", ";").split(";");
		
		Arquivo arquivo = new Arquivo();
		arquivo.setCaminho(caminhoArquivo);
		arquivo.setExtensao(nomeDividido[1]);
		arquivo.setNome(nomeDividido[0]);
		arquivo.setTipoDeArquivo(encontrarTipoDeArquivo(arquivo.getExtensao()));
		
		return arquivo;
	}
	
	public void inserir(Arquivo arquivo) {
		arquivoRepo.save(arquivo);
	}
	
	private TipoDeArquivo encontrarTipoDeArquivo(String extensao) {
		Long id = (long) 0;
		
		if(extensao.equals("jpg") || extensao.equals("png"))
			id = (long)1;
		else if(extensao.equals("mp3"))
			id = (long)2;
		
		return tipoDeArquivoService.carregar(id);
	}
}
