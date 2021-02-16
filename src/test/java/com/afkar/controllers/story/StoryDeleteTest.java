package com.afkar.controllers.story;

import com.afkar.controllers.reply.ReplyDelete;
import com.afkar.models.Story;
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

class StoryDeleteTest {
    @Mock
    User user = new User(2);

    @Mock
    StoryDelete storyDelete = new StoryDelete();


    @Before
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void doGet() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        Story story = mock(Story.class);



        when(request.getParameter("uuid")).thenReturn("a5b7d1c578d121204d5a3e2d865100fc");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("username")).thenReturn("hamid");
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(story.getId()).thenReturn(Long.valueOf(1));



        storyDelete.doGet(request, response);

        verify(request, atLeast(2)).getSession();
        verify(response).sendRedirect(request.getContextPath() + "/");




    }



}