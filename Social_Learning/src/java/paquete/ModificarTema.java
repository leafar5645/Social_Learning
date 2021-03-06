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
 * @author betoj
 */
@MultipartConfig
public class ModificarTema extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       HttpSession session= request.getSession();
        Curso curso =(Curso) session.getAttribute("CursoH");
        Tema tema = (Tema)session.getAttribute("TemaR");
        String sidcurso=new String(request.getParameter("idcurso").getBytes("ISO-8859-1"),"UTF-8");
        int idcurso =Integer.parseInt(sidcurso);
        //botones de cancelar y limpiar
        if(request.getParameter("Cancelar")!=null)
           response.sendRedirect("verCurso?id="+idcurso+"");
        else if(request.getParameter("Eliminar")!=null)
        {
            if(!tema.Eliminar())
                response.sendRedirect("error.html");
            session.setAttribute("TemaR", null);
            response.sendRedirect("verCurso?id="+idcurso+"");
        }
        else
        {
           String nombre=new String(request.getParameter("NombreTema").getBytes("ISO-8859-1"),"UTF-8");
           String info=new String(request.getParameter("info").getBytes("ISO-8859-1"),"UTF-8");
           tema.setNombre(nombre);
           tema.setInformacion(info);
           Part filePart = request.getPart("multimedia"); //Devuelve una parte especifica del request. Part Esta clase representa una pieza o elemento de formulario que se recibió dentro de una multipart/form-datas en solicitud POST.
            if(filePart.getSize()>0)
            {
                if(!tema.getRecurso().equalsIgnoreCase("0"))
                {
                String path=request.getRealPath("/RecursosTemas");
                InputStream input = filePart.getInputStream(); //Obtener el contenido de la parte en un inputStream
                File file = new File(path+"/" +tema.getRecurso());
                Files.copy(input, file.toPath(),StandardCopyOption.REPLACE_EXISTING );
            
                }
                else
                {
                    String path=request.getRealPath("/RecursosTemas");
                InputStream input = filePart.getInputStream(); //Obtener el contenido de la parte en un inputStream
                File file = new File(path+"/" +"apoyo"+tema.getId_tema()+".mp4" );
                Files.copy(input, file.toPath(),StandardCopyOption.REPLACE_EXISTING );
                tema.setRecurso("apoyo"+tema.getId_tema()+".mp4");
                }
            }
             response.sendRedirect("verCurso?id="+idcurso+"");
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
    }// </editor-fold>

}
