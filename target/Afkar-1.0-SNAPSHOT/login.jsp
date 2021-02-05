
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        /* Bordered form */
        form {
            border: 3px solid #f1f1f1;
        }

        /* Full-width inputs */
        input{
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        /* Set a style for all buttons */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        /* Add a hover effect for buttons */
        button:hover {
            opacity: 0.8;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
        }


    </style>
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
            <label ><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>

            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">Login</button>
        </div>

    </form>
</body>
</html>
