package br.usjt.ccp3an_mca_projeto_integrado.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Conteudo {
    @Id
    @GeneratedValue
    private Long id;
    private String data;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Arquivo arquivo;
    @ManyToMany
    private List<Tag> tags;
    @ManyToOne
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
