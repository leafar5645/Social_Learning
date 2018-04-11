/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Marcus
 */
public class subMenuR extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion=request.getSession();
            
             Usuario user = (Usuario) sesion.getAttribute("AlumnoR");
             String nomb =user.getNombre();
             String tipo =user.getTipo();
             String correo=user.getCorreo();
             int id=user.getId();
              if (tipo.equalsIgnoreCase("p")) 
              {
                  tipo="Profesor";
              }
              else
              {
              tipo="Alumno";
              }
             if(request.getParameter("editR")==null || request.getParameter("editR").equalsIgnoreCase("cancelar"))
             {
            
             
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet subMenuR</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bienvenido " +tipo + "</h1><br/>");
            out.println("<h2>Este es tu perfil</h2><br/>");
            out.println("<b>Id: </b> "+id+"<br/>");
            out.println("<b>Nombre: </b> "+ nomb+ "<br/>");
            out.println("<b>correo: </b> "+ correo+ "<br/>");
            out.println("<form action='subMenuR' method='get' >"
                    + " <input type='submit' name='editR' value='Editar' />"
                    + "<form/> ");
            
            out.println("</body>");
            out.println("</html>");
             }
             else
                 
             {
                  out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet subMenuR</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edite su perfil  " +tipo + "</h1><br/>");
            out.println("<form action='editPR' method='get' >");
            out.println("<b>Nombre: </b> <input type='text' value='"+ nomb+ "' name='nombre' /><br/>");
            out.println("<b>correo: </b> <input type='text' value='"+ correo+ "' name='correo' /><br/>");
            out.println("<b><h3>*Si no escribe nada en estos campos no se cambiara la contraseña</b></h3>");
            out.println("<b>Nueva contraseña: </b> <input type='text' value='' name='conN' /><br/>");
            out.println("<b>Escribala de nuevo: </b> <input type='text' value='' name='conN2' /><br/>");
            out.println("<b><h3>*Requisito para cualquier cambio </b></h3>");
            out.println("<b>Contraseña actual: </b> <input type='text' value='' name='con' required /><br/>");
            
                 out.println( " <input type='submit' value='Cambiar' />"
                    + "</form> ");
                 
                 
               out.println("<form action='subMenuR' method='get' >"
                    + " <input type='submit' name='editR' value='cancelar' />"
                    + "<form/> ");
               
            out.println("</body>");
            out.println("</html>");
             }
             
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
