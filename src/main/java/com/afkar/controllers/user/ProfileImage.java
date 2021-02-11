package com.afkar.controllers.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ProfileImage extends HttpServlet {
    public static final String DOWNLOAD_DIRECTORY = "profile_images";
    public String downloadPath;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        downloadPath = getServletContext().getRealPath("") + File.separator + DOWNLOAD_DIRECTORY;

        if(req.getParameter("image") == null){
            resp.sendRedirect(req.getContextPath() + "/");
        }

        try (InputStream is = new FileInputStream(new File(downloadPath + File.separator + req.getParameter("image")))) {

            // it is the responsibility of the container to close output stream
            OutputStream os = resp.getOutputStream();

            if (is == null) {

                resp.setContentType("text/plain");
                os.write("Failed to send image".getBytes());
            } else {

                resp.setContentType("image/jpeg");

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) {

                    os.write(buffer, 0, bytesRead);
                }
            }
        }

    }
}
