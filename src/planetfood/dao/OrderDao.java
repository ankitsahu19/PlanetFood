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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import planetfood.dbutil.DbConnection;
import planetfood.pojo.Order;
import planetfood.pojo.OrderDetail;
import planetfood.pojo.Product;

/**
 *
 * @author hp
 */
public class OrderDao {
    public static ArrayList<Order> getOrderByDate(Date startDate, Date endDate )throws SQLException {
        Connection conn =DbConnection.getConnection();
             String qry = "Select * from orders where ord_date between ? and ?";
             PreparedStatement ps = conn.prepareStatement(qry);
             long ms1 = startDate.getTime();
             long ms2 = endDate.getTime();
             java.sql.Date d1 = new java.sql.Date(ms1);
             java.sql.Date d2 = new java.sql.Date(ms2);
             ps.setDate(1, d1);
             ps.setDate(2, d2);
             ArrayList <Order> orderList = new ArrayList();
             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 Order obj = new Order();
                 obj.setOrderId(rs.getString("ord_id"));
                 SimpleDateFormat sdf= new SimpleDateFormat ("dd-MMM-yyyy");
                 java.sql.Date d = rs.getDate("ord_date");
                 String dateStr = sdf.format(d);
                 obj.setOrdDate(dateStr);
                 obj.setOrdAmount(rs.getDouble("ORD_AMIUNT"));
                 obj.setGst(rs.getDouble("gst"));
                 obj.setGstAmount(rs.getDouble("gst_amount"));
                 
                 obj.setGrandTotal(rs.getDouble("grand_total"));
                 obj.setDiscount(rs.getDouble("discount"));
                 obj.setUserID(rs.getString("userid"));
                 orderList.add(obj);
                 
                 
                 
             }
             return orderList;
    }
     public static ArrayList<Order> getAllData () throws SQLException {
          Connection conn = DbConnection.getConnection();
          String qry = "Select * from Orders";
          Statement ps = conn.createStatement();
          ArrayList <Order> orderList = new ArrayList<Order>();
        
          ResultSet rs = ps.executeQuery(qry);
          while (rs.next()) {
                 Order obj = new Order();
                 obj.setOrderId(rs.getString("ord_id"));
                 SimpleDateFormat sdf= new SimpleDateFormat ("dd-MMM-yyyy");
                 java.sql.Date d = rs.getDate("ord_date");
                 String dateStr = sdf.format(d);
                 obj.setOrdDate(dateStr);
                 obj.setOrdAmount(rs.getDouble("ORD_AMIUNT"));
                 obj.setGst(rs.getDouble("gst"));
                 obj.setGstAmount(rs.getDouble("gst_amount"));
                 
                 obj.setGrandTotal(rs.getDouble("grand_total"));
                 obj.setDiscount(rs.getDouble("discount"));
                 obj.setUserID(rs.getString("userid"));
                 orderList.add(obj);
                 
                 
                 
             }
          return orderList;
        }
     public static String getNewId() throws SQLException {
            Connection conn =DbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("Select count(*) from Orders");
         int id = 101;
        ResultSet rs = ps.executeQuery();
         if (rs.next()) {
             id = id +rs.getInt(1);
             
         }
         System.out.print(id);
         return "OD"+id;
     }
     public static boolean addOrder(Order order, ArrayList<OrderDetail> orderlist) throws Exception {
         Connection conn =DbConnection.getConnection();
          String qry = "insert into orders values (?,?,?,?,?,?,?,?)";
             PreparedStatement ps = conn.prepareStatement(qry);
             ps.setString(1, order.getOrderId());
             String datestr = order.getOrdDate();
             SimpleDateFormat sdf= new SimpleDateFormat ("dd-MMM-yyyy");
             java.util.Date d1= sdf.parse(datestr);
             java.sql.Date d2 = new java.sql.Date(d1.getTime());
             ps.setDate(2, d2);
             ps.setDouble(3, order.getGst());
             ps.setDouble(4, order.getGstAmount());
             ps.setDouble(5, order.getDiscount());
             ps.setDouble(6, order.getGrandTotal());
             ps.setString(7, order.getUserID());
             ps.setDouble(8, order.getOrdAmount());
             int x = ps.executeUpdate();
             PreparedStatement ps2 = conn.prepareStatement("Insert into orders_details values (?,?,?,?)");
             int count =0 ;
             int y ;
             
             for (OrderDetail detail : orderlist)
             {
                 ps2.setString(1,detail.getOrderId());
                 ps2.setString(2, detail.getProductId());
                 ps2.setDouble(3, detail.getQuantity());
                 ps2.setDouble(4, detail.getCost());
                 y = ps2.executeUpdate();
                 if(y>0) 
                     count = count +y;
                 
             }
           if (count ==orderlist.size() && x>0) 
                 return true;
             else 
                 return false ;
             
             
             
             
         
     }
}
