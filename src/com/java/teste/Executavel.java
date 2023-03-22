/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.teste;

import com.java.classe.basica.EntradaPadrao;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author joao.rocha
 */
public class Executavel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)) ;
    EntradaPadrao in = new EntradaPadrao();
    String nota;
    String nome;
    float total = 0.0f;
    float media = 0.0f;
    
    nome = in.lerLinhaGrafico(null, "Digite seu nome: ", "Nome do aluno", (short)3);
    
    for (int i =1;i<3;i++){
      nota = in.lerLinhaGrafico(null,"Digite a: "+i+" Nota","Nota do semestre.",(short)3);
      total = total + Float.parseFloat(nota);
    }
    media = total / 2;
    if (media >= 7.0){
      in.exibeMensagem("Aluno: "+nome+" Esta aprovado!!!", "Resultado.", 1);
    } else if (media < 7.0 && media >= 3.0 ){
      in.exibeMensagem("Aluno: "+nome+" Esta em prova final!!!", "Resultado.", 1);  
    } else {
      in.exibeMensagem("Aluno: "+nome+" Esta reprovado!!!", "Resultado.", 0);    
    }
    
    in.exibeMensagem("O aluno " + nome + " Sua media e de: "+ media, "Exibe nome do aluno.",1);
    System.out.printf("O programa terminou com o nome: %s \n", nome);
    }
    
}
