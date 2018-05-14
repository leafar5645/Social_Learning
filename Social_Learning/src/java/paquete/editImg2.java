/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Marcus
 */
@MultipartConfig(maxFileSize = 8177215) 
public class editImg2 extends HttpServlet {

   
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  HttpSession sesion = request.getSession();
Usuario a = (Usuario) sesion.getAttribute("AlumnoR");
         Part filePart = request.getPart("foto"); //Devuelve una parte especifica del request. Part Esta clase representa una pieza o elemento de formulario que se recibió dentro de una multipart/form-datas en solicitud POST.
     
   String path=request.getRealPath("/UsuariosFotosR");
    File file = new File(path+"/" + a.getId()+ ".jpg" ); //La clase file tiene 3 constructores  File (String path).  Crea una nueva instancia de tipo file  convirtiendo la cadena de nombre de ruta dada en una ruta de acceso abstracta.
                       
      
    InputStream input = filePart.getInputStream(); //Obtener el contenido de la parte en un inputStream
        Files.copy(input, file.toPath(),StandardCopyOption.REPLACE_EXISTING );
       
        response.sendRedirect("subMenuR");
   
    }

    

}
