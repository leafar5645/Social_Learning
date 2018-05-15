
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
public class verTema extends HttpServlet {

   
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
"              <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n" +
"    <!-- <link rel=\"stylesheet\" href=\"assets2/css/main.css\">-->\n" +
"      \n" +
"    </head>\n" +
"    <body class>");
            out.println(" <ul style=\"margin:0;padding:0;\">");
            for(int i=0; i<temas.size();i++)
                out.println("<li style='display:block;float:left;padding:0 10px;'> <a href='verTema?id="+i+"&idcurso="+id+"'>"+(temas.get(i)).getNombre()+"</a></li>");
            out.println(" </ul>\n" +
"        <br/>\n" +
"        <div style=\"background:white;\" align=\"center\">\n" +
"             <h1>"+actual.getNombre()+"</h1>\n" +
"        </div>");
            out.println("<div style=\"background:white;\" align=\"center\">\n" +
"             Informacion:<br/>"+(actual.getInformacion()).replaceAll("\n", "<br/>")+"\n" +
"        </div>\n" +
"          <br/>"); 
            if(!actual.getRecurso().equals("0"))
            out.println("<div style=\"background:white;\" align=\"Center\">\n" +
"            <video width=\"320\" height=\"240\" controls>\n" +
"              <source src=\"RecursosTemas\\"+actual.getRecurso()+"\" type=\"video/mp4\">\n" +
"            Tu navegador No tiene Soporte Para el Video\n" +
"            </video> \n" +
"          </div>\n" +
"        <br/>");
            out.println(" <div style='background:white;' align='center'>\n" +
"    <form action='verCurso?id="+idcurso+"'  method='post'>\n" +
"        <input type='submit' name='fin' value='Volver a Curso'/>\n" +
"    </form>\n" + "<form action='subirPreguntasR' method='get'><br/> <input type='submit' value='registar pregunta' name='subirp'/> </form>  " +
"    </div>\n" +
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
