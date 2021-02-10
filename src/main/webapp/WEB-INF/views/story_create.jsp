<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create New Story</h1>

<% if(request.getAttribute("story_create_error") != null) {%>
<h3><%= request.getAttribute("story_create_error") %></h3>
<% } %>

<form action="story_create" method="post">
    <label>Title : </label><br>
    <input type="text" name="title"><br>
    <label>Subtitle : </label><br>
    <input type="text" name="subtitle"><br>
    <label>Keywords (separated by colons) : </label><br>
    <input type="text" name="keywords"><br>
    <label>Story Content : </label><br>
    <textarea name="content"></textarea><br>
    <button type="submit">Create Story</button>
</form>
</body>
</html>
