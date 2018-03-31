
import java.sql.*;

/**
 *
 * @author jmehl, glane
 */
public class Project
{
    private final String driver = "com.mysql.jdbc.Driver";
    private final String connect = "jdbc:mysql://localhost:3306/CMSC495";
    private final String user = "root"; // Change this to mysql username
    private final String pword = "root"; // change this to mysql password

    private String projectName;
    private int priority;
    private String projectAssignedTo;
    private int projectID;
    private String projectSummary;
    private String projectDueDate;
    private Statement statement;
    private PreparedStatement prepStatement;
    private Connection con;
  
    /**
     *
     * Create Project and create record in Project table
     * @param projectName - short name of project goal
     * @param priority - integer ranking priority of project
     * @param projectAssignedTo - project assignment to team
     * @param projectID - unique ID tracking project
     * @param projectSummary - string describing project in more detail
     * @param projectDueDate - date when project needs to be completed
     */
     public Project(String projectName,
             int priority, String projectAssignedTo,
             int projectID, String projectSummary,
             String projectDueDate) 
     {
         this.projectName = projectName;
         this.priority = priority;
         this.projectAssignedTo = projectAssignedTo;
         this.projectID = projectID;
         this.projectSummary = projectSummary;
         this.projectDueDate = projectDueDate;
         
         try
         {
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
             
             
         
             prepStatement = con.prepareStatement("Insert into Projects values "
                                                + "(?,?,?,?,?,?);");
             prepStatement.setInt(1, projectID);
             prepStatement.setString(2, projectName);
             prepStatement.setString(3, projectAssignedTo);
             prepStatement.setString(4, projectDueDate);
             prepStatement.setInt(5, priority);
             prepStatement.setString(6, projectSummary);
         
             prepStatement.execute(); // perform insert
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
         }
         
    } // end constructor
     
     /**
     *
     * Modify String record in Project table
     * @param colToModify - short name of project goal
     * @param newValue - string value to update in table
     * @param projectID - unique ID tracking project
     */
     public void modifyProjectString(String colToModify, String newValue, 
                               int projectID) 
     {
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
         
             prepStatement = con.prepareStatement("UPDATE Projects "
                                                + "SET " + colToModify + " = ?" // Replaced ? with column name, prepStatemnet.setString adds " " around column name
                                                + "WHERE projectID = ?");

           //  prepStatement.setString(1, colToModify);
             prepStatement.setString(1, newValue);
             prepStatement.setInt(2, projectID);
         
             prepStatement.execute(); // perform update
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
         }
    } // end modifyProjectString()
     
     /**
     *
     * Modify integer record in Project table
     * @param colToModify - short name of project goal
     * @param newValue - integer value to update in table
     * @param projectID - unique ID tracking project
     */
     public void modifyProjectInt(String colToModify, int newValue, 
                               int projectID) 
     {
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
         
             prepStatement = con.prepareStatement("UPDATE Projects "
                                                + "SET " + colToModify + "= ? " // Same as above method, setString adds " " to column name
                                                + "WHERE projectID = ?;");

             //prepStatement.setString(1, colToModify);
             prepStatement.setInt(1, newValue);
             prepStatement.setInt(2, projectID);
         
             prepStatement.execute(); // perform update
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
         }
    } // end modifyProjectInt()
     
     /**
     *
     * Search for project and return result set of SQL query
     * @param projectName - short name of project goal
     * @param projectID - unique ID tracking project
     * @return result set of select query
     */
     public ResultSet loadProject(String projectName, int projectID) 
     {
         ResultSet rs = null;
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
         
             prepStatement = con.prepareStatement("SELECT projectID"
                                                + "FROM Projects"
                                                + "WHERE projectID = ?");

             prepStatement.setString(1, projectName);
             prepStatement.setInt(2, projectID);
         
             rs = prepStatement.executeQuery(); // perform update
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occured");
         }
         
         return rs;
    } // end loadProject()

} // end Project class
