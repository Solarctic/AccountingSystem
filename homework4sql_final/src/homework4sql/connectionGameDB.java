/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework4sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author SOL
 */


public class connectionGameDB extends javax.swing.JFrame {
    
        // Start of Connect
    boolean createConnection(String username, String password) {
        //important to create connection
        try {
            //Class.forName("com.mysql.cj.jdbc.Drive");
            //change localhost schema to import table * (for admin and root users)
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", username, password);
            JOptionPane.showMessageDialog(null, "SQL Connected Successfully");
            //System.out.println("Connection successful");
            new frmMain(con).setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
            return false;
        }
        return true;
    }

    public boolean isDbConnected(Connection con) {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
        }

        return false;
    }
    
}
