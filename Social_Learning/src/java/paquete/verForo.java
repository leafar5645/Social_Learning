
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
                    out.println("hay publicaciones");
            }
            else
                out.println("¡Eres el primero! <br> ¡Aprovecha para ingresar todas tus dudas!");
            out.println(" </section>\n");
            out.println(" <section id='main'>\n");
                    out.println("</br>");
                    out.println("<textarea rows=\"4\" cols=\"50\" name=\"comentario\" form=\"agregar\">");
                        out.println("Ingrese la pregunta, alcaracion o comentario que tenga referente al curso");
                    out.println("</textarea>")    ;
                    out.println("<form method='post' action='NuevaPublicacion' id='agregar'>");
                        out.println("<input type='submit' value=Enviar comentairio/>");
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
