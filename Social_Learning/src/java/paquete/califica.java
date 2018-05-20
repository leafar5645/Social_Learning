
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class califica extends HttpServlet {

   
    
   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
           HttpSession sesion = request.getSession();
          
        Usuario user = new Usuario();
        user= (Usuario)sesion.getAttribute("AlumnoR");
        Tema t= (Tema) sesion.getAttribute("TemaR");
                          int idt = t.getId_tema();
        int idA=user.getId();
           Cuestionario c= (Cuestionario) sesion.getAttribute("Cuestionario");
           int cal =0;
           String resp [] = new String[10];
           int preg[] = new int[10];
           for(int i=0; i<10 ; i++)
           {
               resp[i]=request.getParameter("R" + i);
           }
           preg =c.getPreguntas();
           for (int i=0; i<10 ; i++)
           {
        try {
            cal= cal +c.califica(preg[i], resp[i]) ;
        } catch (SQLException ex) {
            Logger.getLogger(califica.class.getName()).log(Level.SEVERE, null, ex);
        }
           }
        try {
            c.MeterCal(cal, idA, idt);
        } catch (SQLException ex) {
            Logger.getLogger(califica.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet califica</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet califica at " + cal + "</h1>");
            out.println("</body>");
            out.println("</html>");
    }


  
}
