<%@ page import="com.afkar.models.Comment" %>
<%@ page import="com.afkar.models.Reply" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    <style>
        <%@include file="../../static_files/css/story.css"%>
    </style>
    <title>Story</title>
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <div class="story-container">
        <section class="panel">

            <div class="panel-subcontainer">
                <div class="likes">
                    <i onclick="redLikeButton()" id="like-btn" class="far fa-heart fa-lg"></i>
                    <span id="like-counter">69</span>
                </div>

                <div class="comments">
                    <i onclick="show()" class="far fa-comment fa-lg"></i>
                    <span>1</span>
                </div>

                <i id="save-btn" onclick="save()" class="far fa-bookmark fa-lg bookmark"></i>

            </div>
        </section>

        <section class="story">
            <h1 class="title"><%= request.getAttribute("title") %></h1>
            <h5><%= request.getAttribute("subtitle") %></h5>
            <div class="story-writer-info">
                <img class="story-writer-photo" src="static_image?image=levi.png" alt="owner">
                <div>
                    <div class="infos">
                        <span class="story-writer">Ygor Henrique</span>
                        <button class="tiny-button" onclick="follow()">Follow</button>
                    </div>
                    <span class="date">Dec 31, 2020</span>
                </div>
            </div>
            <div class="story-content">
                <img class="story-image" src="static_image?image=story_img.jpeg" alt="story-image" >
                <p>
                    <%= request.getAttribute("content") %>
                </p>
            </div>
        </section>


   <!-- <% if( (long)request.getAttribute("user_id") == (long)request.getAttribute("login_id")  ) {%>
        <a href="story_delete?uuid=<%= request.getAttribute("uuid") %>">Delete</a>
        <a href="story_update?uuid=<%= request.getAttribute("uuid") %>">Edit</a>
    <%}%>

    <form method="post" action="comment_create">
        <input type="text" name="content" placeholder="Comment...">
        <input type="hidden" name="story_uuid" value="<%= request.getAttribute("uuid") %>">
        <button type="submit">Submit</button>
    </form> -->

    <div class="comment-sidebar">
        <h3>Commentaires</h3>
        <form>
            <textarea placeholder="Qu'est ce que vous pensez ?"></textarea>
            <input type="submit" value="Commenter">
        </form>

    <% for(Comment comment : (ArrayList<Comment>) request.getAttribute("comments")){%>
        <div class="commenter-info">
            <img class="story-writer-photo" src="zeke.png" alt="owner">
            <div>
                <div class="infos">
                    <span class="story-writer">Ygor Henrique</span>
                </div>
                <span class="date">Dec 31, 2020</span>
            </div>
        </div>
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
        
    <script defer>
        <%@ include file="../../static_files/js/story.js"%>
    </script>    
    </div>
</body>
</html>
