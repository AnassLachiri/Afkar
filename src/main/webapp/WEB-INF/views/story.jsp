<%@ page import="com.afkar.models.Comment" %>
<%@ page import="com.afkar.models.Reply" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.afkar.models.Story" %>
<%@ page import="com.afkar.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="static_css?file=css/story.css">
    <script src="static_js?file=js/story.js" defer></script>
    <title>Story</title>
</head>
<body>

<jsp:include page="navbar.jsp"/>




    <div class="story-container">
        <section class="panel">

            <div class="panel-subcontainer">
                <div class="likes">
                    <i onclick="redLikeButton()" id="like-btn" class="far fa-heart fa-lg"></i>
                    <span id="like-counter"><%= ((Story)request.getAttribute("story")).getTotal_likes() %></span>
                </div>

                <div class="comments">
                    <i onclick="show()" class="far fa-comment fa-lg"></i>
                </div>

                <i id="save-btn" onclick="save()" class="far fa-bookmark fa-lg bookmark"></i>

            </div>
        </section>

        <section class="story">
            <h1 class="title"><%= ((Story)request.getAttribute("story")).getTitle() %></h1>
            <div class="story-writer-info">
                <img class="story-writer-photo" src="profile_image?image=<%= ((User)request.getAttribute("author")).getImage() %>" alt="owner">
                <div>
                    <div class="infos">
                        <span class="story-writer"><%= ((User)request.getAttribute("author")).getUsername() %></span>
                        <button class="tiny-button" onclick="follow()">Follow</button>
                    </div>
                    <span class="date"><%= ((Story)request.getAttribute("story")).getCreated_at() %></span>
                </div>
            </div>


            <% if( ((Story)request.getAttribute("story")).getUser_id() == ((User)session.getAttribute("user")).getId()  ) {%>
            <a href="story_delete?uuid=<%= ((Story)request.getAttribute("story")).getUuid() %>">Delete</a>
            <a href="story_update?uuid=<%= ((Story)request.getAttribute("story")).getUuid() %>">Edit</a>
            <%}%>


            <div class="story-content">
                <img class="story-image" src="story_image?image=<%= ((Story)request.getAttribute("story")).getImage() %>" alt="story-image" >
                <p><%= ((Story)request.getAttribute("story")).getContent() %></p>
            </div>
        </section>



        <div class="comment-sidebar">
            <h3>Commentaires</h3>
            <form method="post" action="comment_create">
                <textarea name="content" placeholder="Qu'est ce que vous pensez ?"></textarea>
                <input type="hidden" name="story_uuid" value="<%= request.getAttribute("uuid") %>">
                <input type="submit" value="Commenter">

            </form>


            <div class="commenter-info">
                <img class="commenter-photo" src="imgs/zeke.png" alt="owner">

                <div class="infos">
                    <span class="story-writer">Ygor Henrique</span>
                </div>
            </div>


            <p>Comment! </p>
        </div>

    </div>




<!--
    <form action="saved_stories" method="post">
        <input type="hidden" name="uuid" value="<%= request.getAttribute("uuid") %>" />
        <button type="submit">Save Story</button>
    </form>

    <form method="post" action="comment_create">
        <input type="text" name="content" placeholder="Comment...">
        <input type="hidden" name="story_uuid" value="<%= request.getAttribute("uuid") %>">
        <button type="submit">Submit</button>
    </form> -->


<!--
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

-->

</body>
</html>
