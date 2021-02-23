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
import javax.servlet.http.Part;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class StoryIndexTest {
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
        StoryIndex storyIndex = new StoryIndex(){
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };

        when(request.getParameter("uuid")).thenReturn("2ba3f327283df672ded6a230f2c9f31c");
        when(servletContext.getRequestDispatcher("/WEB-INF/views/story.jsp")).thenReturn(requestDispatcher);

        when(request.getParameter("subtitle")).thenReturn("su");
        when(request.getParameter("content")).thenReturn("con");
        when(request.getParameter("keywords")).thenReturn("ke");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("username")).thenReturn("hamid");
        when(request.getSession().getAttribute("user")).thenReturn(user);









        storyIndex.doGet(request, response);

        verify(request, atLeast(2)).getSession();

        verify(request).setAttribute("title", "ti");
        verify(request).setAttribute("subtitle", "su");
        verify(request).setAttribute("content", "con");
        verify(request).setAttribute("uuid", "2ba3f327283df672ded6a230f2c9f31c");
        verify(request).setAttribute("user_id", Long.valueOf(2));
        verify(request).setAttribute("login_id", Long.valueOf(2));






    }


}