/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class BuscarCurso extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<link rel=\"stylesheet\" href=\"assets/css/main.css\">\n");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
            out.println("</head>");
            out.println("<body class> <div id=\"wrapper\">");
            out.println("<header>\n"+
                "<nav><ul>\n");
            out.println(
                "   <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
                "   <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a></li>\n"+
                "   <li><a href=\"BuscarCurso\"><span><i class=\"icon-search\"></i></span>Explorador</a></li>\n"+
                "   <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n");
            out.println("</ul>\n"+
                "</nav>\n"+
                "</header>");
            out.println("<h1>Explorador de Cursos</h1>");
            out.println("<section id='main'>");
            out.println("<h4>Criterios de busqueda</h4> <form method='get' action='BuscarCurso'>");
            out.println("<h4>Ingrese palabras clave:</h4> (curso, autor o descripcion)<input type='text' name='palabras' required/>");
            out.println("<h4>Seleccione el criterio de busqueda:</h4><select name='criterio'>");
            out.println("<option value='autor'>autor</option>");
            out.println("<option value='curso'>curso</option>");
            out.println("<option value='descripcion'>descripcion</option>");
            out.println("</select>");
            out.println("<h4>Seleccione el orden:</h4><select name='orden'>");
            out.println("<option value='recientes'>más recientes</option>");
            out.println("<option value='alfabetico'>alfabetico</option>");
            out.println("</select>");
            out.println("<input type='submit' value='buscar'>");
            out.println("</form>");
            out.println("</section>");
            out.println("<br/>");
            String palabras=request.getParameter("palabras");
            String criterio=request.getParameter("criterio");
            String orden=request.getParameter("orden");
            HttpSession sesion=request.getSession();
            ResultSet resul;
           Usuario us=(Usuario)sesion.getAttribute("AlumnoR");
           us.buscarCursos();
            Curso buscar=new Curso();
            ArrayList<Curso> cursos = us.getCursos();
           out.println(us.getId());
            try{
                if(palabras==null)
                {//si no se ingresó algún criterio
                   resul=buscar.buscarCursos("","","");
                }
                else // se ingresaron datos
                    resul=buscar.buscarCursos(palabras,criterio,orden);  
                
                if(resul.isBeforeFirst())
                    {
                        int bandera=1;
                            while(resul.next())
                            {
                               bandera=1;
                                for(int i=0;i<cursos.size();i++)
                                {
                                    if(((cursos.get(i)).getId_curso())==resul.getInt("idcurso"))
                                    bandera=0;
                                }
                                //out.println(cursos.size()); 
                                out.println("<section id='main'>");
                                out.println("<h4>creador:</h4> "+resul.getString("autor")+"<br>");
                                out.println("<h4>nombre del curso:</h4> "+resul.getString("nombre")+"<br>");
                                out.println("<h4>descripcion del curso:</h4> "+resul.getString("descripcion")+"<br>");
                                if(bandera==0)
                                out.println("YA INSCRITO");
                                else
                                out.println(" <a href='Inscribir?id="+resul.getInt("idcurso")+"'>Inscribir</a>");
                                out.println("</section>");
                                out.println("<br/>");
                                
                            }
                    }
                    else{
                        out.println("<section id='main'>");
                        out.println("<h4>No hay resultados!!</h4>");
                        out.println("</section>");
                        out.println("<br/>");
                    }
            }
            catch(Exception e){
                out.println("Error en:"+e);
            }
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }

    }

}
