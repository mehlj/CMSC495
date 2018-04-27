/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JJIGSAWED;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jmehl
 */
@WebServlet(name = "TaskEdit", urlPatterns = {"/TaskEdit"})
public class TaskEdit extends HttpServlet {
    
    private static final String DRIVER = DBInteraction.getDBDriver();
    private static final String CONNECT = DBInteraction.getDBConnect();
    private static final String USER = DBInteraction.getDBUsername(); 
    private static final String PWORD = DBInteraction.getDBPassword(); 
    private static Statement statement;
    private static PreparedStatement prepStatement;
    private static Connection con;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            
        String taskName = request.getParameter("task_name");
        String originalTaskName = request.getParameter("OriginalTaskName");
        String taskSummary = request.getParameter("task_summary");
        String taskDateCreated = request.getParameter("date_created");
        String taskDateEnded = request.getParameter("date_ended");
        // convert priority to int
        int taskPriority = Integer.parseInt(request.getParameter("task_priority"));
        int originalTaskPriority = Integer.parseInt(request.getParameter("OriginalTaskPriority"));
        int userID = Integer.parseInt(request.getParameter("user_assignment"));
        int projectID = Integer.parseInt(request.getParameter("ProjectID"));
        System.out.println("taskPriority is: " + originalTaskPriority); 
        System.out.println("taskName is: " + originalTaskName); 
        
        
        // get task ID from name and priority
        int taskID = Task.getTaskID(originalTaskName, originalTaskPriority);
        System.out.println(taskID);
            
         try {
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT, USER, PWORD);

             prepStatement = con.prepareStatement("UPDATE CMSC495.Tasks"
                     + " SET TaskName = ?, TaskSummary = ?, TaskDateCreated = ?, TaskDateEnded = ?, TaskPriority = ?, FKUserID = ?"
                     + " WHERE TaskID = ?");

             prepStatement.setString(1, taskName);
             prepStatement.setString(2, taskSummary);
             prepStatement.setString(3, taskDateCreated);
             prepStatement.setString(4, taskDateEnded);
             prepStatement.setInt(5, taskPriority);
             prepStatement.setInt(6, userID);
             prepStatement.setInt(7, taskID);

             prepStatement.execute(); // perform update

             // redirect to success page
             response.sendRedirect("TaskSuccess.jsp?ProjectID=" + projectID);
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("An exception occured");
                System.out.println(ex);
              //  response.sendRedirect("TaskFail.jsp?ProjectID=" + projectID);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
