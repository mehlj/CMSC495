
import java.sql.*;


/**
 *
 * @author jmehl & GLANE
 */
public class Project {

    private String projectName;
    private int priority;
    private String projectAssignedTo;
    private int projectID;
    private String projectSummary;
    private String projectDueDate;
    private String connect;
    private Statement statement;
    private PreparedStatement prepStatement;
    private String driver;
    private String user;
    private String pword;
    private Connection con;
    

    // Create Project and create record in project table
    public void createProject(String projectName,
            int priority, String projectAssignedTo,
            int projectID, String projectSummary,
            String projectDueDate) {

        this.projectName = projectName;
        this.priority = priority;
        this.projectAssignedTo = projectAssignedTo;
        this.projectID = projectID;
        this.projectSummary = projectSummary;
        this.projectDueDate = projectDueDate;
        
      try{
          
          driver = "com.mysql.jdbc.Driver";
          connect= "jdbc:mysql://localhost:3306/CMSC495";
          user = "root"; // Change this to mysql username
          pword = "root"; // change this to mysql password
          Class.forName(driver);
          con = DriverManager.getConnection(connect,user,pword);
          prepStatement = con.prepareStatement("Insert into Projects values ?,?,?,?,?,?");
          prepStatement.setInt(1, projectID);
          prepStatement.setString(2, projectName);
          prepStatement.setString(3, projectAssignedTo);
          prepStatement.setString(4, projectDueDate);
          prepStatement.setInt(5, priority);
          prepStatement.setString(6, projectSummary);
          
          prepStatement.execute(); // perform insert
          
      } catch (ClassNotFoundException ex) {
            System.out.println("An exception occured");
        } catch (SQLException x) {
            System.out.println("An exception occured");
        }

    } // end create project

}
