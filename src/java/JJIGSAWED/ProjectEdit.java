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
@WebServlet(name = "ProjectEdit", urlPatterns = {"/ProjectEdit"})
public class ProjectEdit extends HttpServlet {
    
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
        
            
        String projectName = request.getParameter("project_name");
        String projectDue = request.getParameter("due_date");
        int projectPriority = Integer.parseInt(request.getParameter("project_priority"));
        String projectSummary = request.getParameter("project_summary");
        
        // convert priority to int
        int originalProjectPriority = Integer.parseInt(request.getParameter("OriginalProjectPriority"));
        String originalProjectName = request.getParameter("OriginalProjectName");
        int projectID = 0;
        
        // get project ID from name and priority
        projectID = Project.getProjectID(originalProjectName, originalProjectPriority);
        System.out.println(projectID);
            
         try {
             Class.forName(DRIVER);
             con = DriverManager.getConnection(CONNECT, USER, PWORD);

             prepStatement = con.prepareStatement("UPDATE CMSC495.Projects"
                     + " SET ProjectName = ?, ProjectDue = ?, ProjectPriority = ?, ProjectSummary = ?"
                     + " WHERE ProjectID = ?");

             prepStatement.setString(1, projectName);
             prepStatement.setString(2, projectDue);
             prepStatement.setInt(3, projectPriority);
             prepStatement.setString(4, projectSummary);
             prepStatement.setInt(5, projectID);

             prepStatement.execute(); // perform update

             // redirect to success page
             response.sendRedirect("ProjectsSuccess.jsp");
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("An exception occured");
                System.out.println(ex);
                response.sendRedirect("ProjectsFail.jsp");
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
