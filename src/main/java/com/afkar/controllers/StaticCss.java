package com.afkar.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class StaticCss extends HttpServlet {
    public static final String DOWNLOAD_DIRECTORY = "static_files";
    public String downloadPath;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        downloadPath = getServletContext().getRealPath("") + File.separator + DOWNLOAD_DIRECTORY;


        String file = req.getParameter("file");

        if(file == null){
            resp.sendRedirect(req.getContextPath() + "/");
        }

        String[] parts = file.split("/");
        file = "";
        for(int i= 0; i < parts.length - 1 ; i++){
            file += parts[i];
            file += File.separator;
        }

        file += parts[parts.length - 1];

        try (InputStream is = new FileInputStream(new File(downloadPath + File.separator + file))) {

            // it is the responsibility of the container to close output stream
            OutputStream os = resp.getOutputStream();

            resp.setContentType("text/css");
            if (is == null) {
                os.write("Failed to send image".getBytes());
            } else {

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) {

                    os.write(buffer, 0, bytesRead);
                }
            }
        }

    }
}
