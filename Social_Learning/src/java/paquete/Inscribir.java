
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
public class Inscribir extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
        Usuario user =(Usuario) session.getAttribute("AlumnoR");
        String Sidcurso = request.getParameter("id");
        int idcurso= Integer.parseInt(Sidcurso);
        PrintWriter out = response.getWriter();
        Curso revisar=new Curso(idcurso);
        if(revisar.getTipo()==1)
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet olvidar</title>");
            out.println("<meta charset=\"UTF-8\">\n" +
"         <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
            out.println("</head>");
            out.println("<body class>");
            out.println("<h1>Introduce la contraseña del curso: "+revisar.getNombre()+"</h1>");
            out.println("<section id='main'>");
            out.println("<br> <form action='inscribirPass?id="+Sidcurso+"&' method='post' > <input type='password' name='pass' placeholder='Contraseña'/> <input type='submit' name='boton' value='Inscribirse'/> </form>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
        else
        {
            if(!user.Inscribir(idcurso))
            response.sendRedirect("error.html");
            else
            response.sendRedirect("MisCursos");
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
