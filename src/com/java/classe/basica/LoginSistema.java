/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.classe.basica;
import com.java.executaveis.JanelaPrincipal;

/**
 *
 * @author Paulinho
 */
public class LoginSistema {
  EntradaPadrao ep = null;
  DaoSql ds = null;
  
  public LoginSistema(){
      
  } 
    
  public int validaLogin(Login p_login){
   ds = new DaoSql(null, 'P');
   ep = new EntradaPadrao();
   int validacao = 3;
   
   if (p_login.getvNomeUsuario().equals("") && p_login.getvTxtSenha().equals("")){
       ep.exibeMensagem("Digite seu login e sua senha para começar.", "Login", 2);
   } else {
     if (!p_login.getvTxtSenha().equals("")){
       if (p_login.getvTxtSenha().length() < 6){
          ep.exibeMensagem("Sua senha deve ter no minimo 6 digitos.", "Senha incorreta.", 0);
       } else {
           validacao = ds.consultaLogin(p_login);
           if (validacao != 0){
             if (validacao == 1){
                 ep.exibeMensagem("A senha não confere!", "Senha Incorreta", 0);
               } 
             if (validacao == 2){
                 ep.exibeMensagem("Usuario não localizado!", "Usuario Incorreto", 0);
               }
             return validacao;
           } else {
             // Chama a janela principal.
               ep.exibeMensagem("Bem vindo ao sistema: " + p_login.getvNomeUsuario(), null, 1);
               new JanelaPrincipal().setVisible(true);
             return validacao;  
            } 
       }
       return validacao; // erro de senha.
     }  
   }
      return validacao;
  }   
  
  public void criaLogin(Login p_login){
   ds = new DaoSql(null, 'P');
   ep = new EntradaPadrao();
   int validacao;  
   
   if (p_login.getvNomeUsuario().equals("") && p_login.getvTxtSenha().equals("") && p_login.getvIndTipoUsuario().equals("")){
       ep.exibeMensagem("Campo login ou senha em branco. Favor verificar!", "Novo Login", 2);
   } else {
           validacao = ds.insereUsuario(p_login);
           if (validacao != 0){
             if (validacao == 1){
                 ep.exibeMensagem("O nome de usuario precisa ter entre 6 e 25 caracteres.", "Novo Usuario", 0);
               } 
             if (validacao == 2){
                 ep.exibeMensagem("Sua senha deve ter no minimo 6 digitos.", "Senha incorreta.", 0);
               }
             if (validacao == 3){
                 ep.exibeMensagem("Usuario ja cadastrado.", "Cadastro", 3);
             }
             if (validacao == 4){
                 ep.exibeMensagem("Erro no cadastramento dos dados. Comunique-se com o suporte do sistema.", "Erro de Gravação", 0);
             }
           } else {
             // Chama a janela principal.
               ep.exibeMensagem("Bem vindo ao sistema: " + p_login.getvNomeUsuario(), null, 1);
            } 
       } 
     }  
   }

