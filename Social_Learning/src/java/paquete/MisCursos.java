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
//recibe una session de el usuario actual
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
            out.println("<!DOCTYPE html>");
            out.println("<html>\n" +
                "    <head>\n" +
                "        <meta charset='UTF-8'>\n" +
                "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "        <link rel='stylesheet' href='assets/css/main.css'>\n" +
                "       <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                "       <link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n"+
                "    </head>");
            out.println("<body class><div id='wrapper'>\n" +
                "<header>\n"+
                "<nav>\n"+
                "                        <ul>\n");
            if (user.getTipo().equalsIgnoreCase("p")){
               out.println("             <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                "                        <li><a href=\"MisCursos\"><span><i class=\"icon-search\"></i></span>Mis Cursos</a></li>\n"+
                "                        <li><a href=\"NuevoCurso.html\"><span><i class=\"icon-briefcase\"></i></span>Crear Curso</a></li>\n"+
                "                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n");
           }
           else
           {
            out.println("                <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                "                        <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a></li>\n"+
                "                        <li><a href=\"BuscarCurso\"><span><i class=\"icon-search\"></i></span>Explorador</a></li>\n"+
                "                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n");
            }
        out.println("</ul>\n"+
            "                </nav>\n"+
            "</header>\n");
        out.println("<h1>Tus Cursos Actuales</h1>");
        out.println("        <section id='main' >\n" +
            "            <ul>");
        for(int i=0; i<cursos.size();i++)
        {
            out.println(" <li><a href='verCurso?id="+i+"'>"+(cursos.get(i)).getNombre()+"</a>");
            
            out.println("</li>");
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