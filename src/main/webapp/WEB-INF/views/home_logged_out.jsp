<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link href="static_css?file=css/home_logged_out.css" rel="stylesheet">
    <title>Afkar</title>
</head>
<body>

<!-- this navbar is different than the ordinary one-->

<nav class="navbar">
    <div class="brand">
        <img src="static_image?image=logo.png" alt="logo">
        <div class="brand-title">Afkar</div>
    </div>
    <div class="nav-links">
        <ul>
            <li class="login"><a href="login">Se connecter</a></li>
            <li><button><a href="register" class="join">Rejoindre</a></button></li>
        </ul>
    </div>
</nav>

<div class="my_container">

    <div>
        <p class="main-paragraph">Afkar est une platform où vous pouvez s’exprimer et écrire sur votre passions!</p>
        <button class="join2" onclick="location.href='register'">Rejoindre</button>
    </div>

    <img src="static_image?image=idea.png" class="image" >

</div>

</body>
</html>