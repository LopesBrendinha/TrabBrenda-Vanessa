package trabbrendavane.trabbrendavane.domain.Enum;
public enum EGeneroLivro {
    RELIGIOSOS ("Religiosos"),
    ROMANCE ("Romance"),
    COMEDIA ("Comedia"),
    TERROR ("Terror"),
    POESIA ("Poesia"),
    DRAMA ("Drama"),
    DIDATICO ("Didatico");


    private String genero;

    EGeneroLivro(String genero) {
        this.genero= genero;
    }

    public String getGenero(){
        return this.genero;
    }
}
