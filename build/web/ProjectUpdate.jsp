<!DOCTYPE html>
<!--
HTML written by: Dave Thatcher & Jason Willis  
Class: CMSC 495 UMUC
Date: 4/13/2018
-->
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JJIGSAWED.Project"%>
<% String projectName = request.getParameter("project_name"); %>
<% String dueDate = request.getParameter("due_date"); %>
<% int projectPriority = Integer.parseInt(request.getParameter("project_priority")); %>
<% String projectSummary = request.getParameter("project_summary"); %>

<html>

<head>
  <title>Projects Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="style.css">
  <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
  <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
</head>

<body>
  <!-- Header -->
  <div class="jumbotron">
    <h1 align='center'>Manage Projects</h1>
    <nav class="navbar navbar-right">
      <ul class="nav navbar-nav nav-pills nav-fill">
        <li class="nav-item"><a class="nav-link active" href="index.html">Home</a></li>
        <li class="nav-item active"><a class="nav-link active" href="Projects.jsp">Projects</a></li>
        <li class="nav-item"><a class="nav-link active" href="UserSetup.jsp">UserSetup</a></li>
      </ul>
    </nav>
  </div>

  <!-- Could Use Alerts Success/Error after Save | Search:  https://getbootstrap.com/docs/3.3/components/#alerts
  <div class="alert alert-success" role="alert">You Successfully Saved Your Information!</div>
  <div class="alert alert-danger" role="alert">Something Went Wrong, Please Try Again!</div> -->

  <!-- Input / Search Form -->
  <!-- Top Row -->
  <div class="container" id="projectInput">
    <div class="panel panel-default">
      <!-- Default panel contents -->
      <div class="panel-heading">Project Informaton to Input or Search:</div>
      <!-- <div class="col-xs-12 col-sm-12"> -->
      <!-- The Whole Form - Both Rows -->
      <!-- <form> -->
      <!-- Project Name -->
      <!-- <div class="col-sm-3"> -->
      <form class="form-horizontal" action="${pageContext.request.contextPath}/ProjectEdit?OriginalProjectName=<%= projectName %>&OriginalProjectPriority=<%= projectPriority %>" method="post">
        <div class="form-group.required" style="margin:5px">
          <label class="control-label mt-2 col-sm-2">*Project Name:</label>
          <div class="col-sm-4">
            <input class="form-control" type="text" name='project_name' id="projectName" placeholder="" value="<%= projectName %>">
          </div>
        </div>
      
      <!-- </div> -->

      <!-- Due Date -->
      <!-- <div class="col-sm-3"> -->
      
        <div class="form-group.required" style="margin:5px">
          <label class="control-label col-sm-2">*Due Date:</label>
          <div class="col-sm-4">
            <input class="form-control" type="date" name='due_date' id="projectDueDate" value="<%= dueDate %>">
          </div>
        </div>
      
     <!-- <div class="form-group.required" style="margin:5px">
          <label class="control-label col-sm-2">*Project Assigned To</label>
          <div class="col-sm-4">
            <input class="form-control" type="text" name='project_assigned_to' id="projectDueDate">
          </div>
        </div> -->

      <!-- </div> -->

      <!-- Priority -->
      <!-- <div class="col-sm-3"> -->
  
        <div class="form-group.required" style="margin-top:60px">
          <label class="control-label col-sm-2">*Priority:</label>
          <div class="col-sm-4">
            <select class="form-control" name="project_priority" id="selectRole">
<% 
            if (projectPriority == 3) {
            %>
            <option selected value="3">Low</option>
            <%
            }
            else {
            %>
            <option value="3">Low</option>
            <%
            }
            %>
            <% 
            if (projectPriority == 2) {
            %>
            <option selected value="2">Medium</option>
            <%
            }
            else {
            %>
            <option value="2">Medium</option>
            <%
            }
            %>
            <% 
            if (projectPriority == 1) {
            %>
            <option selected value="1">High</option>
            <%
            }
            else {
            %>
            <option value="1">High</option>
            <%
            }
            %>
            </select>
          </div>
        </div>
    

      <!-- Search | Save buttons -->
    
        <div class="form-group.required" style="margin-top:60px">
          <label class="control-label col-sm-2">*Search | Save:</label>
          <div class="col-sm-4">
            <button type="button" class="btn btn-info">Search</button>
            <button type="submit" class="btn btn-success">Save</button>
          </div>
        </div>
  

      <!-- Project Summary -->
      
        <div class="form-group">
          <div class="form-group.required" style="margin-top:60px; margin-left:10px">
            <label class="control-label col-sm-2">*Project Summary:</label>
            <div class="col-sm-8">
              <textarea class="form-control" id="projectSummary" name="project_summary" rows="5" placeholder="Describe your project here..."> <%= projectSummary %></textarea>
            </div>
          </div>
        </div>  
    
      <!-- Whole Form -->
       </form>
    </div>
  </div>

  <br />
  <br />

  <!-- Table -->
  <div class="container">
    <div class="panel panel-default">
      <!-- Table panel contents -->
      <div class="panel-heading">Your Projects:</div>
      <!-- <div class="container"> -->
      <table class="table table-hover table-responsive-md table-fixed">
        <thead>
          <tr>
            <th>Project Name</th>
            <th>Due Date</th>
            <th>Priority</th>
            <th>Project Summary</th>
            <th>Select</th>
            <th>Edit</th>
            <th>Remove</th>
          </tr>
        </thead>
        <tbody>
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
                <td><%= rs.getString("ProjectDue") %></td>
                <td><%= rs.getInt("ProjectPriority") %></td>
                <td><%= rs.getString("ProjectSummary") %></td>
                <td>
                    <a href="ProjectSelect?ProjectName=<%= rs.getString("ProjectName") %>">
                    <button>Select Project</button>
                    </a>
                </td>   
                <td>
                    <a href="ProjectUpdate.jsp?project_name=<%= rs.getString("ProjectName") %>&due_date=<%= rs.getString("ProjectDue") %>&project_priority=<%= rs.getInt("ProjectPriority")%>&project_summary=<%= rs.getString("ProjectSummary") %>">
                    <button>Edit Project</button>
                    </a>
                </td>
                <td>
                    <a href="ProjectRemove?ProjectName=<%= rs.getString("ProjectName") %>">
                    <button>Remove Project</button>
                    </a>
                </td>

        </tbody>
        <%

                       }
                   }

                %>
      </table>
      <!-- Close Panel for Table -->
    </div>
    <!-- Close Panel Container -->
  </div>


  <!-- Pagination -->
  <nav aria-label="Page navigation" align='center'>
    <ul class="pagination">
      <li>
        <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
      </li>
      <li><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li>
        <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
      </li>
    </ul>
  </nav>


  <!-- Footer -->
  <div class="footer navbar-fixed-bottom well">
    <p class="text-center text-muted">JJIGSAWED Software Company, Copyright 2018.</p>
  </div>

  <!-- Bootstrap 4.0 -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" crossorigin="anonymous"></script> -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>

</html>