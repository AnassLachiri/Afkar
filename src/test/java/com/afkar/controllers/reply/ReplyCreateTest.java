package com.afkar.controllers.reply;

import com.afkar.controllers.comment.CommentCreate;
import com.afkar.controllers.story.StoryCreate;
import com.afkar.models.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReplyCreateTest {
    @Mock
    User user = new User(2);

    @Mock
    ReplyCreate replyCreate = new ReplyCreate();


    @Before
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void doPost() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);


        when(request.getParameter("content")).thenReturn("xxx");
        when(request.getParameter("comment_id")).thenReturn("2");
        when(request.getParameter("story_uuid")).thenReturn("test");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("username")).thenReturn("hamid");
        when(request.getSession().getAttribute("user")).thenReturn(user);





        new ReplyCreate().doPost(request, response);

        verify(request, atLeast(2)).getSession();
        verify(response).sendRedirect(request.getContextPath() + "/story?uuid=" + "test");




    }

}