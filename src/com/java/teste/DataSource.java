/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Types;
import com.java.classe.basica.ConexaoBd;

/**
 *
 * @author joao.rocha
 */

public class DataSource {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    try
    {
        Class.forName("org.postgresql.Driver");
        //Connection conn = null;
        ConexaoBd con = new ConexaoBd();
        
        try ( //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_igreja_adv", "postgres", "postgres");
        //conn.setAutoCommit(false);
        //Statement st = conn.createStatement();
        //CallableStatement cs = conn.prepareCall("{ ? = call func_pesssoa_reg() }");
        //cs.registerOutParameter(1, Types.OTHER);
        //cs.setInt(2, 2);
        //cs.execute();
                PreparedStatement pstmt = con.getConexao().prepareStatement(" select nome_pessoa,religiao, obs from pessoa where upper(religiao) not in (?) limit ? ")) {
                pstmt.setString(1, "CATOLICO");
                pstmt.setInt(2, 10);
            
            //ResultSet rs = (ResultSet) cs.getObject(1);
            //ResultSet rs = st.executeQuery("select func_pesssoa_reg()");
            try (ResultSet rs = pstmt.executeQuery()) {
                //ResultSet rs = (ResultSet) cs.getObject(1);
                //ResultSet rs = st.executeQuery("select func_pesssoa_reg()");
                while (rs.next()){
                    System.out.println("Nome: " + rs.getString(1) + " Religiao: " +rs.getString(2) + " Observações: " + rs.getString(3));
                }
                rs.close();
                //st.close();
            }
        }
        //cs.close();
        con.getConexao().close();
    }catch (SQLException e){
        
    }   catch (ClassNotFoundException ex) {
        
        }
    }
}
