
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
    <link href="static_css?file=css/register.css" rel="stylesheet">
    <title>Rejoindre</title>
</head>
<body>


<!-- this is another nav which dosen't contain any links-->


<nav class="navbar">
    <div class="brand">
        <img src="static_image?image=logo.png" alt="logo">
        <div class="brand-title">Afkar</div>
    </div>
</nav>
<div class="my_container">
    <% if(session.getAttribute("register_error")!=null){ %>
    <h3 class="error"><%= session.getAttribute("register_error") %></h3>
    <% } %>
    <form action="register" method="post" enctype="multipart/form-data">
        <div class="box">
          <h3>Rejoingnez nous</h3>
            <label><i class="far fa-user"></i>  Nom de l'utilisateur: </label>
            <input type="text" name="username" required>

            <label><i class="far fa-id-card"></i>  Photo de profile: </label>
            <input  class="input-image" type="file" name="image" accept="image/*" required>

            <label><i class="far fa-envelope"></i>  Adresse Email: </label>
            <input type="text" name="email" required>

            <label><i class="fas fa-lock"></i>  Mot de passe: </label>
            <input type="password" name="password1" required>

            <label><i class="fas fa-lock"></i>  Confirmer mot de passe: </label>
            <input type="password" name="password2" required>

            <input type="submit" name="submit" class="submit btn btn-dark" value="Continuer">
        </div>
    </form>
</div>

</body>
</html>
