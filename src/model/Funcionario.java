package model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {

    private Integer id;
    private String nome;
    private String cargo;
    private Double salario;

    List<Dependente> dependentes = new ArrayList<>();

    public Funcionario(Integer id, String nome, String cargo, Double salario) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void adicionarDependente(Dependente dependente){
        this.dependentes.add(dependente);
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }
}
