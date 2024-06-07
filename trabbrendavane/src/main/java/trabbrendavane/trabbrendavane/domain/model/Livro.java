package trabbrendavane.trabbrendavane.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import trabbrendavane.trabbrendavane.domain.Enum.EGeneroLivro;

@Entity
@Table(name = "livro")
public class Livro {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLivro")
    private Long id;
    @Column(nullable = false)
    private String autor;
    @Column(nullable = false)
    private String titulo;
    private EGeneroLivro genero;
    private String editora;
    private String cidade;
    private int ano;
    @ManyToOne
    @JoinColumn(name= "idUsuario")
    private Usuario usuario;



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
