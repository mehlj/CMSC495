<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
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
                </div>
                <nav>
                    <ul>
                        <li><a href="index.html">Home</a></li>
                        <li><a href="Projects.jsp">Projects</a></li>
                        <li><a href="Projects.html">Projects</a></li>
                        <li><a href="Tasks.html">Tasks</a></li>
                        <li><a href="UserSetup.jsp">User Setup</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <form action="${pageContext.request.contextPath}/UserServlet" method="post">
            <fieldset>
                <legend>User Information:</legend>
                User Name: <input type ="text" name="user_name">
                User Role: <select name="user_role"> 
                    <option value="Developer">Developer</option>
                    <option value="Project Manager">Project Manager</option>
                    <option value="Team Lead">Team Lead</option>
                </select><br>        
            </fieldset>
            <input type="submit" value="Add User">
        </form>

        <table border=1 cellpadding=5>
            <tr>
                <th>User Name</th>
                <th>User Role</th>
            </tr>
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
            </tr>
            <%

                    }
                }

            %>
        </table>

    </body>
</html>