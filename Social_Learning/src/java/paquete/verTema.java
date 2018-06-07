
package paquete;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
            out.println("<!DOCTYPE html>");
            out.println("<html>\n" +
                "    <head>\n" +
                "              <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                " <script type=\"text/javascript\" src=\"fabric.js\"></script>\n" +
                "     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
            out.println("</head>");
            out.println("<body class>");
            out.println("<header>\n"+
                "   <nav>\n"+
                "       <ul>\n"+
                "           <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                "           <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a>");
            out.println(" <ul>");
            for(int i=0; i<temas.size();i++)
                out.println("<li><a href='verTema?id="+i+"&idcurso="+idcurso+"'>"+(temas.get(i)).getNombre()+"</a></li>");
            out.println(" </ul></li>\n" +
                "           <li><a href=\"BuscarCurso\"><span><i class=\"icon-search\"></i></span>Explorador</a></li>\n"+
                "           <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n"+
                "       </ul>\n"+
                "   </nav>\n"+
                "</header><div id=\"wrapper\">");
            out.println("<h1>Curso: '"+curso.getNombre()+"'</h1>\n");
            out.println("<section id=\"main\">\n");
            out.println("<h2>Tema: "+actual.getNombre()+"</h2>\n"+
                "   <input type='hidden' id='serie' value='"+(actual.getInformacion().replaceAll("\\n","\\\\n"))+"' />     <br/>\n" +
                "    <canvas id=\"canvas\" width=\"750\" height=\"800\" style='border:1px solid black;'></canvas>\n" +
                "    <script>\n"+
                "    var canvas = new fabric.StaticCanvas ('canvas');\n"+
                "    canvas.loadFromJSON(document.getElementById('serie').value);\n"+
                "    </script>\n"+
                "        </section>\n" +
                "          <br/>");
            if(!actual.getRecurso().equals("0"))
                out.println("<section id=\"main\">\n" +
                    "            <video width=\"320\" height=\"240\" controls>\n" +
                    "              <source src=\"RecursosTemas\\"+actual.getRecurso()+"\" type=\"video/mp4\">\n" +
                                    "<source src=\"RecursosTemas\\"+actual.getRecurso()+"\" type=\"video/ogg\" />\n" +
                                    "<source src=\"RecursosTemas\\"+actual.getRecurso()+"\" type=\"video/webm\" />" +
                    "            Tu navegador No tiene Soporte Para el Video\n" +
                    "            </video> \n" +
                    "          </section>\n" +
                    "        <br/>");
            out.println("<section id=\"main\">\n" +
                "    <form action='verCurso?id="+idcurso+"'  method='post'>\n" +
                "        <input type='submit' name='fin' value='Volver a Curso'/>\n" );
       //     out.println("    </form>\n" + "<form action='subirPreguntasR' method='get'><br/> <input type='submit' value='registar pregunta' name='subirp'/> </form>  ");
       int res=0;
     Conexion_Base conexion=new Conexion_Base();
        conexion = new Conexion_Base();
        Connection con = conexion.getConnection();
        Statement st=null;
        ResultSet resul=null;
        try
        {
            st=con.createStatement();
           resul=st.executeQuery("select max(idpregunta) from pregunta where idt='"+actual.getId_tema()+"';");
           if (resul.next()){
               int ids  =resul.getInt(1);
               if(ids>=10)
               {
       out.println("</form>\n" + "<form action='Examen' method='get'><br/> <input type='submit' value='Cuestionario' name='examen'/> </form> ");
            out.println(" </section>\n" +
                "    </body>\n" +
                "</html>");
               }
           }}
        catch(Exception e)
        {
            System.out.println(""+e.getMessage());
        }
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