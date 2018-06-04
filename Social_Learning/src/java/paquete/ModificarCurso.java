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
 * @author betoj
 */
public class ModificarCurso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session= request.getSession();
       Curso curso =(Curso) session.getAttribute("CursoH");
        if(request.getParameter("Eliminar")!=null)
        {
            curso.Eliminar(request.getRealPath("/RecursosCursos")); 
            session.setAttribute("CursoH",null);
            response.sendRedirect("MisCursos");
        }
        else if(request.getParameter("Cancelar")!=null)
        {
             response.sendRedirect("MisCursos");
        }
        else if(request.getParameter("Modificar")!=null)
        {
            String nombre=new String(request.getParameter("Nombre").getBytes("ISO-8859-1"),"UTF-8");
            String descripcion=new String(request.getParameter("Descripcion").getBytes("ISO-8859-1"),"UTF-8");
            curso.setDescripcion(descripcion);
            curso.setNombre(nombre);
            session.setAttribute("CursoH",curso);
            response.sendRedirect("MisCursos");
        }
        else
            response.sendRedirect("error.html");
            
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
