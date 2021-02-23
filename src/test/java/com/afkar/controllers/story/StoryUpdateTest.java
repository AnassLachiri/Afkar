package com.afkar.controllers.story;

import com.afkar.models.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class StoryUpdateTest {

    @Mock
    User user = new User(2);




    @Before
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        final ServletContext servletContext = Mockito.mock(ServletContext.class);
        StoryUpdate storyUpdate = new StoryUpdate(){
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };

        when(request.getParameter("uuid")).thenReturn("test");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("username")).thenReturn("hamid");
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(servletContext.getRequestDispatcher("/WEB-INF/views/story_update.jsp")).thenReturn(requestDispatcher);

        storyUpdate.doGet(request, response);

        verify(request, atLeast(2)).getSession();

        verify(request).setAttribute("title", "xxx");
        verify(request).setAttribute("subtitle", "xxx");
        verify(request).setAttribute("content", "xxx");
        verify(request).setAttribute("uuid", "test");




    }



    @Test
    void doPost() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        final ServletContext servletContext = Mockito.mock(ServletContext.class);
        StoryUpdate storyUpdate = new StoryUpdate(){
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };

        when(request.getParameter("uuid")).thenReturn("test");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("username")).thenReturn("hamid");
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(servletContext.getRequestDispatcher("/WEB-INF/views/story_update.jsp")).thenReturn(requestDispatcher);
        when(request.getParameter("subtitle")).thenReturn("su");
        when(request.getParameter("title")).thenReturn("tit");
        when(request.getParameter("content")).thenReturn("con");
        when(request.getParameter("keywords")).thenReturn("ke");
        when(request.getContextPath()).thenReturn("hii");
        when(request.getSession()).thenReturn(httpSession);

        storyUpdate.doPost(request, response);

        verify(request, atLeast(2)).getSession();
        verify(response).sendRedirect("hii/story?uuid=test");





    }

}