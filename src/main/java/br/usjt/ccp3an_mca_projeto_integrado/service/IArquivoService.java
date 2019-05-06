package br.usjt.ccp3an_mca_projeto_integrado.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;

public interface IArquivoService {

	public Arquivo guardar(MultipartFile multipartFile) throws IllegalStateException, IOException;
	public void inserir(Arquivo arquivo);
}
