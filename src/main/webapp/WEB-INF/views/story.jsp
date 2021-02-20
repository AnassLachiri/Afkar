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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="static_css?file=css/story.css">
    <script src="static_js?file=js/story.js" defer></script>
    <title>Story</title>
</head>
<body>

<jsp:include page="navbar.jsp"/>


<div class="story-container">

    <section class="story">
        <h1 class="title"><%= ((Story)request.getAttribute("story")).getTitle() %></h1>
        <h2><%= ((Story)request.getAttribute("story")).getSubtitle() %></h2>
        <div class="story-writer-info">
            <img class="story-writer-photo" src="profile_image?image=<%= ((User)request.getAttribute("author")).getImage() %>" alt="owner" />
            <div>
                <div class="infos">
                    <span class="story-writer"><%= ((User)request.getAttribute("author")).getUsername() %></span>
                    <a href="follow?username<%= ((User)request.getAttribute("author")).getUsername() %>" class="tiny-button">Follow</a>
                </div>
                <span class="date"><%= ((Story)request.getAttribute("story")).getCreated_at() %></span>
            </div>
        </div>
        <div class="story-content">
            <img class="story-image" src="story_image?image=<%= ((Story)request.getAttribute("story")).getImage() %>" alt="story-image" />
            <p><%= ((Story)request.getAttribute("story")).getContent() %></p>


            <% if( ((Story)request.getAttribute("story")).getUser_id() == ((User)session.getAttribute("user")).getId()  ) {%>
            <div class="controls">
                <a class="btn btn-danger" href="story_delete?uuid=<%= ((Story)request.getAttribute("story")).getUuid() %>">
                    <i class="fas fa-trash-alt"></i>
                    Supprimer
                </a>
                <a class="btn btn-success" href="story_update?uuid=<%= ((Story)request.getAttribute("story")).getUuid() %>">
                    <i class="far fa-edit"></i>
                    Modifier
                </a>
            </div>

            <%}%>

            <div class="like-nd-save">
                <form action="story_like" method="post">
                    <input type="hidden" name="uuid" value="<%= ((Story)request.getAttribute("story")).getUuid() %>" />
                    <button type="submit" class="btn btn-warning">
                        <i class="far fa-heart fa-lg"> </i>
                        <%= ((Story)request.getAttribute("story")).getTotal_likes() %>
                    </button>
                </form>
                <form action="saved_stories" method="post">
                    <input type="hidden" name="uuid" value="<%= ((Story)request.getAttribute("story")).getUuid() %>" />
                    <button type="submit" class="btn btn-info">
                        <i class="far fa-bookmark fa-lg bookmark"> </i>
                        Sauvegarder
                    </button>
                </form>
            </div>



        </div>
        <hr>





        <div class="comment-section">
            <h3>Commentaires</h3>


            <form method="post" action="comment_create">
                <textarea name="content" placeholder="Qu'est ce que vous pensez ?"></textarea>
                <input type="hidden" name="story_uuid" value="<%= ((Story)request.getAttribute("story")).getUuid() %>">
                <input type="submit" value=" Commenter ">
            </form>


            <% for(Comment comment : (ArrayList<Comment>) request.getAttribute("comments")){%>
            <div class="comment">
                <img src="profile_image?image=<%= ((HashMap<Long, User>)request.getAttribute("commentUsers")).get(comment.getId()).getImage() %>" alt="<%= ((HashMap<Long, User>)request.getAttribute("commentUsers")).get(comment.getId()).getUsername() %>" />
                <div class="comment-content">
                    <span><a href="profile_image?image=<%= ((HashMap<Long, User>)request.getAttribute("commentUsers")).get(comment.getId()).getUsername() %>"><%= ((HashMap<Long, User>)request.getAttribute("commentUsers")).get(comment.getId()).getUsername() %></a></span>
                    <p><%= comment.getContent() %></p>
                    <div class="ccc">
                        <span id="<%= comment.getId() %>" onclick="reply('<%= comment.getId() %>', '<%= ((Story)request.getAttribute("story")).getUuid() %>')">Repondre</span>
                        <% if( comment.getUser_id() == ((User)session.getAttribute("user")).getId()) {%>
                            <a href="comment_delete?comment_id=<%= comment.getId() %>&story_uuid=<%= ((Story)request.getAttribute("story")).getUuid() %>">Delete</a>
                        <%}%>
                    </div>
                </div>
            </div>
<!--
            <form method="post" action="reply_create">
                <input type="text" name="content" placeholder="Reply...">
                <input type="hidden" name="comment_id" value="<%= comment.getId() %>">
                <input type="hidden" name="story_uuid" value="<%= request.getAttribute("uuid") %>">
                <button type="submit">Submit</button>
            </form> -->


            <% if(((HashMap<Long, ArrayList<Reply>>) request.getAttribute("replies")).get(comment.getId()).size() > 0){ %>
                <% for(Reply reply : ((HashMap<Long, ArrayList<Reply>>) request.getAttribute("replies")).get(comment.getId())){%>

                <div class="reply">
                    <img src="profile_image?image=<%= ((HashMap<Long, User>)request.getAttribute("replyUsers")).get(reply.getId()).getImage() %>" alt="<%= ((HashMap<Long, User>)request.getAttribute("replyUsers")).get(reply.getId()).getUsername() %>" />
                    <div class="comment-content">
                        <span><a href="profile_image?image=<%= ((HashMap<Long, User>)request.getAttribute("replyUsers")).get(reply.getId()).getUsername() %>"><%= ((HashMap<Long, User>)request.getAttribute("replyUsers")).get(reply.getId()).getUsername() %></a></span>
                        <p><%= reply.getContent() %></p>
                        <div class="ccc">
                            <% if(reply.getUser_id() == ((User)session.getAttribute("user")).getId()) {%>
                            <a href="reply_delete?reply_id=<%= reply.getId() %>&story_uuid=<%= ((Story)request.getAttribute("story")).getUuid() %>">Delete</a>
                            <%}%>
                        </div>
                    </div>
                </div>

                <br>
                <% } %>
            <% } %>
            <br>
            <%}%>




        </div>


    </section>


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

    </div>

-->

</body>
</html>
