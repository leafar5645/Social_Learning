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
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>\n" +
                "    <head>\n" +
                "        <title>TODO supply a title</title>\n" +
                "        <meta charset='UTF-8'>\n" +
                "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
<<<<<<< HEAD
                "        <link rel='stylesheet' href='assets/css/main.css'>\n");
                            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
                            out.println("</head>");
                            out.println("<body class> <div id=\"wrapper\">");
                                out.println("<header>\n"+
                                    "<nav>\n"+
                "                        <ul>\n"+
                "                        <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                "                        <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a>\n"+
                                        "<ul><li><a href=\"NuevoCurso.html\">Crear Curso</a></li></ul>\n"+
                                        "</li>\n"+
                "                        <li><a href=\"BuscarCurso\"><span><i class=\"icon-search\"></i></span>Explorador</a></li>\n"+
                "                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n"+
                "                    </ul>\n"+
                "                </nav>\n"+
                            "</header>");
                out.println("        <h1>"+user.getNombre()+" estos son tus cursos</h1>");
=======
                "        <link rel='stylesheet' href='assets/css/main.css'>\n" +
                "       <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                "       <link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n"+
                "        <!-- <link rel='stylesheet' href='assets2/css/main.css'>-->\n" +
                "        \n" +
                "    </head>");
            out.println("<body class>\n" +
            "<header>\n"+
                    "<nav>\n"+
"                        <ul>\n"+
"                        <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
"                        <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a></li>\n"+
"                        <li><a href=\"NuevoCurso.html\"><span><i class=\"icon-search\"></i></span>Crear Curso</a></li>\n"+
"                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n" +
"                    </ul>\n"+
"                </nav>\n"+
            "</header>\n");
>>>>>>> 35c99622b92c533971c662999f5eeab9609c7091
            out.println("        <section id='main' >\n" +
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