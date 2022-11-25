package model;

public class Dependente {

    private Integer id;
    private String nomeDependente;

    public Dependente(Integer id, String nomeDependente) {
        this.id = id;
        this.nomeDependente = nomeDependente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDependente() {
        return nomeDependente;
    }

    public void setNomeDependente(String nomeDependente) {
        this.nomeDependente = nomeDependente;
    }
}
