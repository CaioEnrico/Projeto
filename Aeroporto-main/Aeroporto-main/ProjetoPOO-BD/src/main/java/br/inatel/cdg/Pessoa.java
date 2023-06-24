package br.inatel.cdg;

public abstract class Pessoa {

    private String nome;
    private String cpf;
    private String ocupacao;
    private int idade;
    public int idPessoa;

    public Pessoa(String nome, String cpf,String ocupacao, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.ocupacao = ocupacao;
        this.idade = idade;
    }

    public Pessoa(){

    }
    //Getters e Setters

    public String getCpf() {
        return cpf;
    }
    public int getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }
    public String getNome() {
        return nome;
    }
    public String getOcupacao() {
        return ocupacao;
    }
    public int getIdade() {
        return idade;
    }
}
