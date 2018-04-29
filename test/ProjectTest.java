
import JJIGSAWED.DBInteraction;
import JJIGSAWED.Project;
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
     
    private final String driver = DBInteraction.getDBDriver();
    private final String connect = DBInteraction.getDBConnect();
    private static final String USER = DBInteraction.getDBUsername(); 
    private static final String PWORD = DBInteraction.getDBPassword(); 

    public ProjectTest() {
    }
    
    /**
     * Test of modifyProjectString method, of class Project.
     * @author jmehl
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
             3, 100, "Testing modifyProjectString()",
             "3/29/18");
        instance.modifyProjectString(colToModify, newValue, 100);
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,USER,PWORD);
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
     * @author jmehl
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
             5, 101, "Testing modifyProjectInt()",
             "3/29/18");
        instance.modifyProjectInt(colToModify, newValue, projectID);
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,USER,PWORD);
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
     * @author jmehl
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
             5, 103, "Testing loadProject()",
             "3/29/18");
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,USER,PWORD);
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
      
      /**
      * Test of Task constructor.
      * @author jmehl
      */
      @Test
      public void testCreateProject() {
                
        System.out.println("* ProjectTest: CreateProject()");
        String expectedValue = "Test Project Create Test";
        String resultValue = "";
        
        // query string to later validate record has been updated
        String query = "SELECT ProjectName FROM Projects "
                     + "WHERE ProjectName = 'Test Project Create Test'";

        // create new test record
        Project instance = new Project("Test Project Create Test",
             1, 122, "Testing CreateProject()",
             "3/29/18");
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,USER,PWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
        while (rs.next()) 
        {
            resultValue = rs.getString("ProjectName");
        }
        //System.out.println("this is the result value" + resultValue);
        
        // ensure that result set is returning proper data
        assertEquals(expectedValue, resultValue);
        
        // drop test record from table once test has completed
        PreparedStatement prepStatement;
        prepStatement = con.prepareStatement("DELETE FROM Projects "
                                            + "WHERE projectID = 122");
        prepStatement.execute();
        
    } catch (SQLException | ClassNotFoundException e ) {
        System.out.println("An exception occurred");
      }
    }
      @Test
      public void testDeleteProject(){
          System.out.println("testDeleteProject");
          // create new test record
        Project instance = new Project("Test Project 3",
             5, 36, "Testing loadProject()",
             "3/29/18");
        String projectName = "";
        String resultValue = "";
        
        instance.deleteProject(36);
        
        String query = "SELECT ProjectName FROM Projects where ProjectID = 36";
        
        try 
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect,USER,PWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
        while (rs.next()) 
        {
            resultValue = rs.getString("ProjectName");
        }
        
        assertEquals(resultValue,projectName);
        
          
      }
         catch (SQLException | ClassNotFoundException e ) {
        System.out.println("An exception occurred");
      }
   
}
}
