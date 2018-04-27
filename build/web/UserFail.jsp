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
<%@page import="JJIGSAWED.User"%>
<html>

<head>
  <title>User Setup Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
  <!-- Header -->
  <div class="jumbotron">
    <h1 align='center'>User Setup</h1>
    <div class="alert alert-danger" role="alert" align="center">Something Went Wrong, Please Try Again!</div>
    <nav class="navbar navbar-right">
      <ul class="nav navbar-nav nav-pills nav-fill">
        <li><a href="index.html">Home</a></li>
        <li><a href="Projects.jsp">Projects</a></li>
        <li class="active"><a href="UserSetup.jsp">User Setup</a></li>
        <li><a href="#">Sign Out</a></li>
      </ul>
    </nav>
  </div>

  <!-- Could Use Alerts Success/Error after Input:  https://getbootstrap.com/docs/3.3/components/#alerts
  <div class="alert alert-success" role="alert">You Successfully Saved Your Information!</div>
  <div class="alert alert-danger" role="alert">Something Went Wrong, Please Try Again!</div> -->

  <!-- Input Form -->
  <div class="container" id="userInput">
    <div class="panel panel-default">
      <!-- Default panel contents -->
      <div class="panel-heading">Create Your User Account Here:</div>
      <!-- User Name -->
      <form class="form-horizontal" action="${pageContext.request.contextPath}/UserServlet"  method="POST">
        <div class="form-group.required" style="margin:5px">
          <label class=" control-label mt-2 col-sm-2">* User Name:</label>
          <div class="col-sm-4">
              <input class="form-control" type="text" name="user_name" id="user_name" placeholder="JWillis">
          </div>
        </div>
      
      <!-- <div class="col-sm-4">
        <div class="form-group.required">
          <label class=" control-label">*Last Name</label>
          <input class="form-control" type="text" id="lastName" placeholder="Willis">
        </div>
      </div>
      <div class="col-sm-4">
        <div class="form-group.required">
          <label class=" control-label">User Name</label>
          <input class="form-control" type="text" id="userName" placeholder="JWillis">
        </div>
      </div> -->
      <!-- </form> -->
      <!-- <br /> -->
      <!-- <form class="form-inline"> -->
      <!-- </div> -->
      <!-- In case we want to switch back to userName -->
      <!-- <label class="col-sm-2 control-label">*User Name</label>
          <div class="col-xs-4">
            <input class="form-control" id="personsName">
          </div> -->
      <!-- <div class="col-sm-4">
        <div class="form-group">
          <label class="control-label">Role</label>
          <input class="form-control" type="text" id="role">
        </div>
      </div> -->
      <!-- User Role -->
      <!--<form class="form-horizontal" action="">-->
        <div class="form-group.required" style="margin:5px">
          <label class="control-label col-sm-2">User Role:</label>
          <div class="col-sm-4">
            <select class="form-control" name="user_role" id="user_role">
            <option>Developer</option>
            <option>Project Manager</option>
            <option>Team Lead</option>
            </select>
          </div>
        </div>
      

      <!-- Search | Save buttons -->
      <!--<form class="form-horizontal" action="$//{pageContext.request.contextPath}/UserServlet" method="post">-->
        <div class="form-group">
          <div class="form-group.required" style="margin-top: 50px; margin-left:16px;">
            <label class="control-label col-sm-2">*Search | Save:</label>
            <div class="col-sm-4">
             <button type="button" class="btn btn-info">Search</button>
              <button type="Submit" class="btn btn-success">Save</button>
            </div>
          </div>
        </div>
      

      <!-- <div class="col-sm-4">
        <label>Save or Search Your Entry</label><br />
        <div class="btn-toolbar">
          <button type="button" id="userSearch" class="btn btn-sm btn-info">Search</button>
          <button type="button" id="userSave" class="btn btn-sm btn-success">Save</button>
        </div>
      </div> -->
      </form>
    </div>
  </div>

  <br />
  <!-- <hr style="width:33%;" /> -->
  <br />
  <!-- </div> -->




  <div class="container">
    <table class="table table-hover table-responsive-md table-fixed">
      <thead>
        <tr>
          <th>Name</th>
          <th>Role</th>
          <th>Remove</th>
          <th>Inactivate</th>
        </tr>
      </thead>
      <tbody>
            <%
                ArrayList<Integer> list = User.getUserIDs();

                for (int i = 0; i < list.size(); i++) {
                    ResultSet rs = User.getUser(list.get(i));

                    // convert resultset to array
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("Name")%></td>
                <td><%= rs.getString("Role")%></td>
            <td>
                    <a href="UserRemove?user_name=<%= rs.getString("name")%>">
                        <button>Remove User</button>
                    </a>
                </td>
                <td>
                    <a href="UserInactive?user_name=<%= rs.getString("name")%>">
                        <button>Inactivate User</button>
                    </a>
                </td>    
        </tr>
      </tbody>
      <%

                    }
                }

            %>
    </table>
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

  <!-- Bootstrap JS & jQuery -->
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>

</html>