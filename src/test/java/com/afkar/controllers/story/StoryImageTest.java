package com.afkar.controllers.story;

import com.afkar.models.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class StoryImageTest {


    @Before
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void doGet() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        final ServletContext servletContext = Mockito.mock(ServletContext.class);
        StoryImage storyImage = new StoryImage(){
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };


        when(servletContext.getRealPath("")).thenReturn("target/Afkar-1.0-SNAPSHOT");
        when(request.getParameter("image")).thenReturn("059b3737648e3764def1b1a409758c12Eo5nPMbWMAQnBnR.jpeg");
        when(response.getOutputStream()).thenReturn(outputStream);


        storyImage.doGet(request, response);
        verify(response).setContentType("image/jpeg");








    }


}