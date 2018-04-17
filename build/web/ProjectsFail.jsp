<%-- 
    Document   : ProjectsFail
    Created on : Apr 16, 2018, 11:24:20 PM
    Author     : jmehl
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JJIGSAWED.Project"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JJIGSAWED Task Tracking Software</title>
        <link rel= "stylesheet" href="./style.css">
    </head>
    <body>
        <header>
            <div class="container">
                <div id="software_name">
                    <h1>Project Management</h1>
                    <h2><p style="color:red;">OPERATION ERROR</p></h2>
                </div>
                <nav>
                    <ul>
                        <li><a href="index.html">Home</a></li>
                        <li><a href="Projects.html">Projects</a></li>
                        <li><a href="Tasks.html">Tasks</a></li>
                        <li><a href="UserSetup.html">User Setup</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <form action="${pageContext.request.contextPath}/ProjectServlet" method="post">
            <fieldset>
                <legend>Project Information:</legend>
                Project Name: <input type ="text" name="project_name">
                Project Summary: <textarea name ="project_summary" 
                                            rows="10" cols="30"></textarea><br>
                Due Date: <input type="date" name="due_date"> <br>
                Priority: <select name="project_priority"> 
                    <option value="3">Low</option>
                    <option value="2">Normal</option>
                    <option value="1">High</option>
                </select><br>        
                Assigned To: <input type="text" name="project_assigned_to"><br>
                Schedule Filter: <select name="schedule_filter"> 
                    <option value="0">All</option>
                    <option value="7">7 Days</option>
                    <option value="14">14 Days</option>
                    <option value="30">30 Days</option>
                </select><br><br>
            </fieldset>
            <input type="submit" value="Add Project">
        </form>
        
            <table border=1 cellpadding=5>
                <tr>
                  <th>Name</th>
                  <th>Assigned To</th>
                  <th>Due</th>
                  <th>Priority</th>
                  <th>Summary</th>
                  <th>Remove</th>
                </tr>
                <%
                   ArrayList<Integer> list = Project.getProjectIDs();

                   for (int i = 0; i < list.size(); i++) 
                   {
                       ResultSet rs = Project.loadProject(list.get(i));

                       // convert resultset to array
                       while (rs.next()) 
                       {
                %>
                <tr>
                <td><%= rs.getString("ProjectName") %></td>
                <td><%= rs.getString("ProjectAssignedTo") %></td>
                <td><%= rs.getString("ProjectDue") %></td>
                <td><%= rs.getInt("ProjectPriority") %></td>
                <td><%= rs.getString("ProjectSummary") %></td>
                <td>
                    <a href="ProjectRemove?ProjectName=<%= rs.getString("ProjectName") %>">
                    <button>Remove Project</button>
                    </a>
                </td>
                </tr>
                <%

                       }
                   }

                %>
            </table>
        
    </body>
</html>
