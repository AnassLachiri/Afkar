<%@ page import="com.afkar.models.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="static_css?file=css/home_logged_in.css">
    <title>Saved Stories</title>
</head>
<body>

<jsp:include page="navbar.jsp"/>
<br>
<h2 style="text-align: center; color: gray;">Les Stories Sauvegardés</h2>

<div class="container">

    <hr>

    <% if(((ArrayList<Story>) request.getAttribute("stories")).size()>0) { %>
    <% for(Story story: (ArrayList<Story>) request.getAttribute("stories")) {%>
    <div class="row">
        <div class="col-md-2 story-img">
            <img src="story_image?image=<%= story.getImage() %>" class="story-img" alt="photo">
        </div>
        <div class="col-md-9">
            <h3><%= story.getTitle() %></h3>
            <h4><%= story.getSubtitle() %></h4>
            <a class="link" href="story?uuid=<%= story.getUuid() %>">Lire la suite</a>
        </div>
        <div class="col-md-1">
        </div>
    </div>

    <hr>

    <%}%>
    <% }else {%>
    <div>
        <p>No stories found!!</p>
    </div>
    <hr>
    <% } %>


</div>

<div class="pagination d-flex justify-content-center align-items-center">
    <a href="?page=<%= (((long)request.getAttribute("page")) -1) %>">&#60;&#60; Last Page </a> - <span> <%= request.getAttribute("page") %> </span> - <a href="?page=<%= (1+(long)request.getAttribute("page")) %>"> Next Page &#62;&#62;</a>
</div>

</body>
</html>
