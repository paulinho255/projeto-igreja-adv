/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.classe.basica;

/**
 *
 * @author Paulinho
 */
public class Login {
 private String vNomeUsuario;
 private String vTxtSenha;
 private String vIndTipoUsuario;
    
  public Login(){
    
}    
    /**
     * @return the vNomeUsuario
     */
    public String getvNomeUsuario() {
        return vNomeUsuario;
    }

    /**
     * @param vNomeUsuario the vNomeUsuario to set
     */
    public void setvNomeUsuario(String vNomeUsuario) {
        this.vNomeUsuario = vNomeUsuario;
    }

    /**
     * @return the vTxtSenha
     */
    public String getvTxtSenha() {
        return vTxtSenha;
    }

    /**
     * @param vTxtSenha the vTxtSenha to set
     */
    public void setvTxtSenha(String vTxtSenha) {
        this.vTxtSenha = vTxtSenha;
    }

    /**
     * @return the vIndTipoUsuario
     */
    public String getvIndTipoUsuario() {
        return vIndTipoUsuario;
    }

    /**
     * @param vIndTipoUsuario the vIndTipoUsuario to set
     */
    public void setvIndTipoUsuario(String vIndTipoUsuario) {
        this.vIndTipoUsuario = vIndTipoUsuario;
    }
 
 
  
}
