
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <title>Rejoindre</title>
    <style>
        <%@include file="../../static_files/css/register.css"%>
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
    <% if(session.getAttribute("register_error")!=null){ %>
        <h3><%= session.getAttribute("register_error") %></h3>
    <% } %>
    <br>
    <form action="register" method="post" enctype="multipart/form-data">
        <div class="container">
            <div class="box">
            <h2>Rejoinez-nous</h2>
            <label >Nom de l'utilisateur: </label>
            <input type="text"  name="username" required>

            <label >Photo de profile: </label>
            <input type="file" name="image" accept="image/*" required>

            <label >Adresse Email: </label>
            <input type="email" name="email" required>

            <label >Mot de passe: </label>
            <input type="password"  name="password1" required>

            <label >Confirmer mot de passe: </label>
            <input type="password" name="password2" required>

            <button type="submit" class="submit" >Continuer</button>
            </div>
        </div>


    </form>
</body>
</html>
