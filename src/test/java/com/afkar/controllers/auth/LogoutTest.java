package com.afkar.controllers.auth;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogoutTest {

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);

        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("hi");
        when(httpSession.getAttribute("username")).thenReturn("hamid");


        new Logout().doGet(request, response);
        verify(request.getSession()).removeAttribute("username");
        verify(request.getSession()).removeAttribute("user");
    }

    @Test
    void doPost() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession httpSession = mock(HttpSession.class);

        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("hi");
        when(httpSession.getAttribute("username")).thenReturn("hamid");


        new Logout().doPost(request, response);
        verify(request.getSession()).removeAttribute("username");
        verify(request.getSession()).removeAttribute("user");

    }
}