/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

//import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.jcp.xml.dsig.internal.dom.Utils;

/**
 *
 * @author Marcus
 */
  
public class AltaUserR extends HttpServlet {

   

 
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     String nombre= request.getParameter("nombre");
     String Correo = request.getParameter("correo");
     String contra = request.getParameter("contra");
     String tipo= request.getParameter("tipo");
        Part filePart = request.getPart("foto"); //Devuelve una parte especifica del request. Part Esta clase representa una pieza o elemento de formulario que se recibió dentro de una multipart/form-datas en solicitud POST.
     
   String path=request.getRealPath("/UsuariosFotosR");
 

    Random r = new Random();
int num = r.nextInt(1000000)+743;
 File file = new File(path+"/" +tipo + num+ ".jpg" ); //La clase file tiene 3 constructores  File (String path).  Crea una nueva instancia de tipo file  convirtiendo la cadena de nombre de ruta dada en una ruta de acceso abstracta.
                       
      
    InputStream input = filePart.getInputStream(); //Obtener el contenido de la parte en un inputStream
        Files.copy(input, file.toPath(),StandardCopyOption.REPLACE_EXISTING );
        String  fotod=(""+tipo+num+"");
     
     int res=0;
     Usuario userA = new Usuario();
     res=userA.AltaUR(Correo, nombre, contra, tipo, fotod );
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
"  type: 'correct',\n" +
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
