package JJIGSAWED;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jmehl, glane
 */
public class Task {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String CONNECT = "jdbc:mysql://localhost:3306/CMSC495";
    private static final String USER = DBInteraction.getDBUsername(); 
    private static final String PWORD = DBInteraction.getDBPassword(); 
    
    private String taskName;
    private int priority;
    private String taskDateCreated;
    private int taskID;
    private int projectID;
    private int USERID;
    private String taskSummary;
    private String taskDateEnded;
    private static Statement statement;
    private static PreparedStatement prepStatement;
    private static Connection con;
    
    
     /**
     *
     * Create Task and create record in task table
     * @param taskName - short name of task
     * @param priority - integer ranking priority of task
     * @param USERID - USER assigned to project
     * @param projectID - unique ID to project
     * @param taskID - unique ID to task
     * @param taskSummary - string describing task in more detail
     * @param taskDateCreated - date when task was created
     * @param taskDateEnded - date task is due
     * @author glane
     */
    
    public Task(String taskName, 
            int priority, int USERID, 
            int projectID, int taskID,
            String taskSummary, String taskDateCreated,
            String taskDateEnded){ // Constructor will insert a task record
        
        this.taskName = taskName;
        this.priority = priority;
        this.USERID = USERID;
        this.projectID = projectID;
        this.taskID = taskID;
        this.taskSummary = taskSummary;
        this.taskDateCreated = taskDateCreated;
        this.taskDateEnded = taskDateEnded;
        
        try
         {
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
             
             
         
             prepStatement = con.prepareStatement("Insert into Tasks values "
                                                + "(?,?,?,?,?,?,?,?);");
             prepStatement.setInt(1, taskID);
             prepStatement.setInt(2, projectID);
             prepStatement.setInt(3, USERID);
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
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
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
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
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
      public static ResultSet loadTasks(int projectID) 
     {
         ResultSet rs = null;
         
         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
             prepStatement = con.prepareStatement("SELECT TaskName, TaskSummary, TaskPriority, TaskDateCreated, TaskDateEnded"
                                                + " FROM Tasks"
                                                + " WHERE FKProjectID = ?");

             //prepStatement.setString(1, projectName);
             prepStatement.setInt(1, projectID);
         
             rs = prepStatement.executeQuery(); // perform update
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occured");
         }
         
         return rs;
    } // end loadTask()
      
     /**
     *
     * Find list of all taskIDs present in DB 
     * @return result set of select query
     * @author jmehl
     */
     public static ArrayList getTaskIDs(int projectID) 
     {
         
         ArrayList<Integer> taskIDList = new ArrayList<Integer>();
         
         try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
             prepStatement = con.prepareStatement("SELECT TaskID "
                                                + "FROM CMSC495.Tasks "
                                                + "WHERE FKProjectID = ?");
         
             prepStatement.setInt(1, projectID);
             
             ResultSet rs = prepStatement.executeQuery(); // perform query
             
             // convert resultset to array
             while (rs.next()) 
             {
                int i = rs.getInt(1);
                
                taskIDList.add(i);
             }
         
             con.close();    
             rs.close();
         }
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println("An exception occured");
            System.out.println(ex);
         }
         
         return taskIDList;
    } // end getTaskIDs()
      
      /**
     *
     * Search for task and return result set of SQL query
     * @param taskID - unique ID tracking task
     * @author glane
     */
      
      public void deleteTask(int taskID){
          try
         {        
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT,USER,PWORD);
         
             prepStatement = con.prepareStatement("DELETE FROM Tasks WHERE TaskID = ?;");

             //prepStatement.setString(1, projectName);
             prepStatement.setInt(1, taskID);
             
             prepStatement.execute(); // Perform delete
             
             con.close();
         } 
         catch (ClassNotFoundException | SQLException ex) 
         {
            System.out.println(ex.getMessage());
         }
          
      } // deleteTask
}


