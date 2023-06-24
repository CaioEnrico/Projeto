package br.inatel.cdg;

import checkInOut.FazCheckIn;
import checkInOut.FazCheckOut;

public class Passageiro extends Pessoa implements FazCheckIn, FazCheckOut {
    public Passageiro(String nome, String cpf, String ocupacao, int idade) {
        super(nome, cpf,ocupacao, idade);
    }

    public Passageiro(){

    }
    @Override
    public void fazCheckIn(){
        System.out.println("Check-in feito no aeroporto!");
    }

    @Override
    public void fazCheckOut() {
        System.out.println("Check-out feito no aeroporto!");
    }
}
