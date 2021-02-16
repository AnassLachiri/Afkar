package com.afkar.controllers.comment;

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

class CommentDeleteTest {
    @Mock
    User user = new User(2);

    @Mock
    CommentDelete commentDelete = new CommentDelete();


    @Before
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void doGet() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);


        when(request.getParameter("comment_id")).thenReturn("1");
        when(request.getParameter("story_uuid")).thenReturn("test");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("username")).thenReturn("hamid");
        when(request.getSession().getAttribute("user")).thenReturn(user);




        commentDelete.doGet(request, response);

        verify(request, atLeast(2)).getSession();
        verify(response).sendRedirect(request.getContextPath() + "/story?uuid=" + "test");
    }
}