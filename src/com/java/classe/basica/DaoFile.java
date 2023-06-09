package com.java.classe.basica;
//
//  Generated by StarUML(tm) Java Add-In

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//
//  @ Project : @JDBC
//  @ File Name : DaoFile.java
//  @ Date : @06/08/2016
//  @ Author : @Paulinho
//
// String caminho = new File("/resources/logo.png").getCanonicalPath();

public class DaoFile {
    private String nomArquivo;
    private String path;
    private File f = null;
    private JFileChooser jf = null;
    private FileNameExtensionFilter fx = null;
    private InputStreamReader arqStream = null;
    private BufferedReader buffer = null;
    private OutputStreamWriter gravaArq = null;
    private EntradaPadrao ep = null;
    
    public DaoFile(String URLArquivo){
     this.path = URLArquivo;
     ep = new EntradaPadrao();
    }
    
    public void lerArquivo(String nomArq) {
    
    }
    
    public void gravaArquivo() {
    
    }
    
    public String converteArquivo(File pArq){
        String linhaArquivo;
        String vNomeArqAnt, vNomeArqNovo;
        int count = 0;
        int numSeq = new DaoSql(null, 'P').getSeqArq();
        
        vNomeArqAnt = pArq.getAbsolutePath();
        vNomeArqNovo = vNomeArqAnt.substring(0,vNomeArqAnt.lastIndexOf('.'));
        vNomeArqNovo = vNomeArqNovo + "_new_" + "0"+numSeq+ ".csv";
        pArq = new File(vNomeArqNovo);
        
        try {
            //vNomeArq = pCaminhoArq.getName();
            //vCaminho = pCaminhoArq.getParent();
            //vCaminhoArq = vCaminho+ "\\" +vNomeArq;
            
            /*Arquivo de entrada.*/
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(vNomeArqAnt), "Cp1252"));
            linhaArquivo = buffer.readLine();

            if (linhaArquivo != null){ 
                if(pArq.exists()){
                   pArq.delete();
                }
                 /*Arquivo de saida.*/
                gravaArq = new OutputStreamWriter(new FileOutputStream(vNomeArqNovo), "UTF-8");
                do {
                    System.out.println("Linha:" + linhaArquivo);
                    gravaArq.write(linhaArquivo + "\n");
                    linhaArquivo = buffer.readLine();
                    count = count + 1;
                } while (buffer.ready());
                gravaArq.close();
                return vNomeArqNovo;
                
            } else {
                return null;
            }
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public String getCaminho(){
        f = new File("."); 
        String caminho = "";
        
        try {
            caminho = f.getCanonicalPath() + path;
        } catch (IOException ex) {
            System.out.println("Erro ao recuperar o caminho do relatorio! " + ex.getMessage());
        }
        return caminho;
    }
     
    public InputStream getJasper(){
        /*URL arquivo = getClass().getResource( "/com/java/relatorios/RelMembros.jasper" );	    
        try {
            JasperReport jasper = (JasperReport) JRLoader.loadObject( arquivo );
            return jasper;
        } catch (JRException ex) {
            System.out.println("Erro ao recuperar o caminho do relatorio! " + ex.getMessage());
        }
        return null;*/
        InputStream relatorio = getClass().getResourceAsStream( "/com/java/relatorios/RelMembros.jasper" );
        return relatorio;
        
    }
    
    public String salvarArquivo(){
      String caminhoArq;
      int retorno;
              
      jf = new JFileChooser(new File(System.getProperty("user.home")));  
      fx = new FileNameExtensionFilter("Arquivos *.PDF", "pdf");
      
      jf.setFileFilter(fx);
      jf.setAcceptAllFileFilterUsed(false);
     //jf.addChoosableFileFilter(fx);
      
      retorno = jf.showSaveDialog(jf);

      if (retorno == JFileChooser.APPROVE_OPTION){
          File arq = jf.getSelectedFile();
          caminhoArq = arq.getAbsolutePath() + ".pdf";
      } else {
          caminhoArq = null;  
      }
      
     return caminhoArq;
    }
    
    public String abrirArquivo(){
        String caminhoArq = null;
        int retorno;
        
        jf = new JFileChooser(new File(System.getProperty("user.home")));  
        fx = new FileNameExtensionFilter("Arquivos *.CSV", "csv");
      
        jf.setFileFilter(fx);
        jf.setAcceptAllFileFilterUsed(false);
      
        retorno = jf.showOpenDialog(jf);

        if (retorno == JFileChooser.APPROVE_OPTION){
            File arq = jf.getSelectedFile();
            caminhoArq = converteArquivo(arq);
            
            if(caminhoArq != null){
               return caminhoArq.replace("\\", "/");
            }
        } else {
            return caminhoArq;  
        }
       return caminhoArq;
    }
    /*
    public URL getUrl(){
        URL resource = getClass().getResource("/com/java/xml/parametros/");
        return resource;
    }*/
    public String getUrl(){
        URL resource = getClass().getResource("/com/java/xml/parametros/");
        
        String vCaminho = "";

        try {
            f = new File(resource.toURI());
            vCaminho = f.getCanonicalPath() + "\\config.xml";
            f = new File(vCaminho);
            if (f.isFile()){
                vCaminho = f.getName();
            }
        } catch (URISyntaxException | IOException ex) {
            ep.exibeMensagem("Erro ao recuperar o caminho da arquivo. "
                            +"Classe: "+DaoFile.class.getName()+" \nErro: "
                            +ex.getMessage()
                            ,"Caminho Invalido", 0);
        }
        return vCaminho;
    }
    
    public boolean  getConfig(){
        URL resource = getClass().getResource("/com/java/xml/parametros/");
        boolean vExiste = false;
        
        try {
            f = new File(resource.toURI());
            
            if (new File(f.getCanonicalPath() + "\\config.xml").exists()){
                vExiste = true;
            }
        } catch (URISyntaxException | IOException ex) {
            ep.exibeMensagem("Erro ao recuperar o caminho da arquivo. "
                            +"Classe: "+DaoFile.class.getName()+" \nErro: "
                            +ex.getMessage()
                            ,"Caminho Invalido", 0);
        }
        return vExiste;
    }
    
}
