package br.usjt.ccp3an_mca_projeto_integrado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Arquivo {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String caminho;
    @ManyToOne
    private ExtensaoArquivo extensao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ExtensaoArquivo getExtensao() {
		return extensao;
	}

	public void setExtensao(ExtensaoArquivo extensao) {
		this.extensao = extensao;
	}

	public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
