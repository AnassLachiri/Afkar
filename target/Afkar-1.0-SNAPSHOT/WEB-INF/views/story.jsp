<%@ page import="com.afkar.models.Comment" %>
<%@ page import="com.afkar.models.Reply" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Story</h1>

    <h3><%= request.getAttribute("title") %></h3>
    <h5><%= request.getAttribute("subtitle") %></h5>
    <p><%= request.getAttribute("content") %></p>

    <% if( (long)request.getAttribute("user_id") == (long)request.getAttribute("login_id")  ) {%>
        <a href="story_delete?uuid=<%= request.getAttribute("uuid") %>">Delete</a>
        <a href="story_update?uuid=<%= request.getAttribute("uuid") %>">Edit</a>
    <%}%>
    <form action="saved_stories" method="post">
        <input type="hidden" name="uuid" value="<%= request.getAttribute("uuid") %>" />
        <button type="submit">Save Story</button>
    </form>

    <form method="post" action="comment_create">
        <input type="text" name="content" placeholder="Comment...">
        <input type="hidden" name="story_uuid" value="<%= request.getAttribute("uuid") %>">
        <button type="submit">Submit</button>
    </form>

    <div class="comments" style="border-left: 15px solid rgba(0, 0, 255, 0.5);padding-left: 20px;">
    <% for(Comment comment : (ArrayList<Comment>) request.getAttribute("comments")){%>
        <div class="comment">
            <p><%= comment.getContent() %></p>
            <% if(comment.getUser_id() == (long) request.getAttribute("login_id")) {%>
                <a href="comment_delete?comment_id=<%= comment.getId() %>&story_uuid=<%= request.getAttribute("uuid") %>">Delete</a>
            <%}%>
        </div>
        <form method="post" action="reply_create">
            <input type="text" name="content" placeholder="Reply...">
            <input type="hidden" name="comment_id" value="<%= comment.getId() %>">
            <input type="hidden" name="story_uuid" value="<%= request.getAttribute("uuid") %>">
            <button type="submit">Submit</button>
        </form>
        <% if(((HashMap<Long, ArrayList<Reply>>) request.getAttribute("replies")).get(comment.getId()).size() > 0){ %>
            <div class="replies" style="border-left: 10px solid rgba(255,0,0,0.5);padding-left: 15px;">

            <% for(Reply reply : ((HashMap<Long, ArrayList<Reply>>) request.getAttribute("replies")).get(comment.getId())){%>
                <div class="reply" style="border-left: 5px solid rgba(0, 255, 0, 0.5);padding-left: 10px;">
                    <p><%= reply.getContent() %></p>
                    <% if(reply.getUser_id() == (long) request.getAttribute("login_id")) {%>
                    <a href="reply_delete?reply_id=<%= reply.getId() %>&story_uuid=<%= request.getAttribute("uuid") %>">Delete</a>
                    <%}%>
                </div><br>
            <% } %>
            </div>
        <% } %>
        <br>
    <%}%>
    </div>

</body>
</html>
