
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <title>S'inscrire</title>
    <style>
        <%@include file="../../static_files/css/login.css"%>
    </style>

</head>
<body>
    <nav class="navbar">
        <div class="brand">
            <img src="static_image?image=logo.png" alt="logo">
            <div class="brand-title">Afkar</div>
        </div>
    </nav>
    <br>
    <% if(session.getAttribute("login_error")!=null){ %>
    <h3><%= session.getAttribute("login_error") %></h3>
    <% } %>
    <br>
    <form action="login" method="post">
        <div class="container">
            <div class="box">
                <h2>S'inscrire</h2>
                <label>Nom de l'utilisateur: </label>
                <input type="text" name="username">
                <label>Mot de passe: </label>
                <input type="password" name="password">
                <input type="submit" name="submit" class="submit" value="Continuer">
            </div>
        </div>

    </form>
</body>
</html>
