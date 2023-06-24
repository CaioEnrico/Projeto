package br.inatel.cdg;

import java.util.Scanner;

public class Main {

    public static void main(String[] Args){

        Database data = new Database();

        Scanner sc = new Scanner(System.in);
        Aviao aviao = new Aviao();
        Passageiro h = new Passageiro();
        boolean aux = true;

        while(aux){

            System.out.println("Bem vindo ao Aeroporto!");
            System.out.println("1 - Cadastrar aviao");
            System.out.println("2 - Cadastrar passageiro");
            System.out.println("3 - Cadastrar empregado");
            System.out.println("4 - Listar passageiro e aviao");
            System.out.println("5 - Fazer check-in");
            System.out.println("6 - Lista de pessoas nos avioes");
            System.out.println("7 - Fazer check-out");
            System.out.println("8 - Sair");
            System.out.println("");
            int menu = sc.nextInt();

            switch(menu){
                case 1:
                    sc.nextLine();
                    System.out.println("Insira o nome do Aviao: ");
                    aviao.setNome(sc.nextLine());
                    System.out.println("Quantos assentos o aviao possui?");
                    aviao.setQtdAssentos(sc.nextInt());
                    data.insertAviao(aviao);
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Entre com o nome do passeiro: ");
                    String nome = sc.nextLine();
                    System.out.println("Entre com o CPF do passeiro: ");
                    String cpf = sc.nextLine();
                    System.out.println("Entre com a idade do passeiro: ");
                    int idade = sc.nextInt();
                    Pessoa passageiro = new Passageiro(nome, cpf,"Passageiro",idade);
                    data.insertPessoa(passageiro);
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Entre com o nome do empregado: ");
                    String n = sc.nextLine();
                    System.out.println("Entre com o CPF do empregado: ");
                    String cpf1 = sc.nextLine();
                    System.out.println("Entre com a idade do empregado: ");
                    int i = sc.nextInt();
                    Pessoa empregado = new Empregado(n, cpf1,"Empregado",i);
                    data.insertPessoa(empregado);
                    break;
                case 4:
                    System.out.println("Avioes disponiveis: ");
                    data.researchAviao();
                    System.out.println("Pessoas para check-in: ");
                    data.researchPassageiro();
                    break;
                case 5:
                    System.out.println("Insira o id do Aviao e o id do passageiro: ");
                    data.insertPAviao(sc.nextInt(),sc.nextInt());
                    h.fazCheckIn();
                    break;
                case 6:
                    data.researchListaAviao();
                    break;
                case 7:
                    System.out.println("Insira o ID de quem ira fazer check-out");
                    data.deletePessoa(sc.nextInt());
                    h.fazCheckOut();
                    break;
                case 8:
                    aux = false;
                    break;
            }

        }


    }
}

