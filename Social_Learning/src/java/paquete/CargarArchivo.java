
package paquete;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
@MultipartConfig
public class CargarArchivo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Curso curso =(Curso) session.getAttribute("CursoH"); 
         //guardando archivo multimedia
            Part filePart = request.getPart("multimedia"); //Devuelve una parte especifica del request. Part Esta clase representa una pieza o elemento de formulario que se recibió dentro de una multipart/form-datas en solicitud POST.
            if(filePart.getSize()>0)
            {
                String path=request.getRealPath("/RecursosCursos");
                InputStream input = filePart.getInputStream(); //Obtener el contenido de la parte en un inputStream
                File directorio = new File(path+"/"+curso.getNombre());//ruta del directorio
                if(!directorio.exists())//si no existe lo creamos
                    directorio.mkdirs();
                File file=null;
                int i=0;
                do
                {
                    file = new File(path+"/" +curso.getNombre()+"/"+"("+i+")"+filePart.getSubmittedFileName());
                    i++;
                }
                while(file.exists());
                Files.copy(input, file.toPath(),StandardCopyOption.REPLACE_EXISTING );
            }
            response.sendRedirect("verCurso");
        
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
