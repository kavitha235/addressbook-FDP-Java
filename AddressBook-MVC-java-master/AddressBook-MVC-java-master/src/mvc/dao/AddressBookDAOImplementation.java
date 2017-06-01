/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.dao;

import mvc.models.Person;
import mvc.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import mvc.views.DetailViewPanel;
import mvc.views.NameListPanel;

/**
 *
 * @author Admin
 */
public class AddressBookDAOImplementation implements AddressBookDAO{
    NameListPanel list;
    Connection conn;
    Statement st;
    /*public AddressBookDAOImplementation ()
    {
        conn= DBConnection.getInstance().getConnect();
        
    }*/
    
    @Override
    public void addPerson(Person person) 
    {   
       try
       {
          conn = DBConnection.getInstance().getConnect();
           st = conn.createStatement();
           String qry;
           qry = "insert into addressbook values ('"+person.getName()+"','"+person.getMob()+"','"+person.getEmail()+"','"+person.getBG()+"')";
           st.executeUpdate(qry);
           conn.close();
        }
            
       catch(SQLException e)
       {
           System.out.println(e);
       }
           
    }
    
    @Override
     public void removePerson(String name)
     {
         try
         {
           conn = DBConnection.getInstance().getConnect();
           st = conn.createStatement();
           String qry="delete from addressbook where name='"+name+"'";
           st.executeUpdate(qry);
           conn.close();
         }
         catch (SQLException e)
         {
             System.out.println(e);
         }
     }
    
    @Override
     public void updatePerson(Person person,String name)
     {
         try
       {
           conn = DBConnection.getInstance().getConnect();
           st = conn.createStatement();
            String qry="update addressbook set name='"+person.getName()+"',mob='"+person.getMob()+"',email='"+person.getEmail()+"',BG='"+person.getBG()+"', where name='"+name+"'";
           st.executeUpdate(qry);
           conn.close();
        }
            
       catch(SQLException e)
       {
          System.out.println(e); 
       }
     }
    
    /**
     *to display all names to select
     * @param namePanel
     * @return 
     */
    @Override
    public DefaultListModel getAllNames(NameListPanel namePanel) 
    {
             DefaultListModel<String> dlm = new DefaultListModel<>();
            try {
         conn = DBConnection.getInstance().getConnect();
            Statement stmt;
                 stmt = conn.createStatement();
            String qry = "select * from addressbook order by name asc";
                 try (ResultSet rs = stmt.executeQuery(qry)) {
                     while (rs.next()){
                         String name = rs.getString(1);
                         dlm.addElement(name);
                     }
                     
                     namePanel.getJList().setModel(dlm);
                 }
            stmt.close();
           conn.close();
        }
           catch (SQLException ex) {
                   System.out.println(ex+"NO Records/Cannot retrieve records");
                   }
           
        return dlm;
    }
    
    
    @Override
    public void getSelectedName(DetailViewPanel detailPanel,String selectedName)
    {
        try {
            conn = DBConnection.getInstance().getConnect();
            Statement stmt;
            stmt = conn.createStatement();
            String qry = "select * from addressbook where name = '"+selectedName+"'";
            ResultSet rs;
            rs = stmt.executeQuery(qry);
            while(rs.next()){
             detailPanel.setName(rs.getString("name"));
             detailPanel.seteMail(rs.getString("email"));
             detailPanel.setMobile(rs.getString("mob"));
             detailPanel.setBG(rs.getString("BG"));
             
           }
            conn.close();
        }
         catch (Exception e) {
                   System.out.println("NO Records/Cannot retrieve records");
                   }
    }
     //@Override
    public DefaultListModel getSearchNames(NameListPanel namePanel, String searchcon) 
    {
             DefaultListModel<String> dlm = new DefaultListModel<>();
            try {
         conn = DBConnection.getInstance().getConnect();
            Statement stmt;
                 stmt = conn.createStatement();
                 searchcon = searchcon+'%';
            String qry = "select * from addressbook where name like'"+searchcon+"'";
                 try (ResultSet rs = stmt.executeQuery(qry)) {
                     while (rs.next()){
                         String name = rs.getString(1);
                         dlm.addElement(name);
                     }
                     
                     namePanel.getJList().setModel(dlm);
                 }
            stmt.close();
           conn.close();
        }
           catch (SQLException ex) {
                   System.out.println(ex+"NO Records/Cannot retrieve records");
                   }
           
        return dlm;
    }

    @Override
    public DefaultListModel getSearch(NameListPanel namePanel, String searchcon) 
    {
      DefaultListModel<String> dlm = new DefaultListModel<>();
            try {
         conn = DBConnection.getInstance().getConnect();
            Statement stmt;
                 stmt = conn.createStatement();
                 searchcon = searchcon+'%';
            String qry = "select * from addressbook where name like '"+searchcon+"'";
                 try (ResultSet rs = stmt.executeQuery(qry)) {
                     while (rs.next()){
                         String name = rs.getString(1);
                         dlm.addElement(name);
                     }
                     
                     namePanel.getJList().setModel(dlm);
                 }
            stmt.close();
           conn.close();
        }
           catch (SQLException ex) {
                   System.out.println(ex+"NO Records/Cannot retrieve records");
                   }
           
        return dlm;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

    