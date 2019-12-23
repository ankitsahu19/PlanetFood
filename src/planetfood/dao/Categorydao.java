
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DbConnection;
import planetfood.pojo.Category;
import planetfood.pojo.Emp;
import planetfood.pojo.Product;

/**
 *
 * @author hp
 */
public class Categorydao {
    public static HashMap <String ,String> getAllCategoryid() throws SQLException {
         Connection conn =DbConnection.getConnection();
         Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery("Select * from categories");
        HashMap <String ,String > categories = new HashMap ();
        while (rs.next()) {
            String catId = rs.getString(1);
            String catName = rs.getString(2);
            categories.put(catName,catId);
        }
        
        
        return categories;
        
        
    }
    public static boolean addCategory(Category e) throws SQLException {
           Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into Categories values (?,?)");
        ps.setString(1, e.getCat_id());
        ps.setString(2, e.getCat_Name());
       
        int x =ps.executeUpdate();
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
    }
     public static boolean updateCategory(Category e) throws SQLException {
        Connection conn =DbConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update categories set CAT_NAME=? where CAT_ID =? ");
        
        ps.setString(1, e.getCat_Name());
        ps.setString(2, e.getCat_id());
       
        int x =ps.executeUpdate();
        
        if (x!=0) 
            return true;
        
        else 
            return false;
        
        
        
    }
     
        public static ArrayList<Category> getAllData () throws SQLException {
          Connection conn = DbConnection.getConnection();
          String qry = "Select * from Categories ";
          Statement ps = conn.createStatement();
          ArrayList <Category> CategoryList = new ArrayList<Category>();
        
          ResultSet rs = ps.executeQuery(qry);
          while (rs.next()) {
              Category p = new Category();
             
              p.setCat_id(rs.getString("CAT_ID"));
              p.setCat_Name(rs.getString("CAT_NAME"));
              
              
              CategoryList.add(p);
                      
              
          }
          return CategoryList;
        }

           
        
        
        
      

        
    
}
