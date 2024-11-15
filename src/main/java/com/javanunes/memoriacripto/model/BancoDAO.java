/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanunes.memoriacripto.model;
import com.javanunes.memoriacripto.controller.Nucleo;
import com.javanunes.memoriacripto.view.Exibicao;
import java.sql.*;


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

public class BancoDAO {
   
    
    private String tabelaMoedas(){
        // no SQLLite não precisa de AUTOINCREMENT
        return "CREATE TABLE IF NOT EXISTS moedas (\n" +
        "     id INTEGER PRIMARY KEY,\n" +
        "     nome VARCHAR(30) NOT NULL,\n" +
        "     vale VARCHAR(11) NOT NULL,\n" +
        "     data VARCHAR(11) NOT NULL,\n" +
        "     investi VARCHAR(11) NOT NULL,\n" +
        "     dono VARCHAR(100)\n" +
        ");";
    }
    
    private Connection conecta(){
        Exibicao exb = new Exibicao();
        Nucleo ncl = new Nucleo();
        String caminhoBanco ="jdbc:sqlite:"+ncl.retornaCaminhoArquivoBancoDados();
        try{
           return DriverManager.getConnection(caminhoBanco);
        }
        catch(Exception e){
            exb.print("Ops! falha no banco/arquivo db!\n");
        }
        return null;
    }
    
    
    private int executaQuery(String query){
       if(query.isEmpty())
           return 1;
       Exibicao exb = new Exibicao();
       try{
          Connection db = conecta();
          Statement stmt = db.createStatement();
          stmt.execute(query);
          stmt.close();
          db.close();
          return 0;
       }
       catch(Exception e){
          exb.print("Comando de banco não aceito pois "+e);
       }
       return 2;
    }
    
    public int criaTabelaMoedaSeNaoExistir(){
        Exibicao exb = new Exibicao();
        try{
            executaQuery(tabelaMoedas());
            return 0;
        }
        catch(Exception e){
             exb.print("Ops! falha ao criar tabela pois "+e);
             return 9;
        }
    }
   
    public void insereDadosTabela(Moeda moedas){
        Exibicao exb = new Exibicao();
        try{
            String query = "INSERT INTO moedas (nome,vale,data,investi,dono) VALUES (?,?,?,?,?) ";
            Connection db =  conecta();
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setString(1, moedas.getNome());
            pstmt.setString(2, moedas.getVale());
            pstmt.setString(3, moedas.getNaData());
            pstmt.setString(4, moedas.getInvesti());
            pstmt.setString(5, moedas.getDono());
            pstmt.executeUpdate();
            pstmt.close();
            db.close();
        }
        catch(Exception e){
            exb.print("Ops! Nao foi possivel inserir dados de moeda!\n");
        }
    }
    
    public String retornaResultadoMoeda(String nomeMoeda){
        Exibicao exb = new Exibicao();
        StringBuilder resultado = new StringBuilder();
        String query;
        
        if(nomeMoeda.isEmpty()){
           query = "SELECT * FROM moedas";
        }
        else{
           query = "SELECT * FROM moedas WHERE nome='"+nomeMoeda+"'"; 
        }
        
        try{
            Connection db = conecta();
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String id, nome, vale, data, investi, dono;
         
            
            while (rs.next()) {
                id =      rs.getString("id");
                nome =    rs.getString("nome");
                vale =    rs.getString("vale");
                data =    rs.getString("data");
                investi = rs.getString("investi");
                dono =    rs.getString("dono");
                
                resultado.append("[");
                resultado.append(id);
                resultado.append("] ");
                resultado.append("Moeda: ");
                resultado.append(nome);
                resultado.append(", ");
                resultado.append("Valia: ");
                resultado.append(vale);
                resultado.append("   ");
                resultado.append("em: ");
                resultado.append(data);
                resultado.append(" ");
                resultado.append("quando você investiu: ");
                resultado.append(investi);
                resultado.append(" , senhor(a) ");
                resultado.append(dono);
                resultado.append("\n");
            }
            rs.close();
            stmt.close();
            db.close();
            return resultado.toString();
        }
        catch(Exception e){
            return "erro de:"+e; 
        }
    }
    
    /**
     * 
     * @param registro informa o id a ser deletado no banco de dados
     */
    public void apagaRegistro(String registro){
        try{
            if(!registro.isEmpty() && registro.length() < 7){
                String query = "DELETE FROM moedas WHERE id="+registro ;
                executaQuery(query);
            }
        }
        catch(Exception e){
            
        }
    }
    
    
}
