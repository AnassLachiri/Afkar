<%@ page import="com.afkar.models.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.afkar.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="static_css?file=css/profile.css">
    <title>Profile</title>
</head>
<body>

<jsp:include page="navbar.jsp"/>


<div class="container space">

    <div class="card text-center card-style">
        <div class="card-body">
            <img class="profile-photo" src="profile_image?image=<%= ((User)request.getAttribute("profile")).getImage() %>" alt="photo">
            <div>
                <h1><%= ((User)request.getAttribute("profile")).getUsername() %></h1>
            </div>

            <div class="story-section">
                <h3 class="text-left">Les stories de l'utilisateur</h3><br>
                <div class="row no-gutters">

                    <% if(((ArrayList<Story>) request.getAttribute("stories")).size()>0) { %>
                    <% for(Story story: (ArrayList<Story>) request.getAttribute("stories")) {%>
                    <div class="col-md-3">
                        <div class="card">
                            <img class="card-img-top" src="story_image?image=<%= story.getImage() %>" alt="Card image">
                            <div class="card-body" style="margin: auto;">
                                <h4 class="card-title text-center"><%= story.getTitle() %></h4>
                                <h5><%= story.getSubtitle() %></h5>
                                <a href="story?uuid=<%= story.getUuid() %>" class="btn btn-dark">Voir Story</a>
                            </div>
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

            <br>
            <div class="pagination">
                <a href="profile?username=<%= request.getAttribute("username") %>&page=<%= (((long)request.getAttribute("page")) -1) %>">&#60;&#60; Last Page </a> - <span> <%= request.getAttribute("page") %> </span> - <a href="profile?username=<%= request.getAttribute("username") %>&page=<%= (1+(long)request.getAttribute("page")) %>"> Next Page &#62;&#62;</a>
            </div>


        </div>


    </div>
</div>


</body>
</html>
