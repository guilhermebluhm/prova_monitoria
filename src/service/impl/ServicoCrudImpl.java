package service.impl;

import model.Dependente;
import model.Funcionario;
import service.ServicoCRUD;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoCrudImpl implements ServicoCRUD {

    static List<Funcionario> lista_funcionarios = new ArrayList<>();

    @Override
    public void adicionarFuncionario() {
        System.out.println("=======CADASTRAR FUNCIONARIO==========");
        //sempre que o metodo e chamado a janela do JoptionPane fica atras da janela da IDE
        try {
            Integer cod_funcionario = Integer.parseInt(JOptionPane.showInputDialog("digite o codigo do funcionario: "));

            if(checarExistenciaFuncionario(cod_funcionario)){
                JOptionPane.showMessageDialog(null, "funcionario ja cadastrado");
                return;
            }

            String nome_funcionario = JOptionPane.showInputDialog("nome do funcionario: ");
            String cargo_funcionario = JOptionPane.showInputDialog("cargo do funcionario: ");
            Double salario_funcionario = Double.parseDouble(JOptionPane.showInputDialog("salario do funcionario: "));

            Funcionario funcionario = new Funcionario(cod_funcionario,nome_funcionario,cargo_funcionario,salario_funcionario);
            lista_funcionarios.add(funcionario);

            Optional<Funcionario> first = lista_funcionarios.stream().filter(x -> x.getNome().equals(nome_funcionario)).findFirst();

            JOptionPane.showMessageDialog(null, "funcionario cadastrado: " + first.get().getNome());
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "forneça um input valido: " + e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cadastrarDependente() {
        System.out.println("=======CADASTRAR DEPENDENTE==========");
        Optional<Funcionario> funcionario = this.getInstance();
        if (funcionario.isPresent()){
            try {
                Integer cod_dependente = Integer.parseInt(JOptionPane.showInputDialog("digite o codigo do dependente: "));

                if(funcionario.get().getDependentes().stream().anyMatch(x -> x.getId().equals(cod_dependente))){
                    JOptionPane.showMessageDialog(null, "dependente ja cadastrado");
                    return;
                }

                String nome_dependente = JOptionPane.showInputDialog("nome do dependente: ");
                Dependente d = new Dependente(cod_dependente, nome_dependente);
                funcionario.get().adicionarDependente(d);
                funcionario.get().setSalario(funcionario.get().getSalario()*0.02 + funcionario.get().getSalario());
                JOptionPane.showMessageDialog(null, "dependente cadastrado: " + d.getNomeDependente()
                        + " para o funcionario: " + funcionario.get().getNome());
                return;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "forneça um input valido: " + e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Funcionario inexistente", "erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void excluirFuncionario() {
        System.out.println("=======EXCLUIR FUNCIONARIO==========");
        Optional<Funcionario> funcionario = this.getInstance();
        if(funcionario.isPresent()){
            lista_funcionarios.remove(funcionario.get());
            JOptionPane.showMessageDialog(null, "funcionario excluido");
            return;
        }
        JOptionPane.showMessageDialog(null, "Funcionario inexistente", "erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void alterarSalarioFuncionario() {
        System.out.println("=======ALTERAR SALARIO FUNCIONARIO==========");
        Optional<Funcionario> funcionario = this.getInstance();
        if(funcionario.isPresent()){
            Double salario_funcionario = Double.parseDouble(JOptionPane.showInputDialog("informe o novo salario para o funcionario: "));
            funcionario.get().setSalario(salario_funcionario);
            if(funcionario.get().getDependentes().size() != 0){
                int size = funcionario.get().getDependentes().size();
                funcionario.get().setSalario(size/100*funcionario.get().getSalario() + funcionario.get().getSalario());
            }
            JOptionPane.showMessageDialog(null, "Salario alterado com sucesso para: " + funcionario.get().getNome());
            return;
        }
        JOptionPane.showMessageDialog(null, "Funcionario inexistente", "erro", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mostrarSalarioComBonusDependente() {
        System.out.println("=======MOSTRAR BONUS DEPENDENTE/FUNCIONARIO==========");
        Optional<Funcionario> funcionario = this.getInstance();
        if(funcionario.isPresent()){

            int size = funcionario.get().getDependentes().size();
            String caminho = System.getProperty("user.home");
            //caminho que será persistido o arquivo na maquina onde o codigo será executado
            System.out.println(caminho);
            JOptionPane.showMessageDialog(null, "Funcionario" + funcionario.get().getNome() + " tem: " + size + " dependentes " + " gerando um bonus de: " + size*2+"%" + " salario ficou em: " + funcionario.get().getSalario());
            File f = new File(System.getProperty("user.home")+"/arq1.txt");

            try{

                f.createNewFile();
                FileWriter file = new FileWriter(f, false);
                PrintWriter escrita = new PrintWriter(file);
                escrita.println("numero de dependentes");
                escrita.println(size);
                escrita.println("nome funcionario");
                escrita.println(funcionario.get().getNome());
                escrita.println("bonus");
                escrita.println(size*2);
                escrita.println("salario");
                escrita.println(funcionario.get().getSalario());
                escrita.flush();
                escrita.close();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        JOptionPane.showMessageDialog(null, "Funcionario inexistente", "erro", JOptionPane.ERROR_MESSAGE);
    }

    private Optional<Funcionario> getInstance(){
        Integer cod_funcionario = Integer.parseInt(JOptionPane.showInputDialog("digite o codigo do funcionario: "));
        return lista_funcionarios.stream().filter(x -> x.getId() == cod_funcionario).findFirst();
    }

    private boolean checarExistenciaFuncionario(Integer codigo){
        return lista_funcionarios.stream().anyMatch(x -> x.getId() == codigo);
    }

}
