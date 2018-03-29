
import java.sql.ResultSet;
import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;

/**
 *
 * @author jmehl, glane
 */
public class ProjectTest
{
     
    private final String driver = "com.mysql.jdbc.Driver";
    private final String connect = "jdbc:mysql://localhost:3306/CMSC495";
    private final String user = "root"; // Change this to mysql username
    private final String pword = "root"; // change this to mysql password    

    public ProjectTest() {
    }
    
    /**
     * Test of modifyProjectString method, of class Project.
     */
    @Test
    public void testModifyProjectString() 
    {
        System.out.println("* ProjectTest: testModifyProjectString()");
        String colToModify = "ProjectName";
        String originalValue = "Test Project 1";
        String newValue = "NewProjectToComplete";
        
        // query string to later validate record has been updated
        String query = "SELECT ProjectName FROM Projects "
                     + "WHERE ProjectName = 'NewProjectToComplete'";

        // create new test record
        Project instance = new Project("Test Project 1",
             3, "Testers", 100, "Testing modifyProjectString()",
             "3/29/18");
        instance.modifyProjectString(colToModify, newValue, 100);
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,user,pword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
        while (rs.next()) 
        {
            originalValue = rs.getString("ProjectName");
        }
        
        // ensure that original value has been updated
        assertEquals(originalValue, newValue);
        
        // drop test record from table once test has completed
        PreparedStatement prepStatement;
        prepStatement = con.prepareStatement("DELETE FROM Projects "
                                            + "WHERE projectID = 100");
        prepStatement.execute();
        
    } catch (SQLException | ClassNotFoundException e ) {
        System.out.println("An exception occurred");
      }
    }

    /**
     * Test of modifyProjectInt method, of class Project.
     */
    @Test
    public void testModifyProjectInt() 
    {
        System.out.println("* ProjectTest: testModifyProjectInt()");
        String colToModify = "ProjectPriority";
        int originalValue = 5;
        int newValue = 7;
        int projectID = 101;
        
        // query string to later validate record has been updated
        String query = "SELECT ProjectPriority FROM Projects "
                     + "WHERE ProjectPriority = 7";

        // create new test record
        Project instance = new Project("Test Project 2",
             5, "Testers", 101, "Testing modifyProjectInt()",
             "3/29/18");
        instance.modifyProjectInt(colToModify, newValue, projectID);
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,user,pword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
        while (rs.next()) 
        {
            originalValue = rs.getInt("ProjectPriority");
        }
        
        // ensure that original value has been updated
        assertEquals(originalValue, newValue);
        
        // drop test record from table once test has completed
        PreparedStatement prepStatement;
        prepStatement = con.prepareStatement("DELETE FROM Projects "
                                            + "WHERE projectID = 101");
        prepStatement.execute();
        
    } catch (SQLException | ClassNotFoundException e ) {
        System.out.println("An exception occurred");
      }
    }

    /**
     * Test of loadProject method, of class Project.
     */
    @Test
    public void testLoadProject() {
                
        System.out.println("* ProjectTest: testModifyProjectInt()");
        String colToModify = "ProjectPriority";
        String expectedValue = "Test Project 3";
        String resultValue = "";
        int projectID = 103;
        
        // query string to later validate record has been updated
        String query = "SELECT ProjectName FROM Projects "
                     + "WHERE ProjectName = 'Test Project 3'";

        // create new test record
        Project instance = new Project("Test Project 3",
             5, "Testers", 103, "Testing loadProject()",
             "3/29/18");
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,user,pword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
        while (rs.next()) 
        {
            resultValue = rs.getString("ProjectName");
        }
        
        // ensure that result set is returning proper data
        assertEquals(expectedValue, resultValue);
        
        // drop test record from table once test has completed
        PreparedStatement prepStatement;
        prepStatement = con.prepareStatement("DELETE FROM Projects "
                                            + "WHERE projectID = 103");
        prepStatement.execute();
        
    } catch (SQLException | ClassNotFoundException e ) {
        System.out.println("An exception occurred");
      }
    }
   
}
