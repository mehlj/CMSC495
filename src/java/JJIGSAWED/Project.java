package JJIGSAWED;


import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jmehl, glane
 */
public class Project
{
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String CONNECT = "jdbc:mysql://localhost:3306/CMSC495";
    private static final String USER = "root"; // Change this to mysql USERname
    private static final String PWORD = "root"; // change this to mysql password

    private String projectName;
    private int priority;
    private String projectAssignedTo;
    private int projectID;
    private String projectSummary;
    private String projectDueDate;
    private static Statement statement;
    private static PreparedStatement prepStatement;
    private static Connection con;
  
    /**
     *
     * Create Project and create record in Project table
     * @param projectName - short name of project goal
     * @param priority - integer ranking priority of project
     * @param projectAssignedTo - project assignment to team
     * @param projectID - unique ID tracking project
     * @param projectSummary - string describing project in more detail
     * @param projectDueDate - date when project needs to be completed
     * @author glane
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
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
             
             
         
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
            System.out.println(ex);
         }
         
    } // end constructor
     
     /**
     *
     * Modify String record in Project table
     * @param colToModify - short name of project goal
     * @param newValue - string value to update in table
     * @param projectID - unique ID tracking project
     * @author jmehl
     */
     public void modifyProjectString(String colToModify, String newValue, 
                               int projectID) 
     {
         
         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
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
            System.out.println(ex);
         }
    } // end modifyProjectString()
     
     /**
     *
     * Modify integer record in Project table
     * @param colToModify - short name of project goal
     * @param newValue - integer value to update in table
     * @param projectID - unique ID tracking project
     * @author jmehl
     */
     public void modifyProjectInt(String colToModify, int newValue, 
                               int projectID) 
     {
         
         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
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
            System.out.println(ex);
         }
    } // end modifyProjectInt()
     
     /**
     *
     * Search for project and return result set of SQL query
     * @param projectID - unique ID tracking project
     * @return result set of select query
     * @author jmehl
     */
     public static ResultSet loadProject(int projectID) 
     {
         ResultSet rs = null;
         
         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
             prepStatement = con.prepareStatement("SELECT * "
                                                + "FROM CMSC495.Projects "
                                                + "WHERE projectID = ?");
             
             prepStatement.setInt(1, projectID);
         
             rs = prepStatement.executeQuery(); // perform update
             
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occured");
            System.out.println(ex);
         }
         
         return rs;
    } // end loadProject()
     
     /**
     *
     * Search for project and return 
     * @return result set of select query
     * @author jmehl
     */
     public static ArrayList getProjectIDs() 
     {
         
         ArrayList<Integer> projectIDList = new ArrayList<Integer>();
         
         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
             prepStatement = con.prepareStatement("SELECT * "
                                                + "FROM CMSC495.Projects");
         
             ResultSet rs = prepStatement.executeQuery(); // perform update
             
             // convert resultset to array
             while (rs.next()) 
             {
                int i = rs.getInt(1);
                
                projectIDList.add(i);
             }
         
             con.close();    
             rs.close();
         }
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occured");
            System.out.println(ex);
         }
         
         return projectIDList;
    } // end loadProject()
     
     /**
     *
     * Search for project and delete from database
     * @param projectID - unique ID tracking project
     * @author glane
     */
     
     public void deleteProject(int projectID){
         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
             prepStatement = con.prepareStatement("DELETE FROM Projects "
                                                + "WHERE projectID = ?;");

             prepStatement.setInt(1, projectID);
         
             prepStatement.execute(); // perform delete
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
           // System.out.println(ex);
         }
         
     } // end deleteProject();
     
     
} // end Project class

