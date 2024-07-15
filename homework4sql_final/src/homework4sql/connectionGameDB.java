/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework4sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", username, password);
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

//    public frmGame(Connection sqlpass, String id, String game, String publisher_id,
//        String release_date, boolean bNew, DefaultTableModel tbl)
    //txtGame_id
    //txtTitle
    //txtPublisher_id
    //txt_Release_date
    public void newGame(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmGame(con, null, null, null, null, true, model).setVisible(true);
    }

    public boolean saveGame(Connection con, boolean bNew,
            javax.swing.JTextField txtGame_id, javax.swing.JTextField txtTitle, javax.swing.JTextField txtPublisher_id,
            javax.swing.JTextField txtRelease_date) {
        // TODO add your handling code here:
        //save the data to the table so add the data to the table

        String id = txtGame_id.getText();
        String title = txtTitle.getText();
        String publisher = txtPublisher_id.getText();
        String release_date = txtRelease_date.getText();

        String sql_query;
        String sql_publisher;
        String sql_customercontact;

//         if (bNew) {
//            sql_query = "INSERT INTO contact(contact_type) "
//                    + "VALUES (?)";
//        } else {
//            sql_query = "UPDATE contact " + "SET contact_type = ? " + "WHERE contact_id = ?";
//        }
        if (bNew) {
            sql_query = "INSERT INTO game(game_title, publisher_id, release_date) "
                    + "VALUES (?, ?, ?)";

            sql_publisher = "INSERT INTO publisher(publisher_name) " + "VALUES (?)";
        } else {
            sql_query = "UPDATE game " + "SET game_title = ?, " + " publisher_id = ?, "
                    + "  release_date = ?, " + "WHERE game_id = ?";

            sql_publisher = "UPDATE publisher " + "SET publisher_name = ? " + "WHERE publisher_id = ?";
        }

        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);
            PreparedStatement stmt_pub = con.prepareStatement(sql_publisher);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, title);
                stmt.setString(2, publisher);
                stmt.setString(3, release_date);

                stmt_pub.setString(1, publisher);

                stmt_pub.executeUpdate();
                //System.out.println(rowsAffected + " row(s) affected");
                int rowsAffected = stmt.executeUpdate();

                //for adding
                ResultSet customer_rs = con.prepareStatement("SELECT * FROM CUSTOMER").executeQuery();
                ResultSet cc_rs = con.prepareStatement("SELECT * FROM CONTACT").executeQuery();

                String id_add = "";
                String contact_id_add = "";

                while (customer_rs.next()) {
                    id_add = customer_rs.getString("customer_id");
                }
                while (cc_rs.next()) {
                    contact_id_add = cc_rs.getString("contact_id");
                }

                PreparedStatement ccstmt = con.prepareStatement("INSERT INTO customercontact(contact_id, customer_id) "
                        + "VALUES (?, ?)");

                ccstmt.setInt(1, Integer.parseInt(contact_id_add));
                ccstmt.setInt(2, Integer.parseInt(id_add));

                ccstmt.executeUpdate();

                //System.out.println(rowsAffected + " row(s) affected");
                ccstmt.close();
                stmt_pub.close();
                stmt.close();

            } else {

                stmt.setString(1, title);
                stmt.setString(2, publisher);
                stmt.setString(3, release_date);
                //stmt.setInt(6, Integer.parseInt(id));

                stmt_pub.setString(1, publisher);
                stmt_pub.setInt(2, Integer.parseInt(publisher));

                int rowsAffected = stmt.executeUpdate();
                stmt_pub.executeUpdate();
                //System.out.println(rowsAffected + " row(s) affected");

                stmt_pub.close();
                stmt.close(); // Close the statement
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
            return false;
        }
        //frmCustomerList.getCustomer(con);

        this.dispose();
        return true;
    }

    public void getDefaultGameDatabase(Connection con, DefaultTableModel tbl_c) {
        try {
            Statement stmt = con.createStatement();

//            ResultSet rs = stmt.executeQuery("SELECT c.*, co.contact_type "
//                    + "FROM lab_3.customer c "
//                    + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
//                    + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id");
//            ResultSet rs = stmt.executeQuery("SELECT c.*, co.contact_type, cc.contact_id "
//                    + "FROM lab_3.customer c "
//                    + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
//                    + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id");
            ResultSet rs = stmt.executeQuery("SELECT * FROM project.game");

//            SELECT c.*, co.contact_type, cc.contact_id
            //FROM lab_3.customer c
            //LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id
            //LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id
            //ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.customer");
            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("game_id");
                String game_Name = rs.getString("game_title");
                String publisher_Name = rs.getString("publisher_id");
                String date = rs.getString("release_date");
                //add to table
                tbl_c.addRow(new Object[]{id, game_Name, publisher_Name, date});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }

    public void getGameDatabase(Connection con, javax.swing.JTable tblCustomers) {

        DefaultTableModel tableModel = (DefaultTableModel) tblCustomers.getModel();
        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM project.game");

            
            rs = stmt.executeQuery("SELECT project.game.*, project.publisher.publisher_name FROM project.game"
                    + "INNER JOIN project.publisher ON project.game.publisher_ID = project.publisher.publisher_ID");

//            SELECT c.*, co.contact_type, cc.contact_id
            //FROM lab_3.customer c
            //LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id
            //LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id
            //ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.customer");
            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("game_id");
                String game_Name = rs.getString("game_title");
                String publisher_Name = rs.getString("publisher_id");
                String date = rs.getString("release_date");
                //add to table
                tableModel.addRow(new Object[]{id, game_Name, publisher_Name, date});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }

    public void editGame(Connection con, javax.swing.JTable tbl_Game) {
        // TODO add your handling code here:
        //fetch data
//        TableModel tableModel = tblCustomers.getModel();
//        JTable jTableModel = new JTable(tblCustomers);

        TableModel tableModel = tbl_Game.getModel();
        DefaultTableModel model = (DefaultTableModel) tbl_Game.getModel();

        if (tableModel.getRowCount() != -1) {
            int row = tbl_Game.getSelectedRow();
            if (row <= -1) {
                JOptionPane.showMessageDialog(null, "Cannot Edit Row: Row Unselected");
                return;
            }
            //System.out.println("row ID: " + row);

            String id = tableModel.getValueAt(row, 0).toString();
            String game_Name = tableModel.getValueAt(row, 1).toString();
            String publisher_Name = tableModel.getValueAt(row, 2).toString();
            String date = tableModel.getValueAt(row, 3).toString();

            //String query = "UPDATE Books SET availability = ? WHERE author = ? AND bookname = ?";
            new frmGame(con, id, game_Name, publisher_Name, date, false, model).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Table Value");
        }
    }

    public boolean deleteGame(Connection con, javax.swing.JTable tblCustomers) {
        // TODO add your handling code here:

        TableModel tableModel = tblCustomers.getModel();
        DefaultTableModel model = (DefaultTableModel) tblCustomers.getModel();
        int row = tblCustomers.getSelectedRow();

        if (model.getRowCount() == -1) {
            JOptionPane.showMessageDialog(null, "Cannot delete empty table!");
            return false;
        }

        if (tableModel.getRowCount() != -1) {
            if (row <= -1) {
                JOptionPane.showMessageDialog(null, "Cannot Edit Row: Row Unselected");
                return false;
            }
        }

        if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to delete this data?", "Deletion of data", JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM game WHERE game_id = ?";

            //change this or smthing
            //int row = tblCustomers.getSelectedRow();
            //System.out.println(row);
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Cannot delete empty row!");
                return false;
            }

            String customerId = (String) tableModel.getValueAt(row, 0);
            try {

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(customerId));
                stmt.executeUpdate();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace(); // Print the stack trace for debugging
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
            }
        }
        return true;
    }

    public void getPublisher(Connection con, javax.swing.JTable tblContacts) {
        DefaultTableModel tableModel = (DefaultTableModel) tblContacts.getModel();
        try {
            Statement stmt = con.createStatement();
            //change name of schema for your datbase,
            ResultSet rs = stmt.executeQuery("SELECT * FROM project.publisher")

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("publisher_id");
                String contact = rs.getString("publisher_name");
                //add to table
                tableModel.addRow(new Object[]{id, contact});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }

    public boolean savePublisher(Connection con, boolean bNew, javax.swing.JTextField txt_pubid, javax.swing.JTextField txt_pubname) {
        // TODO add your handling code here:
        String id = txt_pubid.getText();
        String name = txt_pubname.getText();

        String sql_query;
        String e_query;

        if (bNew) {
            sql_query = "INSERT INTO publisher(publisher_name) "
                    + "VALUES (?)";
        } else {
            sql_query = "UPDATE publisher " + "SET publisher_name = ? " + "WHERE publisher_id = ?";
        }

        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, name);
            } else {

                stmt.setString(1, name);
                stmt.setInt(2, Integer.parseInt(id));
            }

            int rowsAffected = stmt.executeUpdate();
            //System.out.println(rowsAffected + " row(s) affected");

            stmt.close(); // Close the statement

        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
            return false;
        }
        //frmCustomerList.getCustomer(con);

        this.dispose();
        return true;
    }

    public void newPublisher(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmPublisher(con, null, null, true, model).setVisible(true);
    }

    public void updatePublisher(Connection con, javax.swing.JTable tblContacts) {
        // TODO add your handling code here:
        TableModel tableModel = tblContacts.getModel();
        DefaultTableModel model = (DefaultTableModel) tblContacts.getModel();
        if (tableModel.getRowCount() != -1) {
            int row = tblContacts.getSelectedRow();
            if (row <= -1) {
                JOptionPane.showMessageDialog(null, "Cannot Edit Row: Row Unselected");
                return;
            }
            //System.out.println("row ID: " + row);

            String id = tableModel.getValueAt(row, 0).toString();
            String name = tableModel.getValueAt(row, 1).toString();
            new frmPublisher(con, id, name, false, model).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Table Value");
        }
    }
    
    public boolean deletePublisher(Connection con, javax.swing.JTable tblContacts) {

        DefaultTableModel model = (DefaultTableModel) tblContacts.getModel();

        if (model.getRowCount() <= -1) {
            JOptionPane.showMessageDialog(null, "Cannot delete empty table!");
            return false;
        }

        int row = tblContacts.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Cannot delete empty row!");
            return false;
        }

        if (JOptionPane.showConfirmDialog(null, "Are you sure do you want to delete this data?", "Deletion of data", JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM publisher WHERE publisher_id = ?";

            TableModel tableModel = tblContacts.getModel();
            //change this or smthing
            //System.out.println(row);

            String contactId = (String) tableModel.getValueAt(row, 0);
            try {

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(contactId));
                stmt.executeUpdate();
                stmt.close();

                return true;

            } catch (SQLException e) {
                e.printStackTrace(); // Print the stack trace for debugging
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
            }
        }
        return false;
    }

    
        public void getDefaultPublisher(Connection con, DefaultTableModel tbl_c) {

        try {
            Statement stmt = con.createStatement();
            //change name of schema for your datbase,
            ResultSet rs = stmt.executeQuery("SELECT * FROM project.publisher");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("publisher_id");
                String contact = rs.getString("publisher_name");
                //add to table
                tbl_c.addRow(new Object[]{id, contact});
            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }
}
