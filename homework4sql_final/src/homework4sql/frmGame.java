/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package homework4sql;

import java.sql.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SOL
 */
public class frmGame extends frmSuperForm{

    /**
     * Creates new form frmGame_genre
     */
    boolean checkNew;
    String editContact_id;

    public frmGame(Connection sqlpass, String id, String firstname, String lastname,
        String address, String city, String country, String contactType, String contact_id, boolean bNew, DefaultTableModel tbl) {
        super(sqlpass);
        
        boolean check = cnf.isDbConnected(con);
        //System.out.println(check);

//        if (!check) {
//            JOptionPane.showMessageDialog(null, "SQL Connection Failed or Interrupted.");
//            return;
//        }
//        
//        setDefaultTable(tbl);
//        initComponents();
//
//        checkNew = bNew;
//
//        if (!bNew) {
//            txtCustomer_id.setText(id);
//        }
//
//        txtCustomer_first_name.setText(firstname);
//        txtCustomer_last_name.setText(lastname);
//        txtCustomer_address.setText(address);
//        txtCustomer_city.setText(city);
//        txtCustomer_country.setText(country);
//        cmbBoxContact.setSelectedItem(contactType);
//        editContact_id = contact_id;
    }

    @Override
    protected void loadData() {
        getCnf().getDefaultContact(getCon(), getDefaultTable()); // Load contact data into tblCostomers
    }

    @Override
    protected void refreshTable()
    {
        DefaultTableModel model = (DefaultTableModel) getDefaultTable();
        model.setRowCount(0);
        getCnf().getDefaultCustomer(getCon(), getDefaultTable());
    }
    //see frmMain for details
//    public boolean isDbConnected(Connection con) {
//        try {
//            return con != null && !con.isClosed();
//        } catch (SQLException ignored) {
//            System.out.println("sql failed to connect");
//        }
//        return false;
//    }


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        lblPublisher_id = new javax.swing.JLabel();
        txtPublisher_id = new javax.swing.JTextField();
        lblRelease_date = new javax.swing.JLabel();
        txtRelease_date = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtGame_id = new javax.swing.JTextField();
        lblGameID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Database - Customer Table");
        setResizable(false);

        lblTitle.setText("Title:");

        txtTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitleActionPerformed(evt);
            }
        });

        lblPublisher_id.setText("Publisher ID:");

        txtPublisher_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPublisher_idActionPerformed(evt);
            }
        });

        lblRelease_date.setText("Release Date:");

        txtRelease_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRelease_dateActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        txtGame_id.setEditable(false);
        txtGame_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGame_idActionPerformed(evt);
            }
        });

        lblGameID.setText("Game ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblRelease_date, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGameID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPublisher_id)
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPublisher_id, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRelease_date, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGame_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(txtTitle))))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblPublisher_id, lblRelease_date, lblTitle});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGame_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGameID))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPublisher_id)
                    .addComponent(txtPublisher_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRelease_date)
                    .addComponent(txtRelease_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnExit))
                .addGap(33, 33, 33))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtPublisher_id, txtRelease_date, txtTitle});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblPublisher_id, lblRelease_date, lblTitle});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnExit, btnSave});

        getAccessibleContext().setAccessibleName("Database - Customer List");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

    private void txtPublisher_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPublisher_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPublisher_idActionPerformed

    private void txtRelease_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRelease_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRelease_dateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

//        if(getCnf().saveCustomer(con, checkNew, txtCustomer_id, txtCustomer_first_name, txtCustomer_last_name, txtCustomer_city, txtCustomer_address, txtCustomer_country, 
//                cmbBoxContact, editContact_id ))
//        {
//            refreshTable();
//            this.dispose();
//        }
    }//GEN-LAST:event_btnSaveActionPerformed


    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        //exit the tab without saving data
        refreshTable();
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtGame_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGame_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGame_idActionPerformed

    /**
     * @param args the command line arguments *
     */
    //code from internet modified to check connection just in case it doesnt pass
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
            java.util.logging.Logger.getLogger(frmGame_genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGame_genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGame_genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGame_genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblGameID;
    private javax.swing.JLabel lblPublisher_id;
    private javax.swing.JLabel lblRelease_date;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtGame_id;
    private javax.swing.JTextField txtPublisher_id;
    private javax.swing.JTextField txtRelease_date;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables

}
