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

/**
 *
 * @author Marcus
 */
public class AltaUserR extends HttpServlet {

   

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     String nombre= request.getParameter("nombre");
     String Correo = request.getParameter("correo");
     String contra = request.getParameter("contra");
     String tipo= request.getParameter("tipo");
     
     int res=0;
     Usuario userA = new Usuario();
     res=userA.AltaUR(Correo, nombre, contra, tipo);
     if(res==1)
     {
          PrintWriter out = response.getWriter();
         response.setContentType("text/html;charset=UTF-8");
      out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'exito',\n" +
"  text: \"Alta exitosa !\",\n" +
"  type: 'error',\n" +
"  showCancelButton: false,\n" +
"  confirmButtonColor: '#d33',\n" +
"  cancelButtonColor: '#d33',\n" +
"  confirmButtonText: 'OK'\n" +
"}).then(function (result) {\n" +
"  if (result.value) {\n" +
"   window.location.href=\"index.html\";"+
"  }else{ window.location.href=\"index.html\";}\n" +
"})");
   
       out.println("</script>");
       
       out.println("</body>");

            out.println("</html>");   
     }
     else
     { 
         PrintWriter out = response.getWriter();
         response.setContentType("text/html;charset=UTF-8");
      out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'Error',\n" +
"  text: \"Error al registar intentelo mas tarde!\",\n" +
"  type: 'error',\n" +
"  showCancelButton: false,\n" +
"  confirmButtonColor: '#d33',\n" +
"  cancelButtonColor: '#d33',\n" +
"  confirmButtonText: 'OK'\n" +
"}).then(function (result) {\n" +
"  if (result.value) {\n" +
"   window.location.href=\"index.html\";"+
"  }else{ window.location.href=\"index.html\";}\n" +
"})");
   
       out.println("</script>");
       
       out.println("</body>");

            out.println("</html>");   
     }
     
    }


}
