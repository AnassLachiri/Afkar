<%@ page import="com.afkar.models.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Saved Stories</title>
</head>
<body>
<h1>Saved Stories</h1>


<div class="stories">
    <% if(((ArrayList<Story>) request.getAttribute("stories")).size()>0) { %>
    <% for(Story story: (ArrayList<Story>) request.getAttribute("stories")) {%>
    <div class="story">
        <h3><%= story.getTitle() %></h3>
        <h5><%= story.getSubtitle() %></h5>
        <a href="story?uuid=<%= story.getUuid() %>">Read the story</a>
    </div>
    <%}%>
    <% }else {%>
    <div>
        <p>No stories found!!</p>
    </div>
    <% } %>
</div>
<div class="pagination">
    <a href="?page=<%= (((long)request.getAttribute("page")) -1) %>">&#60;&#60; Last Page </a> - <span> <%= request.getAttribute("page") %> </span> - <a href="?page=<%= (1+(long)request.getAttribute("page")) %>"> Next Page &#62;&#62;</a>
</div>

</body>
</html>
