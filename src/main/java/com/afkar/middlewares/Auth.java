package com.afkar.middlewares;

import javax.servlet.http.HttpSession;

public class Auth {

    public static boolean userLoggedIn(HttpSession httpSession){
        String username = (String) httpSession.getAttribute("username");
        if(username != null && !username.trim().isEmpty()){
            // User connected
            return true;
        }
        return false;
    }

}
