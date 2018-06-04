
package paquete;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;


/**
 *
 * @author betoj
 */
@MultipartConfig
public class NuevoTema extends HttpServlet {  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       HttpSession session= request.getSession();
       Usuario user =(Usuario) session.getAttribute("AlumnoR");
         String nombre=new String(request.getParameter("nombre").getBytes("ISO-8859-1"),"UTF-8");
         String informacion=new String(request.getParameter("info").getBytes("ISO-8859-1"),"UTF-8");
        //botones de cancelar y limpiar
        if(request.getParameter("Cancelar")!=null)
           response.sendRedirect("MenuCreacioCursos");
       else if(request.getParameter("limpiar")!=null)
         response.sendRedirect("NuevoTema.html");
       else
       {
            Curso curso =(Curso) session.getAttribute("CursoH"); 
            Tema nuevo = curso.agregarTema(nombre, informacion);
            user.getId();
             //guardando archivo multimedia
            Part filePart = request.getPart("multimedia"); //Devuelve una parte especifica del request. Part Esta clase representa una pieza o elemento de formulario que se recibió dentro de una multipart/form-datas en solicitud POST.
            if(filePart.getSize()>0)
            {
                String path=request.getRealPath("/RecursosTemas");
                InputStream input = filePart.getInputStream(); //Obtener el contenido de la parte en un inputStream
                File file = new File(path+"/" +"apoyo"+nuevo.getId_tema()+".mp4" );
                Files.copy(input, file.toPath(),StandardCopyOption.REPLACE_EXISTING );
                nuevo.setRecurso("apoyo"+nuevo.getId_tema()+".mp4");
            } 
            // System.out.println("archivo:" + filePart.getSubmittedFileName());
            if(nuevo.getId_tema()!=-1)
            {
                session.setAttribute("CursoH", curso);
                response.sendRedirect("verCurso?id="+user.getIndiceCurso(curso.getId_curso())+"");
            }
            else
               response.sendRedirect("error.html");
        }
    }
}
