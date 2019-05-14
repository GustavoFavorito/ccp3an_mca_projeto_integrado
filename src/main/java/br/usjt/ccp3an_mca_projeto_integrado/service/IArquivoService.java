package br.usjt.ccp3an_mca_projeto_integrado.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import br.usjt.ccp3an_mca_projeto_integrado.model.Arquivo;
import br.usjt.ccp3an_mca_projeto_integrado.model.TipoDeArquivo;

public interface IArquivoService {

	public Arquivo guardar(MultipartFile multipartFile) throws IllegalStateException, IOException;
	public void inserir(Arquivo arquivo);
	public Map<TipoDeArquivo, List<Arquivo>> carregarArquivosPorTipoDeArquivo();
}