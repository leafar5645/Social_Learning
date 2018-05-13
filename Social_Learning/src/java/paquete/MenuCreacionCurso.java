
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
public class MenuCreacionCurso extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        Usuario user =(Usuario) session.getAttribute("AlumnoR");
        Curso curso =(Curso) session.getAttribute("CursoNH");
        ArrayList<Tema> temas = curso.getTemas();
        try (PrintWriter out = response.getWriter()) 
               //imprimimos el html para agregar temas
            {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>\n" +
"        <title>TODO supply a title</title>\n" +
"              <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n" +
"    <!-- <link rel=\"stylesheet\" href=\"assets2/css/main.css\">-->\n" +
"      \n" +
"    </head>");
                out.println("<body class>");            
                out.println("<div id='wrapper'>\n" +
"        <h1>Curso: '"+curso.getNombre()+"'</h1>\n" +
"        <h4>ID Curso: '"+curso.getId_curso()+"'</h4>\n" +
"        <br/>\n" +
"        <br/>\n" +
"        <h3>Los Temas Actuales Son:</h3>\n" +
"        <br/>\n" +
"        <br/>");
                out.println("<section id='main' >\n" +
"            <ul>");
                
               for(int i=0;i<temas.size();i++)
               {
                out.println("<li>\n" +
"                <a href='modificarTemas?id="+(temas.get(i)).getId_tema()+"'>"+(temas.get(i)).getNombre()+"</a>\n" +
"                </li>");
               }
               if(temas.size()==0)
                   out.println("<li>Actualmente no tienes Temas<li/>");
                out.println("</ul><br/>");
                out.println(" <form action='cargar_contenido.html' method ='post' enctype='multipart/form-data'>\n" +
"            <input type='submit' name='recursos' value='Agregar Recursos'>\n" +
"        </form>");
                out.println("<form action='NuevoTema.html' method ='post' enctype='multipart/form-data'>\n" +
"           <input type='submit' name='NuevoTema' value ='Nuevo Tema'>\n" +
"        </form>");
                out.println("   <form action='MisCursos' method='post'>\n" +
"            <input type='submit' name='fin' value='Finalizar'/>\n" +
"        </form>");
                out.println("</section>\n" +
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
