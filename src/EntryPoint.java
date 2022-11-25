import service.impl.ServicoCrudImpl;

import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {

        int escolha = 99;
        ServicoCrudImpl impl = new ServicoCrudImpl();
        Scanner sc = new Scanner(System.in);

        while (escolha != 0){

            System.out.println("SISTEMA DE CADASTRO DE PESSOAS");
            System.out.println("Opções");
            System.out.println("1 - Cadastrar Funcionario");
            System.out.println("2 - Excluir Funcionario");
            System.out.println("3 - Cadastrar dependente");
            System.out.println("4 - Alterar salario funcionario");
            System.out.println("5 - Mostrar bonus dependente/funcionario");
            System.out.println("opção: ");

            int i = sc.nextInt();
            switch (i){
                case 0:
                    escolha = 0;
                    break;
                case 1:
                    impl.adicionarFuncionario();
                    break;
                case 2:
                    impl.excluirFuncionario();
                    break;
                case 3:
                    impl.cadastrarDependente();
                    break;
                case 4:
                    impl.alterarSalarioFuncionario();
                    break;
                case 5:
                    impl.mostrarSalarioComBonusDependente();
                    break;
            }

        }

    }

}
