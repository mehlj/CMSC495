package JJIGSAWED;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author glane
 */
public class User {

    private final String driver = "com.mysql.jdbc.Driver";
    private final String connect = "jdbc:mysql://localhost:3306/CMSC495";
    private final String user = "root"; // Change this to mysql username
    private final String pword = "root"; // change this to mysql password

    private String userName;
    private String userRole;
    private Statement statement;
    private PreparedStatement prepStatement;
    private Connection con;
    
    
    /**
     *
     * Create User and create record in User table
     * @param userName - Name of user
     * @param userRole - Role of the user being added
     * @author glane
     */

    public void createUser(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
        
        Random rand = new Random();
        int userID = 0 + rand.nextInt(500);
        
        try
         {
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
             
             
         
             prepStatement = con.prepareStatement("Insert into Users(userID,Name,Role) values "
                                                + "(?,?,?);");
             prepStatement.setInt(1, userID);
             prepStatement.setString(2, userName);
             prepStatement.setString(3, userRole);
             
         
             prepStatement.execute(); // perform insert
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
           // System.out.println("An exception occurred");
             System.out.println(ex.getMessage());
         }

    }
    
         public void modifyUserString(String colToModify, String newValue, 
                               int userID) 
     {
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
         
             prepStatement = con.prepareStatement("UPDATE Users "
                                                + "SET " + colToModify + " = ?" // Replaced ? with column name, prepStatemnet.setString adds " " around column name
                                                + "WHERE userID = ?");

           //  prepStatement.setString(1, colToModify);
             prepStatement.setString(1, newValue);
             prepStatement.setInt(2, userID);
         
             prepStatement.execute(); // perform update
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
         }
    } 
     

}
