/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.classe.basica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulinho
 */
public class TrataParametrosXml {
    FileInputStream fileIn = null;
    FileOutputStream fileOut = null;
    Properties propArq = null;
    DaoFile df = null;
    EntradaPadrao ep = null;
    ConteinerXml cxml = null;
    
    public TrataParametrosXml(){
        
    }
    
    public void gerarXml(String pUsuario, String pSenha, String pUrl, String pNomeBd ){
        df = new DaoFile(null);
        propArq = new Properties();
        ep = new EntradaPadrao();
        String vCaminho = df.getUrl().toString()+"config.xml";
        String teste = vCaminho.replaceAll("[^a-zA-Z0-9_-]","/");
        
        propArq.setProperty("usuario", pUsuario);
        propArq.setProperty("senha", pSenha);
        propArq.setProperty("urlbd", pUrl);
        propArq.setProperty("nomebd", pNomeBd);
        
        try { 
            fileOut = new FileOutputStream(teste);
            propArq.storeToXML(fileOut, "ARQUIVO DE CONFIGURAÇÃO:","ISO-8859-1");
            fileOut.close();
            
        } catch (FileNotFoundException ex) {
            ep.exibeMensagem("Erro na gravação do arquivo XML "+ ex.getMessage(), "Erro ao Gravar", 1);
        } catch (IOException ex) {
            ep.exibeMensagem("Erro de entrada e saida de dados."+ ex.getMessage(), "Erro de IO", 1);
        }
    
    }
    
    public void lerXml(){
        cxml = new ConteinerXml();
        ep = new EntradaPadrao();
        df = new DaoFile(null);
        propArq = new Properties();
        
        try {
            fileIn = new FileInputStream(df.getUrl().toString()+"config.xml");
            propArq.loadFromXML(fileIn);
            
        } catch (FileNotFoundException ex) {
            ep.exibeMensagem("Erro na leitura do arquivo XML "+ ex.getMessage(), "Erro ao Ler", 1);
        } catch (IOException ex) {
            ep.exibeMensagem("Erro de entrada e saida de dados."+ ex.getMessage(), "Erro de IO", 1);
        }
        /*Implementar aqui os metodos de leitura do xml*/
        cxml.setUser(propArq.getProperty("usuario"));
        cxml.setPasswd(propArq.getProperty("senha"));
        cxml.setBdUrl(propArq.getProperty("urlbd"));
        cxml.setBdName(propArq.getProperty("nomebd"));
    }
}
