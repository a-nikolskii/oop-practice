/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice3;
import java.sql.*;
import org.sqlite.*;
import org.sqlite.JDBC;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author a.nikolskii
 */
public class Manager {
    
    private JTable tableview;
    private Connection c;
    private DefaultTableModel dtm;
    
    
    public Manager(JTable table, DefaultTableModel tm){
       
        this.tableview = table;
        this.dtm = tm;
        
        Statement stmt = null;
        try{
            DriverManager.registerDriver(new org.sqlite.JDBC());
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");
            System.out.println("connection established: " + c.isValid(0));
            
            String createTable = "CREATE TABLE IF NOT EXISTS people ("
                    + "id integer PRIMARY KEY AUTOINCREMENT,"
                    + "name text NOT NULL,"
                    + "tel text,"
                    + "location text,"
                    + "time integer);";
            
            stmt = c.createStatement();
            stmt.execute(createTable);
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
   public void show(){
       try{
           while(dtm.getRowCount() != 0)
                {
                    dtm.removeRow(0);
                }
           Statement stmt = c.createStatement();
           String sql = "Select * from people";
           ResultSet rs = stmt.executeQuery(sql); 
           ArrayList rowData = new ArrayList();
           
           while (rs.next()) {
                rowData.add(rs.getInt("id"));
                rowData.add(rs.getString("name"));
                rowData.add(rs.getString("tel"));
                rowData.add(rs.getString("location"));
                rowData.add(rs.getString("time"));
                dtm.insertRow(tableview.getRowCount(), rowData.toArray());
                rowData.clear();
            
            }
           stmt.close();
           rs.close();
        }
       catch(SQLException e){
            System.out.println(e.getMessage());
        }
   }
   public void insert(String n, String t, String l, String v){
       
       try{
            PreparedStatement ins = c.prepareStatement("INSERT INTO people(name, tel, location, time) VALUES (?, ?, ?, ?)");
            ins.setString(1, n);
            ins.setString(2, t);
            ins.setString(3, l);
            ins.setString(4, v);        
            ins.executeUpdate();
            ins.close();
        }
       catch(SQLException e){
            System.out.println(e.getMessage());
        }
   }
   
  public void DeleteDB(int id)
{
    try
    {
        PreparedStatement delete = c.prepareStatement("DELETE FROM people WHERE Id = ?");
        delete.setInt(1, id);
        delete.executeUpdate();
        delete.close();
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
}
   }


class Practice2 extends DefaultTableModel{

    public Practice2() {
        super  (new String[]{"id", "name", "tel",
        "location", "time"}, 0);
     }
    @Override
    public boolean isCellEditable(int row, int column) {
        return !(column != 0 || row == getRowCount() - 1);
    }
}

