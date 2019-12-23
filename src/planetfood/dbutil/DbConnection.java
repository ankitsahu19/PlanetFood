/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class DbConnection {
      private static Connection conn;
    static {
            try{
        
    Class.forName("oracle.jdbc.OracleDriver");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-8OKBQBO7:1521/XE","myproject","Ankit");
    JOptionPane.showMessageDialog(null,"Complete Successfully to the Database","Success!",JOptionPane.INFORMATION_MESSAGE);
        }
    catch( ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, "Error inloading Driver" +ex,"Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    
        }
    catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "SQL exception" +ex,"Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        
    }
    }
    public static Connection getConnection (){
        return conn;
    }
    public static void closeConnection () {
        
    }
}
