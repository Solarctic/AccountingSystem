/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package homework4sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SOL
 */
public class frmGameDatabase extends frmSuperList {

    /**
     * Creates new form frmGameDatabase
     */
    public frmGameDatabase(Connection sqlpass){
        super(sqlpass);
        initComponents();

        DefaultTableModel tableModel = (DefaultTableModel) tblGameDatabase.getModel();
        
        try {
            Statement stmt = con.createStatement();
            //change name of schema for your datbase,
            ResultSet rs = stmt.executeQuery("SELECT * FROM project.game");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("game_id");
                String game_title = rs.getString("game_title");
                String publisher_id = rs.getString("publisher_id");
                String release_date = rs.getString("release_date");
                //add to table
                tableModel.addRow(new Object[]{id, game_title, publisher_id, release_date});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblGameDatabase = new javax.swing.JTable();
        btnAddGame = new javax.swing.JButton();
        btnUpdateGame = new javax.swing.JButton();
        btnDeleteGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblGameDatabase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "game_id", "game_title", "publisher_id", "release_date"
            }
        ));
        jScrollPane1.setViewportView(tblGameDatabase);

        btnAddGame.setText("Add Game");
        btnAddGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGameActionPerformed(evt);
            }
        });

        btnUpdateGame.setText("Update Game");
        btnUpdateGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateGameActionPerformed(evt);
            }
        });

        btnDeleteGame.setText("Delete Game");
        btnDeleteGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdateGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnAddGame)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateGame)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteGame)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGameActionPerformed
        // TODO add your handling code here:
        getCng().newGame(getCon(), getTable()); //.newGame(getCon(), getTable());
      //getCnf().newContact(getCon(), getTable(), false, null);
    }//GEN-LAST:event_btnAddGameActionPerformed

    private void btnUpdateGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateGameActionPerformed
        // TODO add your handling code here:
        getCng().editGame(getCon(), getTable());
    }//GEN-LAST:event_btnUpdateGameActionPerformed

    private void btnDeleteGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteGameActionPerformed
        // TODO add your handling code here:
        if(getCng().deleteGame(getCon(), getTable()))
        {
            refreshTable();
        }
    }//GEN-LAST:event_btnDeleteGameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmGameDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGameDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGameDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGameDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddGame;
    private javax.swing.JButton btnDeleteGame;
    private javax.swing.JButton btnUpdateGame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGameDatabase;
    // End of variables declaration//GEN-END:variables
}
