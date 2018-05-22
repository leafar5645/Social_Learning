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
//recibe el id de curso como parametro, este id es la posuicion en el arreglo de los cursos
//recibe una session del usuario
public class verCurso extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        Usuario user =(Usuario) session.getAttribute("AlumnoH");
        ArrayList<Curso> cursos = user.getCursos();
        String sid = request.getParameter("id");
        int id =Integer.parseInt(sid);
        Curso actual = cursos.get(id);
        if(!actual.buscarTemas())
            response.sendRedirect("error.html");
        session.setAttribute("CursoH",actual);
        ArrayList<Tema> temas= actual.getTemas();
        try (PrintWriter out = response.getWriter())
        {
            out.println("<html>\n" +
                "    <head>\n" +
                "        <title>TODO supply a title</title>\n" +
//<<<<<<< HEAD
                "              <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
                out.println("</head>");
                out.println("<body class> <div id=\"wrapper\">");
                out.println("<header>\n"+
                    "<nav>\n"+
                    "                        <ul>\n"+
                    "                        <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                    "                        <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a></li>\n"+
                    "                        <li><a href=\"BuscarCurso\"><span><i class=\"icon-search\"></i></span>Explorador</a></li>\n"+
                    "                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n"+
                    "                    </ul>\n"+
                    "                </nav>\n"+
                    "</header>\n"+
                    "        <h1>Curso: '"+actual.getNombre()+"'</h1>\n" +
                    "        <h3>Descripción: '"+(actual.getDescripcion()).replaceAll("\n", "<br/>")+"'</h3>\n");
                out.println("       <h3>Los Temas Actuales Son:</h3>\n" +
                    "        <section id='main' >\n" +
                    "            <ul>");
                for(int i=0; i<temas.size();i++)
                {
                    if(user.getTipo().equalsIgnoreCase("P"))
                    out.println("<li> <a href='VerTemaProfesor?id="+i+"&idcurso="+id+"'>"+(temas.get(i)).getNombre()+"</a></li>");
                    else
                    out.println("<li> <a href='verTema?id="+i+"&idcurso="+id+"'>"+(temas.get(i)).getNombre()+"</a></li>");
                }
                    out.println(" </ul>\n" +
                    "        </section>\n" +
                    "<br>\n"+
                    "        <h3>Los Recursos Actuales Son:</h3>\n" +
                    "        <section id='main' >\n" +
                    "            <ul>\n" +
                    "                <li>\n" +
                    "                <a href='R1'>Nombre Recurso1</a>\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
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
