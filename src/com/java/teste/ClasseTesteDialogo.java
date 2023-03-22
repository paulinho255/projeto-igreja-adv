/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.teste;
import com.java.classe.basica.DaoSql;
import com.java.classe.basica.DaoFile;
import com.java.classe.basica.EntradaPadrao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import com.java.classe.basica.Pessoa;
/**
 *
 * @author Paulinho
 */
public class ClasseTesteDialogo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int count = 0;
        
        while (count < 3){
            String numero = JOptionPane.showInputDialog(null, "Digite somente letras.", "Campo alfabetico", 3);
            boolean confere = numero.matches("[a-z[A-Z][0-9][\\s][^*&#@$!%+-?:;/=.,]]{2,}");
            
            if (!confere){
                JOptionPane.showMessageDialog(null, "Caractere digitado não e uma letra.");
            } else {
                JOptionPane.showMessageDialog(null, "Caractere valido.");
            }
          count = count + 1;
        }
        /* String query = "select p.idpessoa" +
                       "      ,p.nome_pessoa" +
                       "      ,p.sobrenome" +
                       "      ,p.mes_ano_aniversario" +
                       "      ,p.religiao" +
                       "      ,p.responsavel" +
                       "      ,p.obs" +
                       "      ,p.prog_estudo" +
                       "      ,p.ind_estudo" +
                       "      ,c.dt_contato" +
                       "      ,c.num_celular" +
                       "      ,c.num_fixo" +
                       "      ,c.ind_zap" +
                       "      ,c.operadora" +
                       "      ,c.email" +
                       "      ,e.endereco" +
                       "      ,e.cep" +
                       "      ,e.bairro" +
                       "      ,e.cidade" +
                       "  from pessoa p  " +
                       "     , contato c" +
                       "     , enderecos e " +
                       "   where p.contato_idcontato = c.idcontato " +
                       "     and p.enderecos_cod_endereco = e.cod_endereco";
        
        DaoSql ds = new DaoSql(query,'P');
        List<Pessoa> pessoas = ds.sqlConsulta();
        //EntradaPadrao in = new EntradaPadrao();
        int i;
        
        for (i =0; i < pessoas.size();i++)
        {
            System.out.println("Valor: "+pessoas.get(i).getNome()+" Posição: "+pessoas.get(i).getContador());
        }
       SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
        Date data = new Date();
        String dt = null;
        try {
            dt = sdf.format(data);
        } catch (Exception e) {
            System.out.println("Erro na conversao: " + e.getMessage());
        }
        if(in.exibeConfirmacao(null, "Possui Whatapp ?", "Opçoes de Celular.", 1, 3)){
           in.exibeMensagem("Confirmado! " + dt, "Resultado", 2); 
        } else {
          in.exibeMensagem("Não Confirmado! ", "Resultado", 1);  
        }
        int val = in.exibeConfirmacao(null, "Possui Whatapp ?", "Opçoes de Celular.", 1, 3);
        in.exibeMensagem("Confirmado! " + in.getDataLocal(null,null) + val, "Resultado", 0); 
        */
        
    }
    
}
