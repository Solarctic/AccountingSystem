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

    public void editContact(Connection con, javax.swing.JTable tblContacts) {
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
            String contact_type = tableModel.getValueAt(row, 1).toString();
            new frmContact(con, id, contact_type, false, false, null, model).setVisible(true);
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

    public boolean deleteCustomer(Connection con, javax.swing.JTable tblCustomers) {
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

            String sql = "DELETE FROM customer WHERE customer_id = ?";

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

    // End of Customer 
    // Start of Contact
    public void getDefaultContact(Connection con, DefaultTableModel tbl_c) {

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
    public void getDefaultCustomer(Connection con, DefaultTableModel tbl_c) {
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

    public boolean saveCustomer(Connection con, boolean bNew,
            javax.swing.JTextField txtCustomer_id, javax.swing.JTextField txtCustomer_first_name,
            javax.swing.JTextField txtCustomer_last_name, javax.swing.JTextField txtCustomer_city,
            javax.swing.JTextField txtCustomer_address, javax.swing.JTextField txtCustomer_country,
            javax.swing.JComboBox cmbBoxContact, String contact_id) {
        // TODO add your handling code here:
        //save the data to the table so add the data to the table

        String id = txtCustomer_id.getText();
        String firstname = txtCustomer_first_name.getText();
        String lastname = txtCustomer_last_name.getText();
        String city = txtCustomer_city.getText();
        String address = txtCustomer_address.getText();
        String country = txtCustomer_country.getText();
        String contact_type = cmbBoxContact.getSelectedItem().toString();

        String sql_query;
        String sql_contact;
        String sql_customercontact;

//         if (bNew) {
//            sql_query = "INSERT INTO contact(contact_type) "
//                    + "VALUES (?)";
//        } else {
//            sql_query = "UPDATE contact " + "SET contact_type = ? " + "WHERE contact_id = ?";
//        }
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
            PreparedStatement stmt_contact = con.prepareStatement(sql_contact);

            //change this or smthing
            if (bNew) {
                //stmt.setInt(1, new_id);
                stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setString(3, city);
                stmt.setString(4, address);
                stmt.setString(5, country);

                stmt_contact.setString(1, contact_type);

                stmt_contact.executeUpdate();
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
                stmt_contact.close();
                stmt.close();

            } else {

                stmt.setString(1, firstname);
                stmt.setString(2, lastname);
                stmt.setString(3, city);
                stmt.setString(4, address);
                stmt.setString(5, country);
                stmt.setInt(6, Integer.parseInt(id));

                stmt_contact.setString(1, contact_type);
                stmt_contact.setInt(2, Integer.parseInt(contact_id));

                int rowsAffected = stmt.executeUpdate();
                stmt_contact.executeUpdate();
                //System.out.println(rowsAffected + " row(s) affected");

                stmt_contact.close();
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
        new frmGame(con, null, null, null, null, null, null, null, null, true, model).setVisible(true);
    }
    
    public void newGameDatabase(Connection con, JTable table, boolean addExistGameDatabase, String e_id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (addExistGameDatabase) {
            new frmGameDatabase(con).setVisible(true);
        } else {
            new frmGameDatabase(con).setVisible(true);
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
        new frmPrice(con, null, null, null, null, null, null, null, null, true, model).setVisible(true);
    }
    public void newPublisher(Connection con, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new frmPublisher(con, null, null, null, null, null, null, null, null, true, model).setVisible(true);
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
