package com.afkar.controllers.auth;
import static org.mockito.Mockito.*;

import com.afkar.dao.DAOFactory;
import com.afkar.dao.UserDAO;
import com.afkar.models.User;
import com.mysql.cj.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

 class LoginTest {

     @Test
     void doGet() throws Exception {

         HttpServletRequest request = mock(HttpServletRequest.class);
         HttpServletResponse response = mock(HttpServletResponse.class);
         HttpSession httpSession = mock(HttpSession.class);

         when(request.getSession()).thenReturn(httpSession);
         when(request.getContextPath()).thenReturn("hi");
         when(httpSession.getAttribute("username")).thenReturn("hamid");

         new Login().doGet(request, response);
         verify(response).sendRedirect("hi/");
     }

    @Test
     void doPost() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);

        when(request.getParameter("username")).thenReturn("hamid");
        when(request.getParameter("password")).thenReturn("hamid");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("hi");

        new Login().doPost(request, response);
        verify(httpSession).setAttribute("username", "hamid");
        verify(response).sendRedirect("hi/");
    }


}