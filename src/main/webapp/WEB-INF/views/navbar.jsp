<%@ page import="com.afkar.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    <title>Document</title>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-light shadow-sm" style="background-color:#CEF6F2; height:60px;">

    <a class="navbar-brand" href="#">
        <img style=" width: 45px;height: 45px;margin-left: 30px;" src="static_image?image=logo.png" alt="Afkar">
        <span>Afkar</span>
    </a>

    <ul class="navbar-nav ml-auto ">
        <form class="form-inline search-bar" action="/search" method="GET">
            <input class="form-control" type="search" placeholder="Search" aria-label="Search" name="search_text">
            <button class="btn btn-outline-info" type="submit">
                <svg class="bi bi-search" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 011.415 0l3.85 3.85a1 1 0 01-1.414 1.415l-3.85-3.85a1 1 0 010-1.415z" clip-rule="evenodd"/>
                    <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 100-11 5.5 5.5 0 000 11zM13 6.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0z" clip-rule="evenodd"/>
                </svg>
            </button>
        </form>


        <li class="nav-item dropdown">
            <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                <% if( session.getAttribute("user") != null ) {%>
                <img src="profile_image?image=<%= ((User) session.getAttribute("user")).getImage() %>" style=" width: 45px; height: 45px; border-radius: 50%; cursor: pointer; margin-left: 30px; " alt="your profile"/><span class="caret"></span>
                <% } %>
            </a>

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <% if( session.getAttribute("user") != null ) {%>
                <a href="profile?username=<%= ((User)session.getAttribute("user")).getUsername() %>" class="dropdown-item">Mon Profile</a>
                <% } %>
                <a href="saved_stories" class="dropdown-item">Mes Stories</a>
                <a href="story_create" class="dropdown-item">Ecrire une Story</a>
                <a href="logout" class="dropdown-item">Déconnecter</a>
            </div>
        </li>

    </ul>

</nav>

</body>
</html>
