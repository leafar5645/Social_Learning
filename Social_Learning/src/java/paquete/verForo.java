
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
 * @author User
 */
public class verForo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       HttpSession sesion=request.getSession();
       Curso Dueño=(Curso)sesion.getAttribute("CursoH");
       Usuario user =(Usuario) sesion.getAttribute("AlumnoH");
       Foro foro=new Foro(Dueño.getId_curso(), user.getId());
       ArrayList<Curso> cursos = user.getCursos();
       boolean hayPublicaciones=foro.buscarPublicaciones();
       ArrayList<Publicacion> publicaciones=foro.getPublicaciones() ;
            out.println("<!DOCTYPE html>");
            out.println("<html>\n" +
                "    <head>\n" +
                "        <title>TODO supply a title</title>\n" +
//<<<<<<< HEAD
                "              <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
            out.println("<style>div.ex3 {\n" +
                                 " overflow: auto;\n" +
                                 "}");
            out. println("fieldset {\n" +
            "    margin: 8px;\n" +
            "    border: 1px solid silver;\n" +
            "    padding: 8px;    \n" +
            "    border-radius: 4px;\n" +
            "}\n" +
            "\n" +
            "legend {\n" +
            "    padding: 2px;   } \n");
            out.println("textarea.tipo1 {\n" +
            "  width: 300px;\n" +
            "  height: 150px;\n" +
            "}"+"</style>");
            out.println("</head>");
            out.println("<body class> <div id=\"wrapper\">");
            out.println("<header>\n"+
                "<nav>\n"+
                "                        <ul>\n"+
                "                        <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                "                        <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a>\n");
            out.println(" <ul>");
            for(int i=0; i<cursos.size();i++)
                        out.println(" <li><a href='verCurso?id="+i+"'>"+(cursos.get(i)).getNombre()+"</a></li>");
            out.println(" </ul></li>\n" +
                "                        <li><a href=\"BuscarCurso\"><span><i class=\"icon-search\"></i></span>Explorador</a></li>\n"+
                "                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n"+
                "                    </ul>\n"+
                "                </nav>\n"+
                "   </header>\n");
            out.println("<h1>Foro del curso:"+Dueño.getNombre()+"</h1>");
                            out.println("<section id='main' >\n");
            if(hayPublicaciones)
            {
                for(int i=0; i<publicaciones.size();i++)
                {
                    boolean HayComentarios=(publicaciones.get(i)).buscarComentarios();
                    ArrayList<Comentario> comentarios = (publicaciones.get(i)).getComentarios();
                    out.println("<fieldset>");
                        out.println("<legend>"+(publicaciones.get(i)).getAutor()+"</legend>");
                            out.println("<legend>Contenido</legend>");
                            out.println((publicaciones.get(i)).getContenido());
                        out.println("<form method='post' action='darLike?id="+(publicaciones.get(i)).getIdPubli()+"'><input type='submit' value='Me gusta'>");
                        out.println("Me gusta: "+((publicaciones.get(i)).getLikes()));
                        out.println("</form>");
                    System.out.println("Respuesta: "+HayComentarios);
                    if(HayComentarios)
                    {
                        for(int j=0; j<comentarios.size();j++)
                        {
                            out.println("<fieldset>");
                            out.println("<legend>"+(comentarios.get(i)).getAutor()+"</legend>");
                                out.println("<legend>Comentario:</legend>");
                                out.println((comentarios.get(i)).getTexto());
                        out.println("</fieldset>");
                        }
                    }
                    else
                        out.println("Sin comentarios<br/>");
                        out.println("<textarea rows=\"1\" cols=\"0.5\" name=\"responder\" form=\"comentar\" class='tipo1'/>");
                        out.println("</textarea>");
                        out.println("<form method='post' action='NuevoComentario' id='comentar'>");
                            out.println("<input type='hidden' value='"+(publicaciones.get(i)).getIdPubli()+"' name='publicacion'/>");
                            out.println("<input type='submit' value=Enviar comentario/>");
                        out.println("</form>");
                        out.println("</fieldset>");
                }
                   
            }
            else
                out.println("¡Eres el primero! <br> ¡Aprovecha para ingresar todas tus dudas!");
            out.println(" </section>\n");
            out.println(" <section id='main'>\n");
                    out.println("</br>");
                    out.println("<textarea rows=\"4\" cols=\"50\" name=\"comentario\" form=\"agregar\">");
                        out.println("Ingrese la pregunta, aclaracion o comentario que tenga referente al curso");
                    out.println("</textarea>")    ;
                    out.println("<form method='post' action='NuevaPublicacion' id='agregar'>");
                        out.println("<input type='submit' value=Enviar comentario/>");
                    out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
