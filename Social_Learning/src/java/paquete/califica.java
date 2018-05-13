
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
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
               cal= cal +c.califica(preg[i], resp[i]) ;
           }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet califica</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet califica at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
    }


  
}
