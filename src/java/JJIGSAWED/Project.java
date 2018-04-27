package JJIGSAWED;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jmehl, glane
 */
public class Project
{
    private static final String DRIVER = DBInteraction.getDBDriver();
    private static final String CONNECT = DBInteraction.getDBConnect();

        
    private static final String USER = DBInteraction.getDBUsername(); 
    private static final String PWORD = DBInteraction.getDBPassword(); 
    
    
    private String projectName;
    private int priority;
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
     * @param projectID - unique ID tracking project
     * @param projectSummary - string describing project in more detail
     * @param projectDueDate - date when project needs to be completed
     * @author glane
     */
     public Project(String projectName,
             int priority, 
             int projectID, String projectSummary,
             String projectDueDate) 
     {
         this.projectName = projectName;
         this.priority = priority;
         this.projectID = projectID;
         this.projectSummary = projectSummary;
         this.projectDueDate = projectDueDate;
         
         try
         {
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
             
             
         
             prepStatement = con.prepareStatement("Insert into Projects values "
                                                + "(?,?,?,?,?);");
             prepStatement.setInt(1, projectID);
             prepStatement.setString(2, projectName);
             prepStatement.setString(3, projectDueDate);
             prepStatement.setInt(4, priority);
             prepStatement.setString(5, projectSummary);
         
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
    } // end getProjectIDs()
     
     /**
     *
     * Search for project and delete from database
     * @param projectID - unique ID tracking project
     * @author glane
     */
     
     
     public static void deleteProject(int projectID){

         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
             prepStatement = con.prepareStatement("DELETE FROM Projects "
                                                + "WHERE ProjectID = ?;");

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
     
    /**
     *
     * Obtain projectID given just project name and priority
     *
     * @param projectName - name of project
     * @param projectPriority - priority of project
     * @return - unique ID of project
     * @author jmehl
    */
    public static int getProjectID(String projectName, int projectPriority)
    {
        int projectID = 0;
        
        try 
        {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(CONNECT, USER, PWORD);

                prepStatement = con.prepareStatement("SELECT ProjectID "
                        + "FROM CMSC495.Projects "
                        + "WHERE ProjectName = ? AND ProjectPriority = ?");

                prepStatement.setString(1, projectName);
                prepStatement.setInt(2, projectPriority);

                ResultSet rs = prepStatement.executeQuery(); // perform query
                
                while (rs.next()) {
                    projectID = rs.getInt(1);
                }
        }
        
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println(ex);
        }
        return projectID;
    }

     
} // end Project class

