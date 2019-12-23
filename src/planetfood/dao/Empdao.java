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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DbConnection;
import planetfood.pojo.Emp;
import planetfood.pojo.Product;

/**
 *
 * @author hp
 */
public class Empdao {

    public static boolean addEmployee(Emp e) throws SQLException {
           Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into employee values (?,?,?,?)");
        ps.setString(1, e.getEmpid());
        ps.setString(2, e.getEname());
        ps.setString(3, e.getJobtype());
        ps.setDouble(4, e.getSal());
        int x =ps.executeUpdate();
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
    }
    public static boolean updateEmployee(Emp e) throws SQLException {
        Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update employee set ENAME =? ,job =?, SAL =? where Empid =? ");
        
        ps.setString(1, e.getEname());
        ps.setString(2, e.getJobtype());
        ps.setDouble(3, e.getSal());
        ps.setString(4, e.getEmpid());
        int x =ps.executeUpdate();
        
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
        
    }
    public static boolean deleteEmployee(String eno) throws SQLException {
        Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from employee where empid =?");
        
        
        ps.setString(1,eno );
        int x =ps.executeUpdate();
        
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
        
    }
    
        public static ArrayList<Emp> getAllData () throws SQLException {
          Connection conn = DbConnection.getConnection();
          String qry = "Select * from employee ";
          Statement ps = conn.createStatement();
          ArrayList <Emp> empList = new ArrayList<Emp>();
        
          ResultSet rs = ps.executeQuery(qry);
          while (rs.next()) {
              Emp p = new Emp();
            
              p.setEmpid(rs.getString("EMPID"));
              p.setEname(rs.getString("ENAME"));
              p.setJobtype(rs.getString("job"));
              p.setSal(rs.getDouble("SAL"));
              
              
              empList.add(p);
                      
              
          }
          return empList;
        }
          public static String getNewId () throws SQLException {
     
        Connection conn =DbConnection.getConnection();
         Statement ps = conn.createStatement();
         int id = 101;
        ResultSet rs = ps.executeQuery("Select count(*) from employee");
         if (rs.next()) {
             id = id +rs.getInt(1);
             
         }
         return "E"+id;
        
    }
        
        public static boolean deleteProduct(String prodid) throws SQLException {
        Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from Products where PROD_ID =?");
        
        
        ps.setString(1,prodid );
       
        int x =ps.executeUpdate();
        
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
        
    
        
        
    }
         public static HashMap<String,Emp> getCashierData () throws SQLException {
          Connection conn = DbConnection.getConnection();
          String qry = "select * from employee where job ='Cashier'";
          Statement ps = conn.createStatement();
          HashMap <String,Emp> empList = new HashMap<String,Emp>();
        
          ResultSet rs = ps.executeQuery(qry);
          while (rs.next()) {
              Emp p = new Emp();
            
              p.setEmpid(rs.getString("EMPID"));
              p.setEname(rs.getString("ENAME"));
              p.setJobtype("Cashier");
              p.setSal(rs.getDouble("SAL"));
              
              
              empList.put(p.getEmpid(), p);
                      
              
          }
          return empList;
        }
     public static ArrayList<Emp> getSingleEmpData (String Empid) throws SQLException {
          Connection conn = DbConnection.getConnection();
          String qry = "Select * from employee where EMPID=?";
          PreparedStatement ps = conn.prepareStatement(qry);
          ArrayList <Emp> empList = new ArrayList<Emp>();
          ps.setString(1,Empid);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              Emp p = new Emp();
              p.setEmpid(Empid);
              p.setEname(rs.getString("ENAME"));
              p.setJobtype(rs.getString("job"));
              p.setSal(rs.getDouble("SAL"));
              empList.add(p);
              
             
                      
          
        }
          return empList;
     }
}
     
