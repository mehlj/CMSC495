/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JJIGSAWED;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jmehl
 */
@WebServlet(name = "TaskCreate", urlPatterns = {"/TaskCreate"})
public class TaskCreate extends HttpServlet {

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
        
        int projectID = Integer.parseInt(request.getParameter("ProjectID"));
        
        
        if (!"".equals(request.getParameter("task_name"))
         && !"".equals(request.getParameter("task_summary"))
         && !"".equals(request.getParameter("date_created"))
         && !"".equals(request.getParameter("task_priority"))
         && !"".equals(request.getParameter("user_assignment"))) 
        {   
            // generate task ID
            Random rand = new Random();
            int taskID = rand.nextInt(100000) + 1;
            
            // convert priority to int
            int priority = Integer.parseInt(request.getParameter("task_priority"));
            int userID = Integer.parseInt(request.getParameter("user_assignment"));
            
            Task tsk = new Task(request.getParameter("task_name"),
                    priority,
                    userID,
                    projectID,
                    taskID,
                    request.getParameter("task_summary"),
                    request.getParameter("date_created"),
                    request.getParameter("date_ended"));
            
                    try
                    {
                        response.sendRedirect("TaskSuccess.jsp?ProjectID=" + projectID);
                    }
                    catch (IOException ex)
                    {
                        response.sendRedirect("TaskFail.jsp?ProjectID=" + projectID);
                    }
        }
        else
        {
            response.sendRedirect("TaskFail.jsp?ProjectID=" + projectID);
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
