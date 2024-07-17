/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework4sql;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author SOL
 */
public class frmSuperForm extends javax.swing.JFrame {
    
    protected Connection con;
    protected connectionFunction cnf;
    protected DefaultTableModel table; 
    protected connectionGameDB cng;
    
    
    public frmSuperForm(Connection sqlpass) {
        this.con = sqlpass;
        this.cnf = new connectionFunction(); 
        this.cng = new connectionGameDB();
        boolean check = cnf.isDbConnected(con);
        
        System.out.println(check);
        if (!check) {
            JOptionPane.showMessageDialog(null, "SQL Connection Failed or Interrupted.");
            return;
        }
    }
    public void setCon(Connection con)
    {
        this.con = con;
    }
    
    public void setCnf(connectionFunction cnf)
    {
        this.cnf = cnf;
    }
    public void setCng(connectionGameDB cng)
    {
        this.cng = cng;
    }
    
    public void setDefaultTable(DefaultTableModel table)
    {
        this.table = table;
    }
    
    public Connection getCon()
    {
        return con;
    }
    
    public connectionFunction getCnf()
    {
        return cnf;
    }
    public connectionGameDB getCng()
    {
        return cng;
    }
    
    public DefaultTableModel getDefaultTable()
    {
        return table;
    }
    
    // Common method to be overridden by subclasses to load data into the table
    protected void refreshTable()
    {
        // Default implementation does nothing; subclasses should override this method
    }

    // Common method to be overridden by subclasses to load data into the table
    protected void loadData() {
        // Default implementation does nothing; subclasses should override this method
    }
}
