
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jmehl, glane
 */
public class Task {
    
    private final String driver = "com.mysql.jdbc.Driver";
    private final String connect = "jdbc:mysql://localhost:3306/CMSC495";
    private final String user = "root"; // Change this to mysql username
    private final String pword = "root"; // change this to mysql password 
    
    private String taskName;
    private int priority;
    private String taskDateCreated;
    private int taskID;
    private int projectID;
    private int userID;
    private String taskSummary;
    private String taskDateEnded;
    private Statement statement;
    private PreparedStatement prepStatement;
    private Connection con;
    
    
     /**
     *
     * Create Task and create record in task table
     * @param taskName - short name of task
     * @param priority - integer ranking priority of task
     * @param userID - user assigned to project
     * @param projectID - unique ID to project
     * @param taskID - unique ID to task
     * @param taskSummary - string describing task in more detail
     * @param taskDateCreated - date when task was created
     * @param taskDateEnded - date task is due
     * @author glane
     */
    
    public Task(String taskName, 
            int priority, int userID, 
            int projectID, int taskID,
            String taskSummary, String taskDateCreated,
            String taskDateEnded){ // Constructor will insert a task record
        
        this.taskName = taskName;
        this.priority = priority;
        this.userID = userID;
        this.projectID = projectID;
        this.taskID = taskID;
        this.taskSummary = taskSummary;
        this.taskDateCreated = taskDateCreated;
        this.taskDateEnded = taskDateEnded;
        
        try
         {
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
             
             
         
             prepStatement = con.prepareStatement("Insert into Tasks values "
                                                + "(?,?,?,?,?,?,?,?);");
             prepStatement.setInt(1, taskID);
             prepStatement.setInt(2, projectID);
             prepStatement.setInt(3, userID);
             prepStatement.setString(4, taskName);
             prepStatement.setString(5, taskDateCreated);
             prepStatement.setString(6, taskDateEnded);
             prepStatement.setInt(7, priority);
             prepStatement.setString(8,taskSummary);
         
             prepStatement.execute(); // perform insert
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
         }
         
        
        
    } // end constructor
    
     /**
     * Modify string record in Task table
     * @param colToModify - short name of project goal
     * @param newValue - integer value to update in table
     * @param taskID - unique ID tracking task
     * @author glane
     */
    public void modifyTaskString(String colToModify, String newValue, 
                               int taskID) 
     {
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
         
             prepStatement = con.prepareStatement("UPDATE Tasks "
                                                + "SET " + colToModify + " = ?" 
                                                + "WHERE taskID = ?");

         
             prepStatement.setString(1, newValue);
             prepStatement.setInt(2, taskID);
         
             prepStatement.execute(); // perform update
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
         }
    } // end modifyTaskString()
    
    
    /**
     * Modify integer record in Project table
     * @param colToModify - short name of project goal
     * @param newValue - integer value to update in table
     * @param taskID - unique ID tracking task
     * @param projectID - unique ID tracking project
     * @author glane
     */
     public void modifyTaskInt(String colToModify, int newValue, 
                               int taskID, int projectID) 
     {
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
         
             prepStatement = con.prepareStatement("UPDATE Tasks "
                                                + "SET " + colToModify + " = ? " 
                                                + "WHERE FKProjectID = ? AND TaskID = ?;");

             
             prepStatement.setInt(1, newValue);
             prepStatement.setInt(2, projectID);
             prepStatement.setInt(3, taskID);
             
            // System.out.println(prepStatement.toString());
         
             prepStatement.execute(); // perform update
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occurred");
         }
    } // end modifyTaskInt()
     
     
     /**
     *
     * Search for project and return result set of SQL query
     * @param projectID - unique ID tracking project
     * @return result set of select query
     * @author glane
     */
      public ResultSet loadTasks(int projectID) 
     {
         ResultSet rs = null;
         
         try
         {        
             Class.forName(driver);
             con = DriverManager.getConnection(connect,user,pword);
         
             prepStatement = con.prepareStatement("SELECT TaskName, TaskSummary, TaskPriority, TaskDateCreated, TaskDateEnded"
                                                + "FROM Tasks"
                                                + "WHERE projectID = ?");

             //prepStatement.setString(1, projectName);
             prepStatement.setInt(1, projectID);
         
             rs = prepStatement.executeQuery(); // perform update
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occured");
         }
         
         return rs;
    } // end loadTask()
}


