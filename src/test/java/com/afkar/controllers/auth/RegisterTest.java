package com.afkar.controllers.auth;

import com.afkar.controllers.story.StoryCreate;
import com.afkar.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterTest {

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);

        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("hi");
        when(httpSession.getAttribute("username")).thenReturn("hamid");

        new Register().doGet(request, response);
        verify(response).sendRedirect("hi/");
    }

    @Test
    void doPost() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);
        User user = mock(User.class);
        Part part = mock(Part.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        Register register = new Register(){
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };

        when(request.getParameter("username")).thenReturn("rong randncdefdom");
        when(request.getParameter("email")).thenReturn("randomdsfrdssdkf@gmail.com");
        when(request.getParameter("password1")).thenReturn("hamid");
        when(request.getParameter("password2")).thenReturn("hamid");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("hi");
        when(servletContext.getRealPath("")).thenReturn("excpected");
        when(request.getPart("image")).thenReturn(part);

        register.doPost(request, response);
        verify(response).sendRedirect("hi/");
    }
}