/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework4sql;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.JTable;

/**
 *
 * @author SOL
 */
public class frmSuperList extends javax.swing.JFrame {
    protected Connection con;
    protected connectionFunction cnf;
    protected connectionFunctionGame cng;
    protected JTable table;

    public frmSuperList(Connection sqlpass) {
        this.con = sqlpass;
        this.cnf = new connectionFunction();
        this.cng = new connectionFunctionGame();
        this.table = new JTable();
        //initComponents(); // Assuming this method initializes UI components
        boolean check = cnf.isDbConnected(con);
        boolean checkg = cng.isDbConnected(con);
        System.out.println(checkg);
        if (!checkg) {
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
    
    public void setCng(connectionFunctionGame cng)
    {
        this.cng = cng;
    }
    
    public void setTable(JTable table)
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
    
    public connectionFunctionGame getCng()
    {
        return cng;
    }
    
    public JTable getTable()
    {
        return table;
    }

    // Common method to be overridden by subclasses to load data into the table
    protected void loadData() {
        // Default implementation does nothing; subclasses should override this method
    }
    
    protected void refreshTable()
    {
        // Default implementation does nothing; subclasses should override this method
    }

    // Common method to refresh the table

}
