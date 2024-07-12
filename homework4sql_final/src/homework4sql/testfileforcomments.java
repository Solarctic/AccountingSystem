/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package homework03sql;

import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 *
 * @author SOL
 */
public class testfileforcomments {

    /**
     * @param args the command line arguments
     */
    
                //test file does not actuall do anything but open frmConnect
                //important stuff will be added here
    public static void main(String[] args) {
        // TODO code application logic here
        testfileforcomments pro = new testfileforcomments();
        pro.createConnection();
        //test connection
    }

    void createConnection() {
        //important to create connection
        try {
            //Class.forName("com.mysql.cj.jdbc.Drive");
            //change for admin and root users
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema", "root", "123456");
            //System.out.println("Connection successful");
        } catch (SQLException ex) {
            Logger.getLogger(testfileforcomments.class.getName()).log(Level.SEVERE, null, ex);
        }

        //new frmConnect().setVisible(true);
        
        //function to display sql error so its easier to check
//            public boolean isDbConnected(Connection con) {
//        try {
//            return con != null && !con.isClosed();
//        } catch (SQLException e) {
//            e.printStackTrace(); // Print the stack trace for debugging
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
//        }
//
//        return false;
//    }
    }
}
