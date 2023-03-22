/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.classe.basica;
import java.awt.Component;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author joao.rocha
 */
public class EntradaPadrao {

 BufferedReader bf;    
 InputStreamReader input;
 SimpleDateFormat sdf = null;
 Date data = null;
 
public String lerLinhaTexto() throws IOException{

input = new InputStreamReader(System.in);    
bf = new BufferedReader(input);    
   
  return bf.readLine();
}

public String lerLinhaGrafico(
         Object janela
        ,String texto
        ,String titulo
        ,int tipo_mensagem)
{
  return JOptionPane.showInputDialog((Component) janela,texto,titulo,tipo_mensagem);
}

public void exibeMensagem(String p_mensagem, String p_titulo, int p_tipo){
    
    JOptionPane.showMessageDialog(null,p_mensagem,p_titulo,p_tipo); 
}
public int exibeConfirmacao(
         Component janela
        ,String texto 
        ,String titulo 
        ,int ok_cancel_op 
        ,int tipo_mensagem){
   
    int valor = JOptionPane.showConfirmDialog(janela, texto, titulo, ok_cancel_op, tipo_mensagem); 
     
     return valor; 
     
}
public String getDataLocal(String getFormatoAtual, String getValDataAtual){
    
    String dtAtual = null;
    data = new Date();
    
    if (getFormatoAtual != null){
       sdf = new SimpleDateFormat(getFormatoAtual); /*Pega o formato de data inforado.*/
    } else {
       sdf = new SimpleDateFormat("DD/MM/YYYY"); /*Formato default.*/
    }
    
    try
    {
        if (getValDataAtual != null && getFormatoAtual != null) /*Se diferente de nulo: pega a data formatada.*/
        {
            data =  sdf.parse(getValDataAtual);
            dtAtual = sdf.format(data);
        } else {
            dtAtual = sdf.format(data); /*Pega a data atual do sistema.*/  
        }
    } 
    catch (ParseException ex){
       this.exibeMensagem("Formato de data invalido: "+ex.getMessage(), "Erro", 0);
    }
        return dtAtual;
} 

public boolean validaTexto(String txtPalavra){
      
      return txtPalavra.matches("[a-z]{2,}");
  }
}
