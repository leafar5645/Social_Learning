/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcus
 */
public class login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion= request.getSession();
        int res=0;
       
        String correo = request.getParameter("correo");
        String pass = request.getParameter("pass");
        Usuario user = new  Usuario();
       
        try {
            res = user.loginR(correo, pass);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (res==1)
        {
         sesion.setAttribute("AlumnoR", user);
         response.sendRedirect("menu");
        }
        else
        {
            response.sendRedirect("index.html");
        }
        
      
    }

 

}
