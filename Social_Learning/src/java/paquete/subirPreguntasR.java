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
public class subirPreguntasR extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    if (request.getParameter("subirp").equalsIgnoreCase("registar pregunta") )
    {
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset=\"UTF-8\">\n" +
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
        "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
      out.println("</head>");
      out.println("<body class> <div id=\"wrapper\">");
      out.println("<header>\n"+
        "<nav>\n"+
        "                        <ul>\n"+
        "                        <li><a href=\"subMenuR\"><span><i class=\"icon-home\"></i></span>Pefil</a></li>\n"+
        "                        <li><a href=\"MisCursos\"><span><i class=\"icon-briefcase\"></i></span>Mis Cursos</a></li>\n"+
        "                        <li><a href=\"BuscarCurso\"><span><i class=\"icon-search\"></i></span>Explorador</a></li>\n"+
        "                        <li><a href=\"logout\"><span><i class=\"icon-exit\"></i></span>Log Out</a></li>\n"+
        "                    </ul>\n"+
        "                </nav>\n"+
        "</header>");
      out.println("<h1>Introduce los datos para subir tus preguntas</h1> <section id=\"main\"> <br/> <br/> <form action='subirPreguntasR' method='get'>  ");
      out.println("<input type='text' name='pregunta' placeholder='pregunta' required/> <br/> "
        + "<input type='text' name='respuesta' placeholder='Respuesta Correcta' /><br/> <input type='text' name='a' placeholder='opcion 1'/> "
        + "<br/> <input type='text' name='b' placeholder='opcion 2'/> <br/> <input type='text' name='c' placeholder='opcion 3' /> <br/>"
        + "<input type='submit' name='subirp' value='subir' ");
      out.println("</section </div> </body>");
      out.println("</html>");
      out.println("</html>");
    }
    else
    {
      if (request.getParameter("subirp").equalsIgnoreCase("subir"))
      {
        HttpSession sesion = request.getSession();
        Tema t= (Tema) sesion.getAttribute("TemaR");
        int id = t.getId_tema();
        int res=0;
        String pregunta=request.getParameter("pregunta");
        String respuesta=request.getParameter("respuesta");
        String a= request.getParameter("a");
        String b= request.getParameter("b");
        String c = request.getParameter("c");
        Pregunta p= new Pregunta();
        try {
          res=p.insertPregunta(id, pregunta, respuesta, a, b, c);
        } catch (SQLException ex) {
          Logger.getLogger(subirPreguntasR.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            "  type: 'success',\n" +
            "  showCancelButton: false,\n" +
            "  confirmButtonColor: '#d33',\n" +
            "  cancelButtonColor: '#d33',\n" +
            "  confirmButtonText: 'OK'\n" +
            "}).then(function (result) {\n" +
            "  if (result.value) {\n" +
            "   window.location.href=\"MisCursos\";"+
            "  }else{ window.location.href=\"MisCursos\";}\n" +
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
            "   window.location.href=\"MisCursos\";"+
            "  }else{ window.location.href=\"MisCursos\";}\n" +
            "})");

          out.println("</script>");

          out.println("</body>");

          out.println("</html>");

        }
      }
    }
  }
}





