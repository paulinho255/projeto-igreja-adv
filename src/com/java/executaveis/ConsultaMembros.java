/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.executaveis;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.java.classe.basica.*;
import java.util.List;

/**
 *
 * @author Paulinho
 */
public class ConsultaMembros extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaHnd
     */
    private String vNumCep;
    private String vNomReligiao;
    private String vNomBairro;
            
    private JTable tabela = null;
    private DefaultTableModel modelo = null;
    private List<Pessoa> pessoas = null;
    private List<Contato> contatos = null;
    private List<Enderecos> enderecos = null;
    private DaoSql ds = null;
            
    public ConsultaMembros() {
        initComponents();
        consultaTabela("","","");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
    }
    
    private void consultaTabela(String pNomBairro, String pCodRua, String pNomReligiao) {
        
        String query = "select p.idpessoa"                                                                   +
                       "      ,p.nome_pessoa"                                                                +
                       "      ,to_char(p.mes_ano_aniversario,'dd/mm') as mes_ano_aniversario"                +
                       "      ,p.religiao"                                                                   +
                       "      ,p.responsavel"                                                                +
                       "      ,p.obs"                                                                        +
                       "      ,p.prog_estudo"                                                                +
                       "      ,p.ind_estudo"                                                                 +
                       "      ,p.faixa_etaria"                                                               +
                       "      ,p.txt_origem"                                                                 +
                       "      ,c.idcontato "                                                                 +
                       "      ,to_char(c.dt_contato,'dd/mm/yyyy') as dt_contato"                             +
                       "      ,c.num_celular"                                                                +
                       "      ,c.num_fixo"                                                                   +
                       "      ,c.ind_zap"                                                                    +
                       "      ,c.operadora"                                                                  +
                       "      ,c.email"                                                                      +
                       "      ,e.cod_endereco"                                                               +  
                       "      ,e.endereco"                                                                   +
                       "      ,e.cep"                                                                        +
                       "      ,e.bairro"                                                                     +
                       "      ,e.cidade"                                                                     +
                       "      ,e.cod_rua"                                                                    +
                       "      ,e.txt_complemento"                                                            +  
                       "  from pessoa p  "                                                                   +
                       "     , contato c"                                                                    +
                       "     , enderecos e "                                                                 +
                       "   where p.contato_idcontato = c.idcontato "                                         +
                       "     and p.enderecos_cod_endereco = e.cod_endereco";
                       
                    if (!pNomBairro.equals("")){
                       query = query + " and upper(e.bairro) like '%"+pNomBairro.toUpperCase()+"%'";
                    }
                    if (!pNomReligiao.equals("")){
                       query = query + " and upper(p.religiao) like '%"+pNomReligiao.toUpperCase()+"%'"; 
                    }
                    if (!pCodRua.equals("")){
                        query = query + " and e.cod_rua = to_number('"+pCodRua+"','999999999')";
                    }
                        query = query + " order by idpessoa ";
                        
        modelo = new DefaultTableModel();
        ds = new DaoSql(query,'P');
        pessoas = ds.sqlConsulta();
        contatos = ds.sqlConsultaCo();
        enderecos = ds.sqlConsultaEnd();
        tabela = new JTable(modelo);
        
        
        /*Colunas da tabela*/
        modelo.addColumn("Matricula");
        modelo.addColumn("Nome");
        modelo.addColumn("Aniversario");
        modelo.addColumn("TelFixo");
        modelo.addColumn("Celular");
        modelo.addColumn("Whatsapp");
        modelo.addColumn("Operadora");
        modelo.addColumn("Email");
        modelo.addColumn("Miss.Resp");
        modelo.addColumn("Religiao");
        modelo.addColumn("Programa");
        modelo.addColumn("Em Estudo");
        modelo.addColumn("Observação");
        modelo.addColumn("Endereço");
        modelo.addColumn("Complemento");
        modelo.addColumn("Bairro");
        modelo.addColumn("CEP");
        modelo.addColumn("CodigoRua");
        modelo.addColumn("UF");
        
        
        /*Definindo o tamanho das colunas*/
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(8).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(9).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(10).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(11).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(12).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(13).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(14).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(15).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(16).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(17).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(18).setPreferredWidth(20);
        
        int cont;
        
        for (cont =0; cont < pessoas.size();cont++)
        {
            modelo.addRow( new Object[] {
                pessoas.get(cont).getId()
               ,pessoas.get(cont).getNome()
               ,pessoas.get(cont).getAniversario()
               ,contatos.get(cont).getNumFixo()
               ,contatos.get(cont).getNumCelular()
               ,contatos.get(cont).getIndZap()
               ,contatos.get(cont).getNomeOperadora()
               ,contatos.get(cont).getEndEmail()
               ,pessoas.get(cont).getResponsavel()
               ,pessoas.get(cont).getReligiao()
               ,pessoas.get(cont).getPrograma()
               ,pessoas.get(cont).getIndEstudo()
               ,pessoas.get(cont).getObservacao()
               ,enderecos.get(cont).getEndereco()
               ,enderecos.get(cont).getTxt_complamento()
               ,enderecos.get(cont).getBairro()
               ,enderecos.get(cont).getCep()
               ,enderecos.get(cont).getCod_rua()
               ,enderecos.get(cont).getCidade()
                    
                       });
        }
        jScrollPane1.setViewportView(tabela);
        tabela = null;
        modelo = null;
        pessoas = null;
        enderecos = null;
        contatos = null;
        ds = null;
        query = "";
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        optCep = new javax.swing.JRadioButton();
        optReligiao = new javax.swing.JRadioButton();
        txtConsultaReligiao = new javax.swing.JTextField();
        txtConsultaCodRua = new javax.swing.JTextField();
        txtConsultaBairro = new javax.swing.JTextField();
        optBairro = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Busca Avançada");
        setBackground(new java.awt.Color(153, 255, 255));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa de Missionarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrar Pesquisa: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        buttonGroup4.add(optCep);
        optCep.setText("Cod. Rua:");
        optCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optCepActionPerformed(evt);
            }
        });

        buttonGroup4.add(optReligiao);
        optReligiao.setText("Religião:");
        optReligiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optReligiaoActionPerformed(evt);
            }
        });

        buttonGroup4.add(optBairro);
        optBairro.setText("Bairro:");
        optBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optBairroActionPerformed(evt);
            }
        });

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(optCep)
                            .addComponent(txtConsultaCodRua, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConsultaReligiao)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(optReligiao)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(optBairro)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtConsultaBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(optCep)
                        .addGap(1, 1, 1)
                        .addComponent(txtConsultaCodRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtConsultaReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(optReligiao)
                        .addGap(22, 22, 22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optBairro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(txtConsultaBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/java/logo/logoiasd2.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 566, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optCepActionPerformed
        // TODO add your handling code here:
        txtConsultaCodRua.setEditable(true);
        txtConsultaBairro.setEditable(false);
        txtConsultaBairro.setText("");
        txtConsultaReligiao.setEditable(false);
        txtConsultaReligiao.setText("");
    }//GEN-LAST:event_optCepActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Botão filtrar.
        if (!txtConsultaCodRua.getText().equals("")){
            consultaTabela("",txtConsultaCodRua.getText(),"");
        }
        if (!txtConsultaBairro.getText().equals("")){
            consultaTabela(txtConsultaBairro.getText(),"","");
        }
        if (!txtConsultaReligiao.getText().equals("")){
            consultaTabela("","",txtConsultaReligiao.getText());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void optBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optBairroActionPerformed
        // TODO add your handling code here:
        txtConsultaBairro.setEditable(true);
        txtConsultaCodRua.setEditable(false);
        txtConsultaCodRua.setText("");
        txtConsultaReligiao.setEditable(false);
        txtConsultaReligiao.setText("");
    }//GEN-LAST:event_optBairroActionPerformed

    private void optReligiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optReligiaoActionPerformed
        // TODO add your handling code here:
        txtConsultaReligiao.setEditable(true);
        txtConsultaCodRua.setEditable(false);
        txtConsultaCodRua.setText("");
        txtConsultaBairro.setEditable(false);
        txtConsultaBairro.setText("");
    }//GEN-LAST:event_optReligiaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton optBairro;
    private javax.swing.JRadioButton optCep;
    private javax.swing.JRadioButton optReligiao;
    private javax.swing.JTextField txtConsultaBairro;
    private javax.swing.JTextField txtConsultaCodRua;
    private javax.swing.JTextField txtConsultaReligiao;
    // End of variables declaration//GEN-END:variables
}
