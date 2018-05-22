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
public class VerTemaProfesor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        Curso curso =(Curso) session.getAttribute("CursoH");
        ArrayList<Tema> temas = curso.getTemas();
        String sid = request.getParameter("id");
        int id =Integer.parseInt(sid);
        String sidcurso = request.getParameter("idcurso");
        int idcurso =Integer.parseInt(sidcurso);
        Tema actual = temas.get(id);
        session.setAttribute("TemaR", actual);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>\n" +
                        "    <head>\n" +
                        "        <title>TODO supply a title</title>\n" +
                        "        <meta charset='UTF-8'>\n" +
                        "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                        "        <link rel='stylesheet' href='assets/css/main.css'>\n" +
                        "       <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                        "       <link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n"+
                        "    </head>");
            out.println("<body class>\n" +
                        "<header>\n"+
                        "<nav>\n"+
                        "                        <ul>\n"+
                        "                        <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                        "                        <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a></li>");
            out.println(" <ul>");
            for(int i=0; i<temas.size();i++)
                out.println("<li><a href='VerTemaProfesor?id="+i+"&idcurso="+id+"'>"+(temas.get(i)).getNombre()+"</a></li>");
            out.println(" </ul>\n" +
                        "                        <li><a href=\"NuevoCurso.html\"><span><i class=\"icon-search\"></i></span>Crear Curso</a></li>\n"+
                        "                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n" +
                        "                    </ul>\n"+
                        "                </nav>\n"+
                        "</header>\n");
            out.println(
                        "        <div id='wrapper'>\n" +
                        "        <h1>Modificar Tema</h1>\n" +
                        "        <h3>Por favor Modifique los datos que quiera cambiar</h3>\n");
            out.println("<form action='ModificarTema?idcurso="+idcurso+"' method ='post' enctype=\"multipart/form-data\">");
            out.println("  <input type='text' name='NombreTema' placeholder='Nombre de Tema' value='"+actual.getNombre()+"' required></input>\n" +
                        "                <br/>");
            out.println(" <textarea name='descripcion' placeholder='Descripción' rows='10' cols='50' required>"+actual.getInformacion()+"</textarea>\n" +
                        "                <br/>");
            out.println("<div style=\"background:white;\" align=\"Center\">\n" +
                        "            <video width=\"320\" height=\"240\" controls>\n" +
                        "              <source src=\"RecursosTemas\\"+actual.getRecurso()+"\" type=\"video/mp4\">\n" +
                        "            Tu navegador No tiene Soporte Para el Video\n" +
                        "            </video> \n" +
                        "        <br/>");
            out.println(" <br/>\n" +
                        "                Recurso de Apoyo(video): "+actual.getRecurso()+"\n" +
                        "            <br> <input type='file' name='multimedia' accept=\".mp4\"/>\n" +
                        "                <br/>\n" +
                        "            <input type='submit' name='Cancelar' value='Cancelar'/>\n" +
                        "            <br/><br/>\n" +
                        "            <input type='submit' name='Enviar' value ='Enviar'>\n" +
                        "            <!--<ul class='icons'> <li> <a href='#' title='Registrar' onclick='$(this).closest('form').submit()' name='destroy' value='alta' class='fa-sign-in'>Registar</a> </li></ul>-->\n" +
                        "        </form>\n");
            out.println("    </form>\n" + "<form action='subirPreguntasR' method='get'><br/> <input type='submit' value='registar pregunta' name='subirp'/> </form>  ");
            out.println(
                        "        </section>\n" +
                        "          </div>\n" +
                        "    </body>\n" +
                        "</html>\n" +
                        "");
        }
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
