/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.teste;

import com.java.classe.basica.DaoFile;
import com.java.relatorios.classes.RelatorioJasper;

/**
 *
 * @author Paulinho
 */
public class ProcessaRelatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     String caminhoArq;
     String nomeArq = "";
       
     caminhoArq = new DaoFile(null).salvarArquivo(); 
     System.out.println("Arquivo: " + caminhoArq);
  
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    //javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    System.out.println("Aparencia: " + info.getName()); 
            }
            
     RelatorioJasper rl = new RelatorioJasper(null,'P');
     rl.gerarRelatorio("S",null,23,null,"S","A","");
     
}
}
