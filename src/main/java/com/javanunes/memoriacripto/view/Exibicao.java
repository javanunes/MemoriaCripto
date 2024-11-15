/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanunes.memoriacripto.view;

import com.javanunes.memoriacripto.controller.Nucleo;
import com.javanunes.memoriacripto.model.BancoDAO;
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
public class Exibicao {
   
   public void print(Object texto){
        System.out.print(texto);
   } 
   
   public boolean confirma(String texto){
       Scanner teclado = new Scanner(System.in);
       print(texto + " [ S / N ]\n");
       String respostaChar = teclado.nextLine();
       respostaChar = respostaChar.toLowerCase();
       if(respostaChar.equals("s")){
           return true;
       }
       return false;
   }
   
    
   private String pergunta(){
       String resposta;
       print("\ncript:\\>\n");
       Scanner teclado = new Scanner(System.in);
       resposta = teclado.nextLine();
       return resposta;
   } 
   
   
   private void interpreta(String solicitacao){
        Nucleo ncl = new Nucleo();
        switch (solicitacao) {
            case "sair":
                print("Até logo!\n");
                System.exit(0);
                break;
            case "help":
                print("help         - exibe o menu de ajuda\n");
                print("moeda        - insere uma moeda investida hoje\n");
                print("listar       - lista os investimentos em moedas feitos\n");
                print("listar todas - lista todas os investimentos de todas moedas\n");
                print("apagar       - apagar uma linha de registro especificada pelo seu id\n");
                print("arquivo      - mostra onde foi salvo os dados de cripto moedas\n");
                
                break;
            case "moeda":
                ncl.pegarMoedaInvestidaAgora();
                break;
            case "apagar":
                ncl.apaga();
                break;
            case "arquivo":
                print(ncl.retornaCaminhoArquivoBancoDados());
                break;
            case "osos":
                
                break;
            case "listar": 
                ncl.pegarListarMoedas();
                break;
            case "listar todas":
                ncl.listarTodasMoedas(solicitacao);
                break;
            case "xxx":    
            default:
               print("O que é "+ solicitacao + " ?");
               break;
        }
   }
   
   private void sempre(boolean ativo){
        while(ativo){
            interpreta(pergunta());
        }
   }
   
    public static void main(String[] args){
        BancoDAO bcd = new BancoDAO();
        Exibicao exb = new Exibicao();
        Nucleo ncl = new Nucleo();
        System.out.println( System.getProperty("os.name"));
        ncl.checagemBanco();
        exb.sempre(true);
    }
    
    
    
}
