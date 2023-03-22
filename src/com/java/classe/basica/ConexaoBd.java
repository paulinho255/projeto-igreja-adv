/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.classe.basica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Paulinho
 */

public class ConexaoBd {
 EntradaPadrao ep = null;
 Connection conn = null;
 TrataParametrosXml pxml = null;
 ConteinerXml cxml = null;
 
 public ConexaoBd(){
   cxml = new ConteinerXml();
   pxml = new TrataParametrosXml();
   pxml.lerXml();
   /*"jdbc:postgresql://localhost:5432/db_igreja_adv", "postgres", "postgres"*/
 }
        
 public Connection getConexao(){
        
    try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_igreja_adv", "postgres", "postgres");
    } catch(SQLException e){
       ep = new EntradaPadrao();
       ep.exibeMensagem("Erro de conexão com o banco."+ e.getMessage(), "Conexão Banco", 1);
    }    
     return conn;   
    }
}
