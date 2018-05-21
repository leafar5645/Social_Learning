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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcus
 */
public class Recupera extends HttpServlet {


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(request.getParameter("boton")!=null)
        {
              HttpSession sesion = request.getSession();
            String pass= request.getParameter("pass");
            String correo =(String)sesion.getAttribute("olvida");
            Usuario user= new Usuario();
            sesion.removeAttribute("olvida");
            sesion.invalidate();
            int res=0;
            try {
                res=user.Recupera(correo, pass);
            } catch (SQLException ex) {
                Logger.getLogger(Recupera.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(res==1)
            {
                
                //
                
                
                
                //
                out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'Error',\n" +
"  text: \"Se actualizo tu contraseña :) !\",\n" +
"  type: 'success',\n" +
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
                out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'Error',\n" +
"  text: \"Hubo un error intentalo despues !\",\n" +
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
        else
        {
            Conexion_Base conexion=null;
            String correo = request.getParameter("confirm");
            HttpSession sesion = request.getSession();
            sesion.setAttribute("olvida", correo);
            String olvidar="";
            conexion = new Conexion_Base();
        Connection con=conexion.getConnection();
           Statement st = null  ;
            ResultSet result = null;

        try
        {
         st = con.createStatement();
             result = st.executeQuery("select * from usuario where olvidar ='"+correo+"';");
            if(result.next())
            {
               olvidar=result.getString("olvidar");
            }
            else
            {
                out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'Error',\n" +
"  text: \"Usuario no valido!\",\n" +
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
        catch(SQLException e)
        {
            System.out.println("e");
        }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Recupera.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(olvidar.equalsIgnoreCase("no"))
            {
                   out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'Error',\n" +
"  text: \"Este Usuario no pidio un cambio!\",\n" +
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
                        
              out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Recupera</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Introduce tu nueva contraseña </h1>");
                       out.println("<br> <form action='Recupera' method='get' > <input type='password' name='pass' placeholder='Contraseña'/> <input type='submit' name='boton' value='Enviar'/> </form>");

            out.println("</body>");
            out.println("</html>");
            }
        }
    }


}
