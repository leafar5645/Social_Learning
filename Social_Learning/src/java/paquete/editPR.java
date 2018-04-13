/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Marcus
 */
public class editPR extends HttpServlet {

 

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession sesion= request.getSession();
        int res=0;
       
        String correo = request.getParameter("correo");
        String nomb = request.getParameter("nombre");
        String con = request.getParameter("con");
        String conN= request.getParameter("conN");
        String conN2 =request.getParameter("conN2");
        Usuario user =(Usuario) sesion.getAttribute("AlumnoR");
        int id = user.getId();
        
       
        try {
            res = user.EditPR(correo, nomb ,id, conN, conN2, con );
            
            
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (res==1)
        {
         sesion.setAttribute("AlumnoR", user);
         response.sendRedirect("subMenuR");
        }
        else
        {
            PrintWriter out=response.getWriter();
             response.setContentType("text/html;charset=UTF-8");
                          out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'Error',\n" +
"  text: \"Contraseña incorrecta !\",\n" +
"  type: 'error',\n" +
"  showCancelButton: false,\n" +
"  confirmButtonColor: '#d33',\n" +
"  cancelButtonColor: '#d33',\n" +
"  confirmButtonText: 'OK'\n" +
"}).then(function (result) {\n" +
"  if (result.value) {\n" +
"   window.location.href=\"subMenuR\";"+
"  }else{ window.location.href=\"subMenuR\";}\n" +
"})");
   
       out.println("</script>");
       
       out.println("</body>");

            out.println("</html>");
           ;
        }
     
    }



}
