package JJIGSAWED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author glane
 */
public class User {

    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String connect = "jdbc:mysql://localhost:3306/CMSC495";
    private static final String USER = DBInteraction.getDBUsername(); 
    private static final String PWORD = DBInteraction.getDBPassword(); 

    private String userName;
    private String userRole;
    private static Statement statement;
    private static PreparedStatement prepStatement;
    private static Connection con;

    /**
     *
     * Create User and create record in User table
     *
     * @param userName - Name of user
     * @param userRole - Role of the user being added
     * @author glane
     */
    public void createUser(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;

        Random rand = new Random();
        int userID = 0 + rand.nextInt(500);

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connect, USER, PWORD);

            prepStatement = con.prepareStatement("Insert into Users(userID,Name,Role) values "
                    + "(?,?,?);");
            prepStatement.setInt(1, userID);
            prepStatement.setString(2, userName);
            prepStatement.setString(3, userRole);

            prepStatement.execute(); // perform insert

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            // System.out.println("An exception occurred");
            System.out.println(ex.getMessage());
        }

    }

    /**
     *
     * modify record in User table
     *
     * @param colToModify - Column to modify
     * @param newValue - new value of column
     * @param userID - unique ID of user column
     * @author glane
     */

    public void modifyUserString(String colToModify, String newValue,
            int userID) {

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connect, USER, PWORD);

            prepStatement = con.prepareStatement("UPDATE Users "
                    + "SET " + colToModify + " = ?" // Replaced ? with column name, prepStatemnet.setString adds " " around column name
                    + "WHERE userID = ?");

            prepStatement.setString(1, newValue);
            prepStatement.setInt(2, userID);

            prepStatement.execute(); // perform update

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("An exception occurred");
        }
    }

    /**
     *
     * delete record in User table
     *
     * @param userID - unique ID of user column
     * @author glane
     */
    public static void deleteUser(int userID) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connect, USER, PWORD);

            prepStatement = con.prepareStatement("DELETE FROM Users WHERE userID = ?");

            prepStatement.setInt(1, userID);

            prepStatement.execute(); // perform delete

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }

    } // end delete user

    public static ArrayList getUserIDs() {

        ArrayList<Integer> userIDList = new ArrayList<Integer>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connect, USER, PWORD);

            prepStatement = con.prepareStatement("SELECT * "
                    + "FROM CMSC495.Users");

            ResultSet rs = prepStatement.executeQuery(); // perform select

            // convert resultset to array
            while (rs.next()) {
                int i = rs.getInt(1);

                userIDList.add(i);
            }

            con.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("An exception occured");
            System.out.println(ex);
        }

        return userIDList;
    } // end getUserIDs()
    public static ResultSet getUser(int userID) 
     {
         ResultSet rs = null;
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,USER,PWORD);
         
             prepStatement = con.prepareStatement("SELECT * "
                                                + "FROM CMSC495.Users "
                                                + "WHERE userID = ?");
             
             prepStatement.setInt(1, userID);
         
             rs = prepStatement.executeQuery(); // perform update
             
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occured");
            System.out.println(ex);
         }
         
         return rs;
    } // end getUser()
    
    /**
     *
     * Obtain name of user given just the userID
     *
     * @param userID - unique user ID
     * @return - name of user
     * @author jmehl
    */
    public static String getUserName(int userID)
    {
        String userName = "";
        
        try 
        {
                Class.forName(driver);
                con = DriverManager.getConnection(connect, USER, PWORD);

                prepStatement = con.prepareStatement("SELECT Name "
                        + "FROM CMSC495.Users "
                        + "WHERE UserID = ?");

                prepStatement.setInt(1, userID);

                ResultSet rs = prepStatement.executeQuery(); // perform update
                
                while (rs.next()) {
                    userName = rs.getString(1);
                }
        }
        
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println(ex);
        }
        return userName;
    }
}
