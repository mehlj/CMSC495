<%-- 
    Document   : Tasks
    Created on : Apr 18, 2018, 6:50:20 PM
    Author     : jmehl
--%>

<%@page import="JJIGSAWED.User"%>
<%@page import="JJIGSAWED.Task"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="JJIGSAWED.Project"%>
<%@page import="JJIGSAWED.Project"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% int projectID = Integer.parseInt(request.getParameter("ProjectID")); %>
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
                    <h1>Task Management</h1>
                    <h2><p style="color:red;">OPERATION ERROR</p></h2>
                </div>
                <nav>
                    <ul>
                        <li><a href="index.html">Home</a></li>
                        <li><a href="Projects.jsp">Projects</a></li>
                        <li><a href="UserSetup.jsp">User Setup</a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <form action="${pageContext.request.contextPath}/TaskCreate?ProjectID=<%= projectID %>" method="post">
            <fieldset>
                <legend>Task Information:</legend>
                *Task Name: <input type ="text" name="task_name">
                *Task Summary: <textarea name ="task_summary" 
                                         rows="10" cols="30"></textarea><br>
                Date Created: <input type="date" name="date_created"> <br>
                Date Ended: <input type="date" name="date_ended"> <br>

                Priority: <select name="task_priority"> 
                    <option value="3">Low</option>
                    <option value="2">Normal</option>
                    <option value="1">High</option>
                </select><br>
                User Assignment: <select name="user_assignment">
                    
                    <%
                        ArrayList<Integer> userList = User.getUserIDs();


                        for (int i = 0; i < userList.size(); i++) 
                        {
                            ResultSet rs = User.getUser(userList.get(i));

                            // convert resultset to array
                        while (rs.next()) 
                        {
                %>
                <option value="<%= rs.getString("UserID") %>"><%= rs.getString("Name") %></option>
                <%
                        }
                }
                %>
                </select><br>
                

                Schedule Filter: <select name="schedule_filter"> 
                    <option value="0">All</option>
                    <option value="7">7 Days</option>
                    <option value="14">14 Days</option>
                    <option value="30">30 Days</option>
                </select><br><br>
            </fieldset>
            <input type="submit" value="Search">
            <input type="submit" value="Add Task">
        </form>
        <table border=1 cellpadding=5>
                <tr>
                  <th>Name</th>
                  <th>Summary</th>
                  <th>Priority</th>
                  <th>Project ID</th>
                  <th>Remove</th>
                </tr>
                <%

                   ArrayList<Integer> list = Task.getTaskIDs(projectID);


                   for (int i = 0; i < list.size(); i++) 
                   {
                       ResultSet rs = Task.loadTasks(list.get(i));

                       // convert resultset to array
                       while (rs.next()) 
                       {
                %>
                <tr>
                <td><%= rs.getString("TaskName") %></td>
                <td><%= rs.getString("TaskSummary") %></td>
                <td><%= rs.getString("TaskPriority") %></td>
                <td><%= projectID %></td>
                <td>
                    <a href="TaskRemove?TaskName=<%= rs.getString("TaskName") %>&ProjectID=<%= projectID %>">
                    <button>Remove Task</button>
                    </a>
                </td>
                </tr>
                <%

                       }
                   }

                %>
        </table>
        <footer>
            <p>
                JJIGSAWED Software Company, Copyright 2018.
            </p>
        </footer>
    </body>
</html>
