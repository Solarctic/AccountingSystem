/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package homework4sql;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author SOL
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */
    
    
    Connection con = null;
    connectionFunction cnf = new connectionFunction();
    connectionGameDB cng = new connectionGameDB();

    public frmMain(Connection sqlcon) {

        initComponents();
        con = sqlcon;
        //sql checker see if the connection is passed properly
        boolean check = cnf.isDbConnected(con);
        //System.out.println(check);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGamedatabase = new javax.swing.JButton();
        btnPublisher = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Database Menu");

        btnGamedatabase.setText("Game Database");
        btnGamedatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGamedatabaseActionPerformed(evt);
            }
        });

        btnPublisher.setText("Publisher Database");
        btnPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublisherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGamedatabase, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnGamedatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGamedatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGamedatabaseActionPerformed
        // TODO add your handling code here:
        boolean check = cng.isDbConnected(con);
        
        if (!check) {
            JOptionPane.showMessageDialog(null, "SQL Connection Failed or Interrupted.");
            return;
        }
        new frmGameDatabase(con).setVisible(true);
    }//GEN-LAST:event_btnGamedatabaseActionPerformed

    private void btnPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublisherActionPerformed
        // TODO add your handling code here:
        
        boolean check = cng.isDbConnected(con);
        if (!check) {
            JOptionPane.showMessageDialog(null, "SQL Connection Failed or Interrupted.");
            return;
        }
        new frmPublisherList(con).setVisible(true);
    }//GEN-LAST:event_btnPublisherActionPerformed

//    //method i found that i modified to check connection just in case the object doesnt pass
//    public boolean isDbConnected(Connection con) {
//        try {
//            return con != null && !con.isClosed();
//        } catch (SQLException e) {
//            e.printStackTrace(); // Print the stack trace for debugging
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
//        }
//
//        return false;
//    }

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGamedatabase;
    private javax.swing.JButton btnPublisher;
    // End of variables declaration//GEN-END:variables
}
