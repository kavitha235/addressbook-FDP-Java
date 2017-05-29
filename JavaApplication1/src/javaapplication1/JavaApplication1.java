/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.SQLException;


/**
 *
 * @author Administrator
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con=null;
                try
        {
            con = DriverManager.getConnection
                ("jdbc:oracle:thin:@172.16.0.70:1521:cas","fdp10","fdp10"); 
           
   // con.close();          
            if (con != null)
            {
                                //System.out.println("connected");
                                 Statement stmt=con.createStatement();  
             ResultSet rs=stmt.executeQuery("select * from addressbook");  
while(rs.next())  
System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                      
    con.close();  
            }
    }    catch(Exception e)
    {
System.out.println("no Connection");}
    }
   
}
