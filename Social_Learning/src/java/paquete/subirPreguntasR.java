/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcus
 */
public class subirPreguntasR extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        Tema t= (Tema) sesion.getAttribute("TemaR");
        int id = t.getId_tema();
        if (request.getParameter("subirp").equalsIgnoreCase("registar pregunta") )
        {
        PrintWriter out = response.getWriter();
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                       
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Introduce los datos para subir tus preguntas</h1> <br/> <br/> <form action='subirPreguntas' method='get'>  ");
            out.println("<input type='text' name='pregunta' placeholder='pregunta' required/> <br/> "
                    + "<input type='text' name='respuesta' placeholder='Respuesta Correcta'><br/> <input type='text' name='a' placeholder='opcion 1'/> "
                    + "<br/> <input type='text' name='b' placeholder='opcion 2'/> <br/> <input type='text' name='c' placeholder='opcion 3' /> <br/>"
                    + "<input type='submit' name='subirp' value='subir' ");
            out.println("</body>");
            out.println("</html>");
        }
    }

  

}
