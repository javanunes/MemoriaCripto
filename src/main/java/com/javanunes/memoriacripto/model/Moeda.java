/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanunes.memoriacripto.model;
/**
 *
 * @author javanunes (itanhaem@live.com)
 *  Ferramenta de console computa o dia que você comprou cripto moedas e quanto elas valiam
 *  Para quem compra cripto moedas mas não lembra quanto valia a moeda comprada no dia que você fez
 *  um determinado investimento, permitindo você ter noção se teve lucro ou não em outros dias
 *  As Exchanges escondem esses dados ou dificultam você encontrá-lo justamente para você não saber
 *  se lucrou ou não com os investimentos.
 *  É algo simples que você poderia fazer pelo Excel, mas eu decidi fazer num aplicativo específico mesmo
 */
public class Moeda {
     
     private String nome ="";
     private String vale = "";
     private String naData ="";
     private String investi ="";
     private String dono = "";

    public Moeda(String nome, String vale, String naData, String investi, String dono){
        this.nome = nome;
        this.vale = vale;
        this.naData = naData;
        this.investi = investi;
        this.dono = dono;
    } 
     
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVale() {
        return vale;
    }

    public void setVale(String vale) {
        this.vale = vale;
    }

    public String getNaData() {
        return naData;
    }

    public void setNaData(String naData) {
        this.naData = naData;
    }

    public String getInvesti() {
        return investi;
    }

    public void setInvesti(String investi) {
        this.investi = investi;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }
     
     
     
}
