/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.relatorios.classes;

import com.java.classe.basica.DaoFile;
import com.java.classe.basica.DaoSql;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Paulinho
 */
public class RelatorioJasper extends DaoSql{
    private String vTipoFiltro;
    private String vNomBairro;
    private int vCodRua;
    private String vNomReligiao;
    private String vIndEstudo;
    private String vIndTipoSaida;
    private String vCaminhoArq;

    public RelatorioJasper(String sql, char banco) {
        super(sql, banco);
    }
    
    public void gerarRelatorio( String pTipoFiltro
                               ,String pNomBairro 
                               ,int pCodRua 
                               ,String pNomReligiao 
                               ,String pIndEstudo
                               ,String pIndTipoSaida
                               ,String pCaminhoArq )
   {
       this.trataParametros( pTipoFiltro
                            ,pNomBairro
                            ,pCodRua
                            ,pNomReligiao
                            ,pIndEstudo
                            ,pIndTipoSaida
                            ,pCaminhoArq);
       
       Map parametro = new HashMap();
       DaoFile df = new DaoFile(null); 
    
       try {
            getConexao();
            
            JasperPrint print;
            parametro.put("p_tipo_filtro", vTipoFiltro);
            parametro.put("p_nom_bairro", vNomBairro);
            parametro.put("p_cod_rua", vCodRua);
            parametro.put("p_nom_religiao", vNomReligiao);
            parametro.put("p_ind_estudo", vIndEstudo);

            print = JasperFillManager.fillReport(df.getJasper(), parametro, conn);

            if (!vIndTipoSaida.equals("T")){ // Geração do relatorio em arquivo.
               if (!vCaminhoArq.equals("")){
                  JasperExportManager.exportReportToPdfFile(print,vCaminhoArq); 
               } 
            } else {
                 JasperViewer relJanela = new JasperViewer(print,false); // Geração do relatorio em tela.
                 relJanela.setTitle("Relatorio de Membros");
                 relJanela.setVisible(true);
            }
            
        } catch (JRException e){
            System.out.println("Erro na geração do relatorio! " + e.getMessage());
        }     
   }
   
    private void trataParametros( String pTipoFiltro
                                 ,String pNomBairro 
                                 ,int pCodRua 
                                 ,String pNomReligiao 
                                 ,String pIndEstudo
                                 ,String pIndTipoSaida
                                 ,String pCaminhoArq )
    {
     if (pTipoFiltro.equals("")){
         vTipoFiltro = null;
     } else {
         vTipoFiltro = pTipoFiltro;
     }
     if (pNomBairro.equals("")){
         vNomBairro = null;
     } else {
         vNomBairro = pNomBairro;
     }
     if (pCodRua < 1){
         vCodRua = 0;
     } else {
         vCodRua = pCodRua;
     }
     
     if (pNomReligiao.equals("")){
         vNomReligiao = null;
     } else {
         vNomReligiao = pNomReligiao;
     }
     if (pIndEstudo.equals("")){
         vIndEstudo = null;
     } else {
         vIndEstudo = pIndEstudo;
     }
     if (pIndTipoSaida.equals("")){
         vIndTipoSaida = null;
     } else {
         vIndTipoSaida = pIndTipoSaida;
     }
     if (pCaminhoArq.equals("")){
         vCaminhoArq = null;
     } else {
         vCaminhoArq = pCaminhoArq;
     }
    }   
   } 
   