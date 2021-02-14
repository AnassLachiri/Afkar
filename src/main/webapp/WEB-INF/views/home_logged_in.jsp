<%@ page import="com.afkar.models.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        <%@ include file="../../static_files/css/feed.css"%>
    </style>
    <title>Afkar</title>
</head>
<body>

<%@ include file="navbar.jsp"%>

<h1 class="products-title text-center">Bienvenue</h1>

<div class="container">
<div class="stories">
    <% if(((ArrayList<Story>) request.getAttribute("stories")).size()>0) { %>
        <% for(Story story: (ArrayList<Story>) request.getAttribute("stories")) {%>
            <div class="story">
                <hr><div class="row">
                <div class="col-md-2 story-img">
                    <img src="levi.jpg" class="story-img" alt="photo">
                </div>
                <div class="col-md-9">
                <h3><%= story.getTitle() %></h3>
                <h5><%= story.getSubtitle() %></h5>
                </div>
                <a lass="link" href="story?uuid=<%= story.getUuid() %>">Read more</a>
                <div class="col-md-1">
                </div>
            </div>
        <%}%>
    <% }else {%>
        <div>
            <p>No stories found!!</p>
        </div>
    <% } %>
</div>
</div>
</div>

<div class="pagination">
    <a href="?page=<%= (((long)request.getAttribute("page")) -1) %>">&#60;&#60; Last Page </a> - <span> <%= request.getAttribute("page") %> </span> - <a href="?page=<%= (1+(long)request.getAttribute("page")) %>"> Next Page &#62;&#62;</a>
</div>

</body>
</html>
