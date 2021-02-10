<%--
  Created by IntelliJ IDEA.
  User: solox
  Date: 10/02/2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Image</title>
</head>
<body>

<form method="post" action="upload_image" enctype="multipart/form-data">
    <input type="file" name="image" accept="image/*"/> <br/>
    <input type="submit" value="Upload" /> <br/>
</form>
<% if( request.getAttribute("image") != null && !request.getAttribute("image").equals("")) { %>
    <img src="download_image?image=<%= request.getAttribute("image") %>" />
<% } %>
</body>
</html>
