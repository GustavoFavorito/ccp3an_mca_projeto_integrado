package br.usjt.ccp3an_mca_projeto_integrado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExtensaoArquivo {
	
	@Id
    @GeneratedValue
    private Long id;
	private String descricao;
	@ManyToOne
	private TipoDeArquivo tipoDeArquivo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoDeArquivo getTipoDeArquivo() {
		return tipoDeArquivo;
	}
	public void setTipoDeArquivo(TipoDeArquivo tipoDeArquivo) {
		this.tipoDeArquivo = tipoDeArquivo;
	}
}
