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
           String nombre=request.getParameter("NombreCurso");
           String descripcion= request.getParameter("descripcion");
           //crea el objeto curso y lo subimnos a sesion
           Curso nuevo = new Curso(nombre,descripcion,user);
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
