package br.com.serverest.utils;

import com.github.javafaker.*;


public class FakerData {


    public static String email() {
        return new Faker().internet().emailAddress();
    }

    public static String password() {
        return new Faker().internet().password(6, 8, true, true, true);
    }
    public static String nomeProduto() {
        return new Faker().commerce().productName();
    }
    public static String precoProduto() {
        return  Integer.toString(new Faker().number().numberBetween(1,1000));
    }
    public static String quantidade() {
        return Integer.toString(new Faker().number().numberBetween(1,100));
    }
    public static String descricaoProduto() {
        return "Descrição do Produto: Cor: " + new Faker().commerce().color() + " Departamento: " + new Faker().commerce().department();
    }
    public static String nomeUsuario() {
        return new Faker().name().fullName();
    }

}
