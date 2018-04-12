/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JJIGSAWED;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProjectServlet", urlPatterns = {"/ProjectServlet"})
public class ProjectServlet extends HttpServlet {

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
        
        if (request.getParameter("project_name") != null
         && request.getParameter("project_summary") != null
         && request.getParameter("due_date") != null
         && request.getParameter("project_priority") != null) 
        {
            // generate project ID
            Random rand = new Random();
            int projectID = rand.nextInt(100000) + 1;
            
            // convert priority to int
            int priority = Integer.parseInt(request.getParameter("project_priority"));
            
            Project prj = new Project(request.getParameter("project_name"),
                    priority,
                    request.getParameter("project_assigned_to"),
                    projectID,
                    request.getParameter("project_summary"),
                    request.getParameter("due_date"));
        }
        else
        {
            try (PrintWriter out = response.getWriter())
            {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ProjectServlet Error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Missing Arguments</h1>");
            out.println("</body>");
            out.println("</html>");
            }
        }
        
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Project Creation Complete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Success</h1>");
            out.println("Project successfully created.");
            out.println("Return to the Projects page: ");
            out.println("<a href=Projects.jsp>Return to Projects</a>");
            out.println("</body>");
            out.println("</html>");
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
