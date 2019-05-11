package br.usjt.ccp3an_mca_projeto_integrado.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.repository.IArquivoRepository;

@Service
public class ArquivoService implements IArquivoService{

	@Autowired
	IArquivoRepository arquivoRepo;
	
	@Autowired
	IExtensaoArquivoService extensaoArquivoService;
	
	public Arquivo guardar(MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		String nomeOriginal = multipartFile.getOriginalFilename();
		String caminhoArquivo = "/home/vinribs/Documents/fileServer/";
		
		multipartFile.transferTo(new File(caminhoArquivo+nomeOriginal));
		
		String extensao = extrairExtensao(nomeOriginal);
		
		Arquivo arquivo = new Arquivo();
		arquivo.setCaminho(caminhoArquivo);
		arquivo.setExtensao(extensaoArquivoService.carregarPorDescricao(extensao));
		arquivo.setNome(extrairNome(nomeOriginal, extensao));
		
		return arquivo;
	}
	
	public void inserir(Arquivo arquivo) {
		arquivoRepo.save(arquivo);
	}
	
	private String extrairExtensao(String nomeOriginal) {
		
		String extensao = ""; 
		Boolean capturar = false;
		
		for(char l : nomeOriginal.toCharArray()){
			
			if(capturar) {
				extensao += l;
			}
			
			if(l == '.'){
				extensao = "";
				capturar = true;
			}
		}
		
		return extensao;
	}
	
	private String extrairNome(String nomeOriginal, String extensao) {
		return nomeOriginal.replace("."+extensao, "");
	}
}
