/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanunes.memoriacripto.controller;

import com.javanunes.memoriacripto.model.BancoDAO;
import com.javanunes.memoriacripto.model.Moeda;
import com.javanunes.memoriacripto.view.Exibicao;
import java.util.Scanner;

/**
 *
 * @author JavaNunes Rosenberg 
 *  Ferramenta de console computa o dia que você comprou cripto moedas e quanto elas valiam
 *  Para quem compra cripto moedas mas não lembra quanto valia a moeda comprada no dia que você fez
 *  um determinado investimento, permitindo você ter noção se teve lucro ou não em outros dias
 *  As Exchanges escondem esses dados ou dificultam você encontrá-lo justamente para você não saber
 *  se lucrou ou não com os investimentos.
 *  É algo simples que você poderia fazer pelo Excel, mas eu decidi fazer num aplicativo específico mesmo
 */

public class Nucleo {
    
    
       public String retornaCaminhoArquivoBancoDados(){
           String caminhoPadrao="";
           String sistemaHospedeiro = System.getProperty("os.name");
           if(sistemaHospedeiro.indexOf("Linux")> -1){
               caminhoPadrao = System.getenv("HOME") + "/Documentos/arquivocriptomoedasinvestidas.db";
               return caminhoPadrao;
           }
           if(sistemaHospedeiro.indexOf("Windows")> -1){
               caminhoPadrao = System.getenv("USERPROFILE") + "\\Documents\\arquivocriptomoedasinvestidas.db";
               return caminhoPadrao;
           }
           return "/tmp/";
       }
    
    
       public void pegarMoedaInvestidaAgora(){
           Exibicao exb = new Exibicao();    
           Scanner teclado = new Scanner(System.in);
           String nome, vale, data, investi, dono;
           exb.print("Nome da moeda:\n");
           nome = teclado.nextLine();

           exb.print("Quanto vale hoje:\n");
           vale = teclado.nextLine();

           exb.print("Data atual:\n");
           data = teclado.nextLine();

           exb.print("Fez o investimento de:\n");
           investi = teclado.nextLine();

           dono = System.getenv("USER");
           Moeda med = new Moeda(nome, vale, data, investi, dono);
           BancoDAO bcd = new BancoDAO();
           bcd.insereDadosTabela(med);
     }
       
     public void listarMoeda(String moeda){
         BancoDAO bcd = new BancoDAO();
         Exibicao exb = new Exibicao();
         exb.print(bcd.retornaResultadoMoeda(moeda));
     } 
     
     public void listarTodasMoedas(String moeda){
         BancoDAO bcd = new BancoDAO();
         Exibicao exb = new Exibicao();
         exb.print("\n------------------------------------------------------------\n");
         exb.print(bcd.retornaResultadoMoeda(""));
     } 
     
     public void pegarListarMoedas(){
          Exibicao exb = new Exibicao();    
          Scanner teclado = new Scanner(System.in);
          String moeda = "";
          exb.print("Que moeda deseja pesquisa:\n");
          moeda = teclado.nextLine();
          if(!moeda.isEmpty()){
              if(moeda.length() < 101){
                  exb.print("\n------------------------------------------------------------\n");
                  listarMoeda(moeda);
              }
          }
     }
     
     public int checagemBanco(){
         BancoDAO bcd = new BancoDAO();
         return bcd.criaTabelaMoedaSeNaoExistir();
     }
     
     public void apaga(){
          Exibicao exb = new Exibicao();
          Scanner teclado = new Scanner(System.in);
          BancoDAO bcd = new BancoDAO();
          exb.print("Digite ID a ser apagado: \n");
          String registroBanco = teclado.nextLine();
          if(!registroBanco.isEmpty() && registroBanco.length() < 7){
              if( registroBanco.matches("\\d") ){
                  if(exb.confirma("Deseja realmente apagar esse registro de operação de moeda?")){
                      bcd.apagaRegistro(registroBanco);
                      exb.print("Registro "+  registroBanco + " apagado pra sempre!\n");
                  }
              }
          }
          
     }
       
}
