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
 * @author fufu
 */
class connectionFunctionGame extends javax.swing.JFrame {

    // Start of Connect
    boolean createConnection(String username, String password) {
        //important to create connection
        try {
            //Class.forName("com.mysql.cj.jdbc.Drive");
            //change localhost schema to import table * (for admin and root users)
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_3", username, password);
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

    // End of Connect
    //Start of Contact List
    public void getContact(Connection con, javax.swing.JTable tblContacts) {
        DefaultTableModel tableModel = (DefaultTableModel) tblContacts.getModel();
        try {
            Statement stmt = con.createStatement();
            //change name of schema for your datbase,
            ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.contact");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("contact_id");
                String contact = rs.getString("contact_type");
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

    public boolean deleteContact(Connection con, javax.swing.JTable tblContacts) {

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

            String sql = "DELETE FROM contact WHERE contact_id = ?";

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

    public void editGame(Connection con, javax.swing.JTable tblGame) {
        // TODO add your handling code here:
        TableModel tableModel = tblGame.getModel();
        DefaultTableModel model = (DefaultTableModel) tblGame.getModel();
        if (tableModel.getRowCount() != -1) {
            int row = tblGame.getSelectedRow();
            if (row <= -1) {
                JOptionPane.showMessageDialog(null, "Cannot Edit Row: Row Unselected");
                return;
            }
            //System.out.println("row ID: " + row);

            String id = tableModel.getValueAt(row, 0).toString();
            String title = tableModel.getValueAt(row, 1).toString();
            String publisher = tableModel.getValueAt(row, 2).toString();
            String releaseDate = tableModel.getValueAt(row, 3).toString();
            new frmGame(con, id, title, publisher, releaseDate, false, model).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Table Value");
        }
    }
    public void editPrice(Connection con, javax.swing.JTable tblPrice) {
        // TODO add your handling code here:
        TableModel tableModel = tblPrice.getModel();
        DefaultTableModel model = (DefaultTableModel) tblPrice.getModel();
        if (tableModel.getRowCount() != -1) {
            int row = tblPrice.getSelectedRow();
            if (row <= -1) {
                JOptionPane.showMessageDialog(null, "Cannot Edit Row: Row Unselected");
                return;
            }
            //System.out.println("row ID: " + row);

            String id = tableModel.getValueAt(row, 0).toString();
            String region = tableModel.getValueAt(row, 1).toString();
            String price = tableModel.getValueAt(row, 2).toString();
            new frmPrice(con, id, region, price, false, model).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Table Value");
        }
    }
    public void editPublisher(Connection con, javax.swing.JTable tblPublisher) {
        // TODO add your handling code here:
        TableModel tableModel = tblPublisher.getModel();
        DefaultTableModel model = (DefaultTableModel) tblPublisher.getModel();
        if (tableModel.getRowCount() != -1) {
            int row = tblPublisher.getSelectedRow();
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
    public void editSales(Connection con, javax.swing.JTable tblSales) {
        // TODO add your handling code here:
        TableModel tableModel = tblSales.getModel();
        DefaultTableModel model = (DefaultTableModel) tblSales.getModel();
        if (tableModel.getRowCount() != -1) {
            int row = tblSales.getSelectedRow();
            if (row <= -1) {
                JOptionPane.showMessageDialog(null, "Cannot Edit Row: Row Unselected");
                return;
            }
            //System.out.println("row ID: " + row);

            String id = tableModel.getValueAt(row, 0).toString();
            String playerCount = tableModel.getValueAt(row, 1).toString();
            String region = tableModel.getValueAt(row, 2).toString();
            new frmPrice(con, id, playerCount, region, false, model).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Table Value");
        }
    }

    // End of Contact List
    // Start of Customer List
    public void getCustomer(Connection con, javax.swing.JTable tblCustomers) {
        DefaultTableModel tableModel = (DefaultTableModel) tblCustomers.getModel();
        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT c.*, co.contact_type, cc.contact_id "
                    + "FROM lab_3.customer c "
                    + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                    + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id");

            //ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.customer");
            while (rs.next()) {
                //fetch data from sql datatable
                //search
                String id = rs.getString("customer_id");
                String first_Name = rs.getString("customer_first_name");
                String last_Name = rs.getString("customer_last_name");
                String address = rs.getString("customer_address");
                String city = rs.getString("customer_city");
                String country = rs.getString("customer_country");
                String contact = rs.getString("contact_type");
                String contact_id = rs.getString("contact_id");
                //add to table
                tableModel.addRow(new Object[]{id, first_Name, last_Name, address, city, country, contact, contact_id});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }

    public void editCustomer(Connection con, javax.swing.JTable tblCustomers) {
        // TODO add your handling code here:
        //fetch data
//        TableModel tableModel = tblCustomers.getModel();
//        JTable jTableModel = new JTable(tblCustomers);

        TableModel tableModel = tblCustomers.getModel();
        DefaultTableModel model = (DefaultTableModel) tblCustomers.getModel();

        if (tableModel.getRowCount() != -1) {
            int row = tblCustomers.getSelectedRow();
            if (row <= -1) {
                JOptionPane.showMessageDialog(null, "Cannot Edit Row: Row Unselected");
                return;
            }
            //System.out.println("row ID: " + row);

            String id = tableModel.getValueAt(row, 0).toString();
            String first_Name = tableModel.getValueAt(row, 1).toString();
            String last_Name = tableModel.getValueAt(row, 2).toString();
            String address = tableModel.getValueAt(row, 3).toString();
            String city = tableModel.getValueAt(row, 4).toString();
            String country = tableModel.getValueAt(row, 5).toString();
            String cmbBox = tableModel.getValueAt(row, 6).toString();
            String contact_id = tableModel.getValueAt(row, 7).toString();

            //String query = "UPDATE Books SET availability = ? WHERE author = ? AND bookname = ?";
            new frmCustomers(con, id, first_Name, last_Name, address, city, country, cmbBox, contact_id, false, model).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Table Value");
        }
    }

    public boolean deleteGame(Connection con, javax.swing.JTable tblGames) {
        // TODO add your handling code here:

        TableModel tableModel = tblGames.getModel();
        DefaultTableModel model = (DefaultTableModel) tblGames.getModel();
        int row = tblGames.getSelectedRow();

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

            String sql = "DELETE FROM customer WHERE customer_id = ?";

            //change this or smthing
            //int row = tblCustomers.getSelectedRow();
            //System.out.println(row);
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Cannot delete empty row!");
                return false;
            }

            String gameID = (String) tableModel.getValueAt(row, 0);
            try {

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(gameID));
                stmt.executeUpdate();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace(); // Print the stack trace for debugging
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
            }
        }
        return true;
    }

    // End of Customer 
    // Start of Contact
    public void getDefaultGame(Connection con, DefaultTableModel tbl_c) { // Change this SQL function

        try {
            Statement stmt = con.createStatement();
            //change name of schema for your datbase,
            ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.contact");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("contact_id");
                String contact = rs.getString("contact_type");
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
    
    public void getDefaultPrice(Connection con, DefaultTableModel tbl_c) { // Change this SQL function

        try {
            Statement stmt = con.createStatement();
            //change name of schema for your datbase,
            ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.contact");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("contact_id");
                String contact = rs.getString("contact_type");
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
    
    public void getDefaultPublisher(Connection con, DefaultTableModel tbl_c) { // Change this SQL function

        try {
            Statement stmt = con.createStatement();
            //change name of schema for your datbase,
            ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.contact");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("publisher_id");
                String name = rs.getString("publisher_name");
                //add to table
                tbl_c.addRow(new Object[]{id, name});
            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }

    public boolean saveContact(Connection con, boolean bNew, boolean addEContact, String e_id, javax.swing.JComboBox<String> cmBox_ContactType, javax.swing.JTextField txt_ContactID) {
        // TODO add your handling code here:
        String id = txt_ContactID.getText();
        String contact_type = cmBox_ContactType.getSelectedItem().toString();

        String sql_query;
        String e_query;

        if (bNew) {
            sql_query = "INSERT INTO contact(contact_type) "
                    + "VALUES (?)";
        } else {
            sql_query = "UPDATE contact " + "SET contact_type = ? " + "WHERE contact_id = ?";
        }

        if (addEContact) {
            e_query = "INSERT INTO customercontact(contact_id, customer_id) " + "VALUES (?, ?)";
        }

        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, contact_type);
            } else {

                stmt.setString(1, contact_type);
                stmt.setInt(2, Integer.parseInt(id));
            }

            int rowsAffected = stmt.executeUpdate();
            //System.out.println(rowsAffected + " row(s) affected");

            if (addEContact) {
                ResultSet cc_rs = con.prepareStatement("SELECT * FROM CONTACT").executeQuery();
                String contact_id_add = "";

                while (cc_rs.next()) {
                    contact_id_add = cc_rs.getString("contact_id");
                }

                PreparedStatement ccstmt = con.prepareStatement("INSERT INTO customercontact(contact_id, customer_id) "
                        + "VALUES (?, ?)");

                ccstmt.setInt(1, Integer.parseInt(contact_id_add));
                ccstmt.setInt(2, Integer.parseInt(e_id));

                ccstmt.executeUpdate();
                ccstmt.close();

            }

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

    // End of Contact
    // Start of Customer
    public void getDefaultGameDatabase(Connection con, DefaultTableModel tbl_c) { // Change this SQL function
        try {
            Statement stmt = con.createStatement();

//            ResultSet rs = stmt.executeQuery("SELECT c.*, co.contact_type "
//                    + "FROM lab_3.customer c "
//                    + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
//                    + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id");
            ResultSet rs = stmt.executeQuery("SELECT c.*, co.contact_type, cc.contact_id "
                    + "FROM lab_3.customer c "
                    + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                    + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id");

//            SELECT c.*, co.contact_type, cc.contact_id
            //FROM lab_3.customer c
            //LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id
            //LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id
            //ResultSet rs = stmt.executeQuery("SELECT * FROM lab_3.customer");
            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("customer_id");
                String first_Name = rs.getString("customer_first_name");
                String last_Name = rs.getString("customer_last_name");
                String address = rs.getString("customer_address");
                String city = rs.getString("customer_city");
                String country = rs.getString("customer_country");
                String contact = rs.getString("contact_type");
                String contact_id = rs.getString("contact_id");
                //add to table
                tbl_c.addRow(new Object[]{id, first_Name, last_Name, address, city, country, contact, contact_id});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }
    
    public void getDefaultPriceDatabase(Connection con, DefaultTableModel tbl_c) { // Change this SQL function
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.*, co.contact_type, cc.contact_id "
                    + "FROM lab_3.customer c "
                    + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                    + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("customer_id");
                String first_Name = rs.getString("customer_first_name");
                String last_Name = rs.getString("customer_last_name");
                String address = rs.getString("customer_address");
                String city = rs.getString("customer_city");
                String country = rs.getString("customer_country");
                String contact = rs.getString("contact_type");
                String contact_id = rs.getString("contact_id");
                //add to table
                tbl_c.addRow(new Object[]{id, first_Name, last_Name, address, city, country, contact, contact_id});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }

     public void getDefaultPublisherDatabase(Connection con, DefaultTableModel tbl_c) { // Change this SQL function
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.*, co.contact_type, cc.contact_id "
                    + "FROM lab_3.customer c "
                    + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                    + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id");

            while (rs.next()) {
                //fetch data from sql datatable
                String id = rs.getString("publisher_id");
                String name = rs.getString("publisher_name");
                //add to table
                tbl_c.addRow(new Object[]{id, name});

            }
            //close statement on completion
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: cannot retrieve the data table");
            return;
        }
    }
     
    public boolean saveGame(Connection con, boolean bNew,
            javax.swing.JTextField txtTitle, javax.swing.JTextField txtPublisher_id,
            javax.swing.JTextField txtRelease_date) {
        // TODO add your handling code here:
        //save the data to the table so add the data to the table

        String title = txtTitle.getText();
        String publisher = txtPublisher_id.getText();
        String releaseDate = txtRelease_date.getText();

        String sql_query;
        String sql_contact; // Change every Contact SQL to Game SQL
        String sql_customercontact;

        if (bNew) {
            sql_query = "INSERT INTO customer(customer_first_name, customer_last_name, customer_city, customer_address, customer_country) "
                    + "VALUES (?, ?, ?, ?, ?)";

            sql_contact = "INSERT INTO contact(contact_type) " + "VALUES (?)";
        } else {
            sql_query = "UPDATE customer " + "SET customer_first_name = ?, " + "    customer_last_name = ?, "
                    + "    customer_city = ?, " + "    customer_address = ?, " + "    customer_country = ? " + "WHERE customer_id = ?";

            sql_contact = "UPDATE contact " + "SET contact_type = ? " + "WHERE contact_id = ?";
        }

        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);
            PreparedStatement stmt_game = con.prepareStatement(sql_contact);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, title);
                stmt.setString(2, publisher);
                stmt.setString(3, releaseDate);

                stmt_game.executeUpdate();
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
                stmt_game.close();
                stmt.close();

            } else {

                stmt.setString(1, title);
                stmt.setString(2, publisher);
                stmt.setString(3, releaseDate);

                int rowsAffected = stmt.executeUpdate();
                stmt_game.executeUpdate();
                //System.out.println(rowsAffected + " row(s) affected");

                stmt_game.close();
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
    
    public boolean savePrice(Connection con, boolean bNew,
            javax.swing.JTextField txtRegion, javax.swing.JTextField txtPrice) {
        // TODO add your handling code here:
        //save the data to the table so add the data to the table

        String region = txtRegion.getText();
        String price = txtPrice.getText();

        String sql_query;
        String sql_contact; // Change every Contact SQL to Game SQL
        String sql_customercontact;

        if (bNew) {
            sql_query = "INSERT INTO customer(customer_first_name, customer_last_name, customer_city, customer_address, customer_country) "
                    + "VALUES (?, ?, ?, ?, ?)";

            sql_contact = "INSERT INTO contact(contact_type) " + "VALUES (?)";
        } else {
            sql_query = "UPDATE customer " + "SET customer_first_name = ?, " + "    customer_last_name = ?, "
                    + "    customer_city = ?, " + "    customer_address = ?, " + "    customer_country = ? " + "WHERE customer_id = ?";

            sql_contact = "UPDATE contact " + "SET contact_type = ? " + "WHERE contact_id = ?";
        }

        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);
            PreparedStatement stmt_game = con.prepareStatement(sql_contact);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, title);
                stmt.setString(2, publisher);
                stmt.setString(3, releaseDate);

                stmt_game.executeUpdate();
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
                stmt_game.close();
                stmt.close();

            } else {

                stmt.setString(1, title);
                stmt.setString(2, publisher);
                stmt.setString(3, releaseDate);

                int rowsAffected = stmt.executeUpdate();
                stmt_game.executeUpdate();
                //System.out.println(rowsAffected + " row(s) affected");

                stmt_game.close();
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
    
    public boolean savePublisher(Connection con, boolean bNew, javax.swing.JTextField txtPublisher_name) {
        // TODO add your handling code here:
        //save the data to the table so add the data to the table

        String name = txtPublisher_name.getText();

        String sql_query;
        String sql_contact; // Change every Contact SQL to Game SQL
        String sql_customercontact;

        if (bNew) {
            sql_query = "INSERT INTO customer(customer_first_name, customer_last_name, customer_city, customer_address, customer_country) "
                    + "VALUES (?, ?, ?, ?, ?)";

            sql_contact = "INSERT INTO contact(contact_type) " + "VALUES (?)";
        } else {
            sql_query = "UPDATE customer " + "SET customer_first_name = ?, " + "    customer_last_name = ?, "
                    + "    customer_city = ?, " + "    customer_address = ?, " + "    customer_country = ? " + "WHERE customer_id = ?";

            sql_contact = "UPDATE contact " + "SET contact_type = ? " + "WHERE contact_id = ?";
        }

        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);
            PreparedStatement stmt_game = con.prepareStatement(sql_contact);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, name);

                stmt_game.executeUpdate();
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
                stmt_game.close();
                stmt.close();

            } else {

                stmt.setString(1, name);

                int rowsAffected = stmt.executeUpdate();
                stmt_game.executeUpdate();
                //System.out.println(rowsAffected + " row(s) affected");

                stmt_game.close();
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
    
    public boolean saveSales(Connection con, boolean bNew, javax.swing.JTextField txtPublisher_name) {
        // TODO add your handling code here:
        //save the data to the table so add the data to the table

        String id = txtPublisher_name.getText();
        String count = txtPublisher_name.getText();
        String region = txtPublisher_name.getText();
        
        

        String sql_query;
        String sql_contact; // Change every Contact SQL to Game SQL
        String sql_customercontact;

        if (bNew) {
            sql_query = "INSERT INTO customer(customer_first_name, customer_last_name, customer_city, customer_address, customer_country) "
                    + "VALUES (?, ?, ?, ?, ?)";

            sql_contact = "INSERT INTO contact(contact_type) " + "VALUES (?)";
        } else {
            sql_query = "UPDATE customer " + "SET customer_first_name = ?, " + "    customer_last_name = ?, "
                    + "    customer_city = ?, " + "    customer_address = ?, " + "    customer_country = ? " + "WHERE customer_id = ?";

            sql_contact = "UPDATE contact " + "SET contact_type = ? " + "WHERE contact_id = ?";
        }

        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);
            PreparedStatement stmt_game = con.prepareStatement(sql_contact);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, name);

                stmt_game.executeUpdate();
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
                stmt_game.close();
                stmt.close();

            } else {

                stmt.setString(1, name);

                int rowsAffected = stmt.executeUpdate();
                stmt_game.executeUpdate();
                //System.out.println(rowsAffected + " row(s) affected");

                stmt_game.close();
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

    public void newGame(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmGame(con, null, null, null, null, false, model).setVisible(true);
    }
    
    public void newGameDatabase(Connection con, JTable table, boolean addExistGameDatabase, String e_id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (addExistGameDatabase) {
            new frmGame(con, null, null, null, null, false, model).setVisible(true);
        } else {
            new frmGame(con, null, null, null, null, false, model).setVisible(true);
        }
    }
    
    public void newGame_genre(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmGame_genre(con, null, null, null, null, null, null, null, null, true, model).setVisible(true);
    }
    
    public void newGame_platform(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmGame_platform(con, null, null, null, null, null, null, null, null, true, model).setVisible(true);
    }
    public void newGame_population(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmGame_population(con, null, null, null, null, null, null, null, null, true, model).setVisible(true);
    }
    public void newPrice(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmPrice(con, null, null, null, true, model).setVisible(true);
    }
    public void newPublisher(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmPublisher(con, null, null, true, model).setVisible(true);
    }
    public void newSales(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmSales(con, null, null, null, null, null, null, null, null, true, model).setVisible(true);
    }

    public void searchCustomer(Connection con, JTable table, String selected, String searchValue, boolean chkBox, boolean bContact) {
        //System.out.println(searchValue);
        //System.out.println(selected);
//        String sql_query = "SELECT *" + "FROM lab_3.customer WHERE " + selected + " LIKE \"" +searchValue+ "\" ";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String sql_query;

        if (chkBox) {
            if (bContact) {
                sql_query = "SELECT c.*, co.contact_type, co.contact_id"
                        + "FROM lab_3.customer c "
                        + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                        + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id "
                        + "WHERE UPPER( co." + selected + ") LIKE \"" + "%" + searchValue + "%" + "\"";
            } else {
                sql_query = "SELECT c.*, co.contact_type, co.contact_id "
                        + "FROM lab_3.customer c "
                        + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                        + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id "
                        + "WHERE UPPER( c." + selected + ") LIKE \"" + "%" + searchValue + "%" + "\"";
            }

        } else {
            if (bContact) {
                sql_query = "SELECT c.*, co.contact_type, co.contact_id "
                        + "FROM lab_3.customer c "
                        + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                        + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id "
                        + "WHERE UPPER( co." + selected + ") LIKE \"" + searchValue + "\"";
            } else {
                sql_query = "SELECT c.*, co.contact_type, co.contact_id "
                        + "FROM lab_3.customer c "
                        + "LEFT JOIN lab_3.customercontact cc ON c.customer_id = cc.customer_id "
                        + "LEFT JOIN lab_3.contact co ON cc.contact_id = co.contact_id "
                        + "WHERE UPPER( c." + selected + ") LIKE \"" + "%" + searchValue + "%" + "\"";
            }
        }
        //\"%\"")

//        "select*from employees where empid like "+empid+"%"
        try {
            PreparedStatement stmt = con.prepareStatement(sql_query);
//            stmt.setString(1, selected);
//            stmt.setString(2, selected);
            //System.out.println(stmt);
            //stmt.setString(3, searchValue);
            model.setRowCount(0);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //System.out.println("try test");
                //fetch data from sql datatable
                String id = rs.getString("customer_id");
                String first_Name = rs.getString("customer_first_name");
                String last_Name = rs.getString("customer_last_name");
                String address = rs.getString("customer_address");
                String city = rs.getString("customer_city");
                String country = rs.getString("customer_country");
                String contact_type = rs.getString("contact_type");
                String contact_id = rs.getString("contact_id");
                //add to table
                model.addRow(new Object[]{id, first_Name, last_Name, address, city, country, contact_type, contact_id});
            }
            //close statement on completion
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage()); // Show error message
        }

    }
}
