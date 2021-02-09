
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style>
        <%@ include file="css/style.css"%>
    </style>

</head>
<body>

    <h1>Register</h1>
    <br>
    <% if(session.getAttribute("register_error")!=null){ %>
        <h3><%= session.getAttribute("register_error") %></h3>
    <% } %>
    <br>
    <form action="register" method="post">
        <div class="container">
            <label ><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>

            <label ><b>Email</b></label>
            <input type="email" placeholder="Enter Password" name="email" required>

            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password1" required>

            <label ><b>Reenter Password</b></label>
            <input type="password" placeholder="Reenter Password" name="password2" required>

            <button type="submit">Register</button>
        </div>

    </form>
</body>
</html>
