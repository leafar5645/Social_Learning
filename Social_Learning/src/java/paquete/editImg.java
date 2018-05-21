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
public class editImg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
             HttpSession sesion= request.getSession();
        Usuario user =(Usuario) sesion.getAttribute("AlumnoR");
        if (user==null)
        {
            response.sendRedirect("index.html");
        }
        else
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n" +
"    <!-- <link rel=\"stylesheet\" href=\"assets2/css/main.css\">-->");
            out.println("</head>");
            out.println("<body class> <div id=\"wrapper\"> ");
            out.println("<h1>Selecciona la nueva imagen </h1> <br/>  <section id=\"main\"> <form action='editImg2' method='post' enctype='multipart/form-data'>"
                    + "<br/> <br/> <input type='file' name='foto' accept='.jpg' required> <input type='submit'/> </form>   ");
          out.println("</section </div> </body>");
            out.println("</html>");
        }
    }
}