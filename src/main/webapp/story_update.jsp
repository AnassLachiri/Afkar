<%--
  Created by IntelliJ IDEA.
  User: solox
  Date: 09/02/2021
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update Story</h1>

<% if(request.getAttribute("story_update_error") != null) {%>
<h3><%= request.getAttribute("story_update_error") %></h3>
<% } %>

<form action="story_update" method="post">
    <label>Title : </label><br>
    <input type="text" name="title" value="<%= request.getAttribute("title") %>"><br>
    <label>Subtitle : </label><br>
    <input type="text" name="subtitle" value="<%= request.getAttribute("subtitle") %>"><br>
    <label>Keywords (separated by colons) : </label><br>
    <input type="text" name="keywords" value="<%= request.getAttribute("keywords") %>"><br>
    <label>Story Content : </label><br>
    <textarea name="content"><%= request.getAttribute("content") %></textarea><br>
    <input type="hidden" name="uuid" value="<%= request.getAttribute("uuid") %>">
    <button type="submit">Update Story</button>
</form>
</body>
</html>
