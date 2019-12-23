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
public class Productdao {
    public static String getNewId () throws SQLException {
     
        Connection conn =DbConnection.getConnection();
         Statement ps = conn.createStatement();
         int id = 101;
        ResultSet rs = ps.executeQuery("Select count(*) from products");
         if (rs.next()) {
             id = id +rs.getInt(1);
             
         }
         return "P"+id;
        
    }
    public static boolean addProduct (Product pr ) throws SQLException {
         Connection conn =DbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("Insert into products values (?,?,?,?,?)");
         ps.setString(1, pr.getProdId());
         ps.setString(2, pr.getCatId());
         
         ps.setString(3, pr.getProdName());
         ps.setDouble(4, pr.getProdPrice());
         ps.setString(5, pr.getIsActive());
         int x = ps.executeUpdate();
         if (x==0) 
             return false;
         else 
             return true;
         
         
        
    }
     public static  ArrayList <String > getProductId (String catid  ) throws SQLException {
         Connection conn =DbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("Select prod_id from product where cat_id = 'catid' ");
        ResultSet rs = ps.executeQuery();
       ArrayList <String > product = new ArrayList ();
        while (rs.next()) {
            String prodId = rs.getString(1);
            product.add(prodId);
           
        }
        
        
        return product;
     }
      public static HashMap<String,Product> getProductByCategory (String catId) throws SQLException {
          Connection conn = DbConnection.getConnection();
          String qry = "Select * from Products where cat_id=?";
          PreparedStatement ps = conn.prepareStatement(qry);
          HashMap <String,Product> productList = new HashMap<String,Product>();
          ps.setString(1,catId);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              Product p = new Product();
              p.setCatId(catId);
              p.setProdId(rs.getString("prod_id"));
              p.setProdName(rs.getString("prod_name"));
              p.setProdPrice(rs.getDouble("product_price"));
              
              p.setIsActive(rs.getString("active"));
              productList.put(p.getProdId(), p);
                      
              
          }
          return productList;
      }
       public static boolean updateProducts(Product e) throws SQLException {
        Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(" update products set CAT_ID=?,PROD_NAME=?, PRODUCT_PRICE=?, ACTIVE =? where PROD_ID=?");
        ps.setString(1, e.getCatId());
        ps.setString(2, e.getProdName());
        ps.setDouble(3, e.getProdPrice());
        
        ps.setString(4, e.getIsActive());
     
      
        ps.setString(5, e.getProdId());
       
        int x =ps.executeUpdate();
        
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
        
    }
        public static ArrayList<Product> getAllData () throws SQLException {
          Connection conn = DbConnection.getConnection();
          String qry = "Select * from Products ";
          Statement ps = conn.createStatement();
          ArrayList <Product> productList = new ArrayList<Product>();
        
          ResultSet rs = ps.executeQuery(qry);
          while (rs.next()) {
              Product p = new Product();
              p.setCatId(rs.getString("CAT_ID"));
              p.setProdId(rs.getString("prod_id"));
              
              p.setProdName(rs.getString("prod_name"));
              p.setProdPrice(rs.getDouble("product_price"));
              
              p.setIsActive(rs.getString("active"));
              productList.add(p);
              
                      
              
          }
          return productList;
        }
        public static boolean removeProduct(String prodname)throws SQLException {
        Connection conn =DbConnection.getConnection();
        
        PreparedStatement ps = conn.prepareStatement("update products set active='N' where prod_name=?");
        
        
        ps.setString(1,prodname );
        int x =ps.executeUpdate();
        
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
        
    }
        
        public static HashMap <String,String>getActiveProductCategory(String catId) throws SQLException {
             Connection conn =DbConnection.getConnection();
             String qry = "Select prod_name,prod_id from Products where cat_id= ? and active ='Y'";
             PreparedStatement ps = conn.prepareStatement(qry);
             ps.setString(1, catId);
            ResultSet rs = ps.executeQuery();
             HashMap <String ,String> productList = new HashMap();
             while (rs.next()) {
                 String prodName = rs.getString("prod_name");
                 String prodId = rs.getString("prod_id");
                 
                 productList.put(prodName, prodId);
             }
             return productList;
             
             
        }
       
    
}
