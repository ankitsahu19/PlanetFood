/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import planetfood.dbutil.DbConnection;
import planetfood.pojo.Emp;
import planetfood.pojo.User;
import planetfood.pojo.UserProfile;

/**
 *
 * @author hp
 */
public class Userdao {
    public static String validateUser(User user) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String qry = ("Select username from users where userid=? and password =? and usertype=?");
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1,user.getUserid());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUsertype());
        ResultSet rs = ps.executeQuery();
        String username = null;
       
        
        if (rs.next())
        {
            username = rs.getString(1);
            
        }
        
 
        return username;
        
        
        
        
        
    }
     public static boolean addUser(User e, String em) throws SQLException {
           Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into users values (?,?,?,?,?)");
  
        ps.setString(1, e.getUserid());
        ps.setString(2, UserProfile.getUserName());
        ps.setString(3, em);
        ps.setString(4, e.getPassword());
        ps.setString(5, e.getUsertype());
        int x =ps.executeUpdate();
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
    }
    
}
