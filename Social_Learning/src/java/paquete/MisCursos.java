/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author betoj
 */
public class MisCursos extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        Usuario user =(Usuario) session.getAttribute("AlumnoR");
        if(!user.buscarCursos())
            response.sendRedirect("error.html");
        session.setAttribute("AlumnoH",user );
        ArrayList<Curso> cursos = user.getCursos();
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>\n" +
"    <head>\n" +
"        <title>TODO supply a title</title>\n" +
"              <meta charset='UTF-8'>\n" +
"        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
"     <link rel='stylesheet' href='assets/css/main.css'>\n" +
"    <!-- <link rel='stylesheet' href='assets2/css/main.css'>-->\n" +
"      \n" +
"    </head>");
            out.println("<body class>\n" +
"        <div id='wrapper'>\n" +
"        <h1>Usuario: '"+user.getNombre()+"'</h1>\n" +
"        <br/>");
            out.println("<br/>\n" +
"        <h3>Tus Cursos Actuales Son:</h3>\n" +
"        <br/>\n" +
"        <br/>\n" +
"        <section id='main' >\n" +
"            <ul>");
            for(int i=0; i<cursos.size();i++)
            {
                out.println(" <li><a href='verCurso?id="+i+"'>"+(cursos.get(i)).getNombre()+"</a></li>"); 
            }
            out.println(" </ul>\n" +
"        \n" +
"        <br/>\n" +
"        </section>\n" +
"        </div>\n" +
"    </body>\n" +
"</html>");
           
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
