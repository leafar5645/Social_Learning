/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
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


public class olvidar extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int p=0;
        int falso1=0;
        int falso2=0;
          Random r = new Random();
p = r.nextInt(1999999)+1;
 falso1=r.nextInt(199999)+1;
  falso2=r.nextInt(199999)+1;
 
        if(request.getParameter("boton")!=null)
        {
         String correo =request.getParameter("correo");
         Usuario user = new Usuario();
             try {
                 user.forgot(correo , p);
             } catch (SQLException ex) {
                 Logger.getLogger(olvidar.class.getName()).log(Level.SEVERE, null, ex);
             }
              final String miCorreo = "social.learning.2018@gmail.com"; //correo del administrador desde que seran enviados los mensajes
    final String miContraseña = "Social9222"; //contraseña del correo
 
    String servidorSMTP = "smtp.gmail.com";
   String puertoEnvio = "587";
    String mailReceptor = correo;
    String asunto = "Recuperar Contraseña"; 
    String cuerpo = " http://localhost:8080/Social_Learning/Recupera?autenticacion="+falso1+"&confirm="+p+"&user="+falso2+" "; //mensaje que sera enviado en el correro
  
               Properties props = new Properties();
        props.put("mail.smtp.user", miCorreo);
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", puertoEnvio);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", puertoEnvio);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

     
        //SecurityManager security = System.getSecurityManager();

        try {
           // Authenticator auth = new autentificadorSMTP();
            
            
            
            Session session;
       session = Session.getInstance(props, 
               new javax.mail.Authenticator() {
                   
                   
                   
                   
                   @Override
                   protected PasswordAuthentication getPasswordAuthentication()  {
                       
                       
                       
                       return new PasswordAuthentication(miCorreo,miContraseña);
                       
                       
                       
                   }
                   
               });

 

            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(cuerpo);
            msg.setSubject(asunto);
            msg.setFrom(new InternetAddress(miCorreo));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    mailReceptor));
         
            
            Transport.send(msg, msg.getAllRecipients());
            
        } catch (Exception mex) {
        }
         out.println("<!DOCTYPE html>");
            out.println("<html>");
   out.println("<body bgcolor='#A2E375'>");
out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.3/sweetalert2.all.js\"></script>");
     out.println("<script>");
       out.println(" swal({\n" +
"  title: 'exito',\n" +
"  text: \"Revisa tu Correo !\",\n" +
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
            out.println("<head>");
            out.println("<title>Servlet olvidar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Introduce la cuenta de correo con la que te registraste</h1>");
            out.println("<br> <form action='olvidar' method='get' > <input type='email' name='correo' placeholder='Correo'/> <input type='submit' name='boton' value='Enviar'/> </form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

  

}
