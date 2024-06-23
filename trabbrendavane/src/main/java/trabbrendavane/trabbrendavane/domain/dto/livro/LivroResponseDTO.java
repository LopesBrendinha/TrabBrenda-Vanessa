package trabbrendavane.trabbrendavane.domain.dto.livro;

import trabbrendavane.trabbrendavane.domain.Enum.EGeneroLivro;
import trabbrendavane.trabbrendavane.domain.model.Usuario;

public class LivroResponseDTO {
    private Long id;
    private String autor;
    private String titulo;
    private EGeneroLivro genero;
    private String editora;
    private String cidade;
    private int ano;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public EGeneroLivro getGenero() {
        return genero;
    }
    public void setGenero(EGeneroLivro genero) {
        this.genero = genero;
    }
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    

}
