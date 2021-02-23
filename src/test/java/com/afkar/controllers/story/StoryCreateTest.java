package com.afkar.controllers.story;

import com.afkar.controllers.reply.ReplyCreate;
import com.afkar.models.Story;
import com.afkar.models.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoryCreateTest {
    @Mock
    User user = new User(2);





    @Before
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void doPost() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        Story story = mock(Story.class);
        Part part = mock(Part.class);
        final ServletContext servletContext = Mockito.mock(ServletContext.class);
        StoryCreate storyCreate = new StoryCreate(){
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };


        when(servletContext.getRealPath("")).thenReturn("hello");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("username")).thenReturn("hamid");
        when(request.getSession().getAttribute("user")).thenReturn(user);
        when(request.getParameter("subtitle")).thenReturn("su");
        when(request.getParameter("content")).thenReturn("con");
        when(request.getParameter("keywords")).thenReturn("ke");
        when(request.getParameter("title")).thenReturn("ti");

        when(request.getPart("image")).thenReturn(part);
        when(part.getSubmittedFileName()).thenReturn("hh");

        when(story.getId()).thenReturn(Long.valueOf(1));






        storyCreate.doPost(request, response);

        verify(request, atLeast(2)).getSession();
        verify(response).sendRedirect(request.getContextPath() + "/story?uuid=" + anyString());
        verify(part).getSubmittedFileName();





    }

}