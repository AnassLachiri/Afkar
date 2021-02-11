
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="static_file?file=css/login.css">

</head>
<body>
    <h1>Login</h1>
    <br>
    <% if(session.getAttribute("login_error")!=null){ %>
    <h3><%= session.getAttribute("login_error") %></h3>
    <% } %>
    <br>
    <form action="login" method="post">
        <div class="container">
            <label><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>

            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">Login</button>
        </div>

    </form>
</body>
</html>
