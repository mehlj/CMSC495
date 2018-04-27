package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import JJIGSAWED.User;
import JJIGSAWED.Task;
import java.util.ArrayList;
import java.sql.ResultSet;
import JJIGSAWED.Project;

public final class TasksUpdate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 int projectID = Integer.parseInt(request.getParameter("ProjectID")); 
      out.write('\n');
 String taskName = request.getParameter("task_name"); 
      out.write('\n');
 int taskPriority = Integer.parseInt(request.getParameter("task_priority")); 
      out.write('\n');
 int userAssignment = Integer.parseInt(request.getParameter("user_assignment")); 
      out.write('\n');
 String dateCreated = request.getParameter("date_created"); 
      out.write('\n');
 String dateEnded = request.getParameter("date_ended"); 
      out.write('\n');
 String taskSummary = request.getParameter("task_summary"); 
      out.write("\n");
      out.write("<!--\n");
      out.write("HTML written by: Dave Thatcher & Jason Willis \n");
      out.write("Class: CMSC 495 UMUC\n");
      out.write("Date: 4/13/2018\n");
      out.write("-->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("    \n");
      out.write("<head>\n");
      out.write("  <title>Tasks Page</title>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"style.css\">\n");
      out.write("  <!-- <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script> -->\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("  <!-- <header> -->\n");
      out.write("  <div class=\"jumbotron\">\n");
      out.write("    <h1 align='center'>Manage Tasks</h1>\n");
      out.write("    <nav class=\"navbar navbar-right\">\n");
      out.write("      <ul class=\"nav navbar-nav nav-pills nav-fill\">\n");
      out.write("        <li><a href=\"index.html\">Home</a></li>\n");
      out.write("        <li><a href=\"Projects.jsp\">Projects</a></li>\n");
      out.write("        <li><a href=\"UserSetup.jsp\">User Setup</a></li>\n");
      out.write("        <li><a href=\"#\">Sign Out</a></li>\n");
      out.write("      </ul>\n");
      out.write("    </nav>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("  <!-- Could Use Alerts Success/Error after Save | Search:  https://getbootstrap.com/docs/3.3/components/#alerts\n");
      out.write("<div class=\"alert alert-success\" role=\"alert\">You Successfully Saved Your Information!</div>\n");
      out.write("<div class=\"alert alert-danger\" role=\"alert\">Something Went Wrong, Please Try Again!</div> -->\n");
      out.write("\n");
      out.write("  <!-- Input / Search Form -->\n");
      out.write("  <!-- Top Row -->\n");
      out.write("  <div class=\"container\" id=\"projectInput\">\n");
      out.write("    <div class=\"panel panel-default\">\n");
      out.write("      <!-- Default panel contents -->\n");
      out.write("      <div class=\"panel-heading\">Project Informaton to Input or Search:</div>\n");
      out.write("      <!-- <div class=\"col-xs-12 col-sm-12\"> -->\n");
      out.write("\n");
      out.write("      <!-- Task Name -->\n");
      out.write("      <!-- <div class=\"col-sm-3\"> -->\n");
      out.write("      <form class=\"form-horizontal\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/TaskCreate?ProjectID=");
      out.print( projectID );
      out.write("\" method=\"post\">\n");
      out.write("        <div class=\"form-group.required\" style=\"margin-top: 10px\">\n");
      out.write("          <label class=\"control-label mt-2 col-sm-2\">*Task Name:</label>\n");
      out.write("          <div class=\"col-sm-4\">\n");
      out.write("              <input class=\"form-control\" type=\"text\" name=\"task_name\" id=\"task_name\" placeholder=\"\" value=\"");
      out.print( taskName );
      out.write("\">\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("      <!-- Priority -->\n");
      out.write("      <!-- <div class=\"col-sm-3\"> -->\n");
      out.write("        <div class=\"form-group.required\" style=\"margin-top: 10px\">\n");
      out.write("          <label class=\"control-label col-sm-2\">*Priority:</label>\n");
      out.write("          <div class=\"col-sm-4\">\n");
      out.write("            <select class=\"form-control\" name=\"task_priority\" id=\"task_priority\">\n");
      out.write("            ");
 
            if (taskPriority == 3) {
            
      out.write("\n");
      out.write("            <option selected value=\"3\">Low</option>\n");
      out.write("            ");

            }
            else {
            
      out.write("\n");
      out.write("            <option value=\"3\">Low</option>\n");
      out.write("            ");

            }
            
      out.write("\n");
      out.write("            ");
 
            if (taskPriority == 2) {
            
      out.write("\n");
      out.write("            <option selected value=\"2\">Medium</option>\n");
      out.write("            ");

            }
            else {
            
      out.write("\n");
      out.write("            <option value=\"2\">Medium</option>\n");
      out.write("            ");

            }
            
      out.write("\n");
      out.write("            ");
 
            if (taskPriority == 1) {
            
      out.write("\n");
      out.write("            <option selected value=\"1\">High</option>\n");
      out.write("            ");

            }
            else {
            
      out.write("\n");
      out.write("            <option value=\"1\">High</option>\n");
      out.write("            ");

            }
            
      out.write("\n");
      out.write("          </select>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("      <!-- User Assignment -->\n");
      out.write("      <!-- <div class=\"col-sm-3\"> -->\n");
      out.write("        <div class=\"form-group.required\" style=\"margin-top: 10px\">\n");
      out.write("          <label class=\"control-label col-sm-2\">*User Assignment:</label>\n");
      out.write("          <div class=\"col-sm-4\">\n");
      out.write("            <select class=\"form-control\" name=\"user_assignment\" id=\"user_assignment\">\n");
      out.write("            ");

                        ArrayList<Integer> userList = User.getUserIDs();


                        for (int i = 0; i < userList.size(); i++) 
                        {
                            ResultSet rs = User.getUser(userList.get(i));

                            // convert resultset to array
                        while (rs.next()) 
                        {
                
      out.write("\n");
      out.write("                ");

                if (userAssignment == Integer.parseInt(rs.getString("UserID")))
                {
                    
      out.write("\n");
      out.write("                    <option selected value=\"");
      out.print( rs.getString("UserID") );
      out.write('"');
      out.write('>');
      out.print( rs.getString("Name") );
      out.write("</option>\n");
      out.write("                    ");

                }
                else
                {
                
      out.write("\n");
      out.write("                <option value=\"");
      out.print( rs.getString("UserID") );
      out.write('"');
      out.write('>');
      out.print( rs.getString("Name") );
      out.write("</option>\n");
      out.write("                ");

                        }
                    }
                }
                
      out.write("\n");
      out.write("          </select>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("      <!-- Start Date -->\n");
      out.write("      <!-- <div class=\"col-sm-3\"> -->\n");
      out.write("        <div class=\"form-group.required\" style=\"margin-top: 60px\">\n");
      out.write("          <label class=\"control-label col-sm-2\">*Start Date:</label>\n");
      out.write("          <div class=\"col-sm-4\">\n");
      out.write("              <input class=\"form-control\" type=\"date\" name=\"date_created\" id=\"date_created\" value=\"");
      out.print( dateCreated );
      out.write("\">\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("      <!-- Due Date -->\n");
      out.write("      <!-- <div class=\"col-sm-3\"> -->\n");
      out.write("        <div class=\"form-group.required\" style=\"margin-top: 60px\">\n");
      out.write("          <label class=\"control-label col-sm-2\">*Due Date:</label>\n");
      out.write("          <div class=\"col-sm-4\">\n");
      out.write("            <input class=\"form-control\" type=\"date\" name=\"date_ended\" id=\"date_ended\" value=\"");
      out.print( dateEnded );
      out.write("\">\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("          \n");
      out.write("      <!-- Search | Save buttons -->\n");
      out.write("        <div class=\"form-group.required\" style=\"margin-top: 110px\">\n");
      out.write("          <label class=\"control-label col-sm-2\">*Search | Save:</label>\n");
      out.write("          <div class=\"col-sm-4\">\n");
      out.write("            <button type=\"button\" class=\"btn btn-info\">Search</button>\n");
      out.write("            <button type=\"submit\" class=\"btn btn-success\">Save</button>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("      <!-- Task Summary -->\n");
      out.write("        <div class=\"form-group\">\n");
      out.write("          <div class=\"form-group.required\" style=\"margin-top: 50px; margin-left:10px\">\n");
      out.write("            <label class=\"control-label col-sm-2\">*Task Summary:</label>\n");
      out.write("            <div class=\"col-sm-4\">\n");
      out.write("              <textarea class=\"form-control\" name=\"task_summary\" id=\"task_summary\" rows=\"5\" placeholder=\"Describe your task here...\" value=\"");
      out.print( taskSummary );
      out.write("\"></textarea>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("      <!-- Whole Form -->\n");
      out.write("      </form>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("  <!-- </div> -->\n");
      out.write("  <!-- Container Div -->\n");
      out.write("\n");
      out.write("\n");
      out.write("  <br />\n");
      out.write("  <br />\n");
      out.write("\n");
      out.write("  <div class=\"container\">\n");
      out.write("    <table class=\"table table-hover table-responsive-md table-fixed\">\n");
      out.write("                <tr>\n");
      out.write("                  <th>Name</th>\n");
      out.write("                  <th>Summary</th>\n");
      out.write("                  <th>Priority</th>\n");
      out.write("                  <th>Assigned User</th>\n");
      out.write("                  <th>Project ID</th>\n");
      out.write("                  <th>Edit</th>\n");
      out.write("                  <th>Remove</th>\n");
      out.write("                </tr>\n");
      out.write("                ");


                   ArrayList<Integer> list = Task.getTaskIDs(projectID);

                   for (int i = 0; i < list.size(); i++) 
                   {
                       ResultSet rs = Task.loadTasks(list.get(i)); 

                       // convert resultset to array
                       while (rs.next()) 
                       {
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                <td>");
      out.print( rs.getString("TaskName") );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( rs.getString("TaskSummary") );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( rs.getString("TaskPriority") );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( User.getUserName(rs.getInt("FKUserID")) );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( projectID );
      out.write("</td>\n");
      out.write("                <td>\n");
      out.write("                    <a href=\"TaskEdit?TaskName=");
      out.print( rs.getString("TaskName") );
      out.write("&ProjectID=");
      out.print( projectID );
      out.write("\">\n");
      out.write("                    <button>Edit Task</button>\n");
      out.write("                    </a>\n");
      out.write("                </td>\n");
      out.write("                <td>\n");
      out.write("                    <a href=\"TaskRemove?TaskName=");
      out.print( rs.getString("TaskName") );
      out.write("&ProjectID=");
      out.print( projectID );
      out.write("\">\n");
      out.write("                    <button>Remove Task</button>\n");
      out.write("                    </a>\n");
      out.write("                </td>\n");
      out.write("                </tr>\n");
      out.write("                ");


                       }
                   }

                
      out.write("\n");
      out.write("        </table>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("  <!-- Pagination -->\n");
      out.write("    <ul class=\"pagination\">\n");
      out.write("      <li>\n");
      out.write("        <a href=\"#\" aria-label=\"Previous\">\n");
      out.write("        <span aria-hidden=\"true\">&laquo;</span>\n");
      out.write("      </a>\n");
      out.write("      </li>\n");
      out.write("      <li><a href=\"#\">1</a></li>\n");
      out.write("      <li><a href=\"#\">2</a></li>\n");
      out.write("      <li><a href=\"#\">3</a></li>\n");
      out.write("      <li><a href=\"#\">4</a></li>\n");
      out.write("      <li><a href=\"#\">5</a></li>\n");
      out.write("      <li>\n");
      out.write("        <a href=\"#\" aria-label=\"Next\">\n");
      out.write("        <span aria-hidden=\"true\">&raquo;</span>\n");
      out.write("      </a>\n");
      out.write("      </li>\n");
      out.write("    </ul>\n");
      out.write("\n");
      out.write("\n");
      out.write("  <!-- Footer -->\n");
      out.write("  <div class=\"footer navbar-fixed-bottom well\">\n");
      out.write("    <p class=\"text-center text-muted\">JJIGSAWED Software Company, Copyright 2018.</p>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
