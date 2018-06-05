
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author betoj
 */
public class NuevoCurso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //comprobando que no se hay cancelado
       if(request.getParameter("Cancelar")!=null)
           response.sendRedirect("subMenuR");
       else if(request.getParameter("limpiar")!=null)
         response.sendRedirect("NuevoCurso.html");
       else
       {
           HttpSession session= request.getSession();
           Usuario user =(Usuario) session.getAttribute("AlumnoR");
           String nombre=new String(request.getParameter("NombreCurso").getBytes("ISO-8859-1"),"UTF-8");
           String descripcion=new String(request.getParameter("descripcion").getBytes("ISO-8859-1"),"UTF-8");
           String Stipo=new String(request.getParameter("tipo").getBytes("ISO-8859-1"),"UTF-8");
           String pass=new String(request.getParameter("pass").getBytes("ISO-8859-1"),"UTF-8");
           int tipo = Integer.parseInt(Stipo);
           Curso nuevo;
           //crea el objeto curso y lo subimnos a sesion
           if((pass.length())>0)
            nuevo = new Curso(nombre,descripcion,user,tipo,pass);
           else
               nuevo = new Curso(nombre,descripcion,user,tipo,"1");
          
           if(nuevo.getId_curso()!=-1)
           {
               session.setAttribute("CursoH", nuevo);
               response.sendRedirect("MenuCreacionCurso");
           }
           else
               response.sendRedirect("error.html");
           
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
    }

}
