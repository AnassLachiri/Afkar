<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="static_css?file=css/create_story.css">
    <title>Update Story</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>


<div class="my_container">
    <% if(request.getAttribute("story_update_error") != null) {%>
    <h3><%= request.getAttribute("story_update_error") %></h3>
    <% } %>

    <form action="story_update" method="post">
        <div class="box">
            <label>Titre: </label>
            <input type="text" name="title" class="input-text" value="<%= request.getAttribute("title") %>">
            <label>Sous-titre: </label>
            <input type="text" name="subtitle" class="input-text" value="<%= request.getAttribute("subtitle") %>">
            <label>Contenu: </label>
            <textarea name="content" placeholder="Ecrivez votre story" class="content"><%= request.getAttribute("content") %></textarea>
            <label>Tags: </label>
            <input type="text" name="keywords" class="input-text" value="<%= request.getAttribute("keywords") %>">
            <input type="hidden" name="uuid" value="<%= request.getAttribute("uuid") %>">
            <button type="submit" name="submit" class="submit btn btn-dark">Update</button>
        </div>
    </form>
</div>


</body>
</html>
