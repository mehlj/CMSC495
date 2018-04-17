/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;
import JJIGSAWED.User;
import org.junit.Test;
import java.sql.*;

/**
 *
 * @author enjoi
 */
public class UserTest {

    private final String driver = "com.mysql.jdbc.Driver";
    private final String connect = "jdbc:mysql://localhost:3306/CMSC495";
    private final String user = "root"; // Change this to mysql username
    private final String pword = "root"; // change this to mysql password

    public UserTest() {
    }

    /**
     * Test of modifyUserString method, of class User.
     */
    @Test
    public void testModifyUserString() {
        System.out.println("modifyUserString");
        String colToModify = "Name";
        String newValue = "NotGreg";
        int userID = 2;
        String originalValue = "";

        User instance = new User();
        instance.modifyUserString(colToModify, newValue, userID);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        String query = "SELECT Name FROM Users "
                + "WHERE Name = 'NotGreg'";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, user, pword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                originalValue = rs.getString("Name");
            }

            assertEquals(originalValue, newValue);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An exception occurred");
        }

    }

    @Test
    public void testCreateUser() {
        System.out.println("testCreateUser");
        String newValue = "TestUser";
        String originalValue = "";

        User instance = new User();
        instance.createUser("TestUser", "Dev");
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        String query = "SELECT Name FROM Users "
                + "WHERE Name = 'TestUser'";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, user, pword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                originalValue = rs.getString("Name");
            }

            assertEquals(originalValue, newValue);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An exception occurred");
        }

    }

    @Test
    public void testDeleteUser() {
        System.out.println("testDeleteUser");
        String newValue = "";
        String originalValue = "";
        int originalUserID;
        int userID = 0;

        User instance = new User();
        instance.createUser("TestUser123", "Dev");

        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        String query = "SELECT userID FROM Users "
                + "WHERE Name = 'TestUser123'";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, user, pword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                userID = rs.getInt("userID");
            }
            originalUserID = userID;
            instance.deleteUser(userID);

            query = "SELECT USERID FROM users WHERE Name = 'TestUser123';";

            Class.forName(driver);
            Connection con1 = DriverManager.getConnection(connect, user, pword);
            Statement stmt1 = con1.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query);
            
            while (rs1.next()) {
                userID = rs1.getInt("userID");
            }

            assertEquals(originalUserID,userID);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
