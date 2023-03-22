/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.teste;
import java.io.*;
import com.java.classe.basica.DaoFile;
import com.java.classe.basica.DaoSql;
import com.java.classe.basica.TrataParametrosXml;



/**
 *
 * @author Paulinho
 */
public class ConverteArquivo {

    /**
     * @param args the command line arguments
     * @return 
     */
    
    public static void main(String[] args) {
        //TrataParametrosXml pxml = new TrataParametrosXml();
        
        //pxml.gerarXml("postgres", "postgres", "jdbc:postgresql://localhost:5432", "db_igreja_adv");
        if (new DaoFile(null).getConfig()){
            System.out.println("Tem arquivo.");
        }
        
        System.out.println("Caminho: " + new DaoFile(null).getUrl()); 
        
        //System.out.println("Caminho: " + new DaoFile(null).abrirArquivo()); 
        //System.out.println("Sequencia: " + new DaoSql(null, 'P').getSeqArq()); 
        /*String linhaArquivo = "";
        int count = 0;
        try {
            InputStreamReader arqStream = new InputStreamReader(new FileInputStream("c:\\Temp\\teste_import.csv"), "UTF-8");
            BufferedReader buffer = new BufferedReader(arqStream);
            OutputStreamWriter gravaArq = new OutputStreamWriter(new FileOutputStream("c:\\Temp\\teste_import_new.csv"), "UTF-8");
            linhaArquivo = buffer.readLine();
            System.out.println("Codificação: " + arqStream.getEncoding());
            do {
                System.out.println("Linha:" + linhaArquivo);
                gravaArq.write(linhaArquivo + "\n");
                linhaArquivo = buffer.readLine();
                count = count + 1;
            } while (buffer.ready());
            gravaArq.close();
            
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    */}
    
}

