
import JJIGSAWED.DBInteraction;
import JJIGSAWED.Task;
import java.sql.ResultSet;
import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;

/**
 *
 * @author jmehl, glane
 */
public class TaskTest {

    private final String driver = "com.mysql.jdbc.Driver";
    private final String connect = "jdbc:mysql://localhost:3306/CMSC495";
    private static final String USER = DBInteraction.getDBUsername(); 
    private static final String PWORD = DBInteraction.getDBPassword();   

    public TaskTest() {
    }

    /**
     * Test of modifyProjectString method, of class Test.
     *
     * @author glane
     */
    @Test
    public void testModifyTaskString() {
        System.out.println("* TaskTest: testModifyTaskString()");
        String colToModify = "TaskName";
        String originalValue = "Test Task 1";
        String newValue = "NewTaskToComplete";

        // query string to later validate record has been updated
        String query = "SELECT TaskName FROM Tasks "
                + "WHERE TaskName = 'NewTaskToComplete'";

        // create new test record
        Task instance = new Task("Test Task 1",
                1, 1, 1, 1, "Testing modifyTaskString()",
                "3/29/18", " ");
        instance.modifyTaskString(colToModify, newValue, 1);

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, USER, PWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                originalValue = rs.getString("TaskName");
            }

            // ensure that original value has been updated
            assertEquals(originalValue, newValue);

            // drop test record from table once test has completed
            PreparedStatement prepStatement;
            prepStatement = con.prepareStatement("DELETE FROM Tasks "
                    + "WHERE TaskID = 1");
            prepStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An exception occurred");
        }
    }

    /**
     * Test of modifyTasktInt method, of class Test.
     *
     * @author glane
     */
    @Test
    public void testModifyTestInt() {
        System.out.println("* TaskTest: testModifyTaskInt()");
        String colToModify = "TaskPriority";
        int originalValue = 5;
        int newValue = 7;
        int taskID = 2;
        int projectID = 1;

        // query string to later validate record has been updated
        String query = "SELECT TaskPriority FROM Tasks "
                + "WHERE TaskPriority = 7 AND FKProjectID = 1";

        // create new test record
        Task instance = new Task("Test Task 1",
                5, 1, 1, 2, "Testing modifyTaskInt()",
                "3/29/18", " ");
        instance.modifyTaskInt(colToModify, newValue, taskID, projectID);

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, USER, PWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                originalValue = rs.getInt("TaskPriority");
            }

            // ensure that original value has been updated
            assertEquals(originalValue, newValue);

            // drop test record from table once test has completed
            PreparedStatement prepStatement;
            prepStatement = con.prepareStatement("DELETE FROM Tasks "
                    + "WHERE FKprojectID = 1");
            prepStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An exception occurred");
        }
    }

    /**
     * Test of loadTask method, of class Task.
     *
     * @author glane
     */
    @Test
    public void testLoadTask() {

        System.out.println("* TaskTest: testLoadTasks");
        // String colToModify = "ProjectPriority";
        String expectedValue = "Test Task 1";
        String resultValue = "";
        // int projectID = 103;

        // query string to later validate record has been updated
        String query = "SELECT TaskName FROM Tasks "
                + "WHERE TaskName = 'Test Task 1' AND FKProjectID = 1";

        // create new test task record
        Task instance = new Task("Test Task 1",
                5, 1, 1, 2, "Testing loadTask()",
                "3/29/18", " ");

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, USER, PWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                resultValue = rs.getString("TaskName");
            }

            // ensure that result set is returning proper data
            assertEquals(expectedValue, resultValue);

            // drop test record from table once test has completed
            PreparedStatement prepStatement;
            prepStatement = con.prepareStatement("DELETE FROM Tasks "
                    + "WHERE taskID = 2");
            prepStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An exception occurred");
        }
    }

    /**
     * Test of createTask method, of class Task.
     *
     * @author glane
     */
    @Test
    public void testCreateTask() {

        System.out.println("* TaskTest: CreateTask()");
        String expectedValue = "Task Task Create Task";
        String resultValue = "";

        // query string to later validate record has been updated
        String query = "SELECT TaskName FROM Tasks "
                + "WHERE TaskName = 'Task Task Create Task'";

        // create new test record
        Task instance = new Task("Task Task Create Task",
                5, 1, 1, 2, "Testing Create Task",
                "3/29/18", " ");

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, USER, PWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                resultValue = rs.getString("TaskName");
            }

            // ensure that result set is returning proper data
            assertEquals(expectedValue, resultValue);

            // drop test record from table once test has completed
            PreparedStatement prepStatement;
            prepStatement = con.prepareStatement("DELETE FROM Tasks "
                    + "WHERE FKprojectID = 1 AND TaskID = 2");
            prepStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An exception occurred");
        }
    }

    @Test
    public void deleteTask() {
        System.out.println("* TaskTest: DeleteTask()");
        String expectedValue = "";
        String resultValue = "";
        Task instance = new Task("Task Task Create Task",
                5, 1, 1, 12, "Testing Create Task",
                "3/29/18", " ");
        instance.deleteTask(12);

        String query = "SELECT * FROM Tasks WHERE taskID = 12";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connect, USER, PWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                resultValue = rs.getString("TaskName");
            }

            // ensure that result set is returning proper data
            assertEquals(expectedValue, resultValue);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An exception occurred");
        }

    }
}
