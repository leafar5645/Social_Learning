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

public class Examen extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"     <link rel=\"stylesheet\" href=\"assets/css/main.css\">\n" +
"          <script type=\"text/javascript\">\n \n" +
"   var cronometro;\n" +
"    function detenerse()\n" +
"    {\n" +
"        clearInterval(cronometro);\n" +
"    }\n" +
"   function carga()\n" +
"    {\n" +
"       contador_s =0;\n" +
"       s = document.getElementById(\"segundos\");\n" +
"           cronometro = setInterval(\n" +
"            function()\n" +
"       {\n" +
"                 s.innerHTML = contador_s;\n" +
"                contador_s++;\n" +
"                     if(contador_s>=160)\n" +
"                {\n" +
"                    \n" +
"                document.t.submit();\n" +
"                 }\n" +
"        } ,1000);\n" +
"    }\n" +
"   \n" +
"\n" );
             out.println("  " +
"\n" +
"            window.onunload = sale;\n" +
"            var valor;\n" +
"            if (document.cookie) {\n" +
"                galleta = unescape(document.cookie)\n" +
"                galleta = galleta.split(';')\n" +
"                for (m = 0; m < galleta.length; m++) {\n" +
"                    if (galleta[m].split('=')[0] == \"recarga\") {\n" +
"                        valor = galleta[m].split('=')[1]\n" +
"                        break;\n" +
"                    }\n" +
"                }\n" +
"                if (valor == \"sip\") {\n" +
"                    document.cookie = \"recarga=nop\";\n" +
"                    window.onunload = function () {};\n" +
"                    document.location.reload()\n" +
"                } else {\n" +
"                    window.onunload = sale\n" +
"                }\n" +
"            }\n" +
"            function sale() {\n" +
"                document.cookie = \"recarga=sip\"\n" +
"            }\n" +
"        </script>");
                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\"/>\n"+
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"iconos.css\"/>\n");
                        out.println("</head>");
                        out.println("<body class onload=\"carga()\"> <div id=\"wrapper\">");
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
             HttpSession sesion = request.getSession();
        Usuario user = new Usuario();
        user= (Usuario)sesion.getAttribute("AlumnoR");
        Tema t= (Tema) sesion.getAttribute("TemaR");
                          int idt = t.getId_tema();
        int idA=user.getId();

        Cuestionario c = new Cuestionario();
        int r=0;
        try {
            r = c.permitir(idt, idA);
        } catch (SQLException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(r==1)
        {
            response.sendRedirect("MisCursos");
        }
        else
        {
        int p=  c.GeneraC(idt);
        if(p==1){
          String [][] cuestionario = new String[5][10];
          cuestionario=c.getCuestionario();
          sesion.setAttribute("Cuestionario", c);

            out.println("<center>\n" +
"       <h1 class=\"style2\"><font color=\"white\">Examen  </font></h1></center>\n" +
"            <div id=\"wrapper\">\n" +
"            Del tema  \"nombre del tema\"</h1>\n" +
"            <h3> <p>Tiempo transcurrido segundos:  <span id=\"segundos\"> </span></p> </h3>\n" +
"                    <section id=\"main\">\n" +
"            \n" +
"                    <form action=\"califica\" method=\"get\" name=\"t\" id=\"t\">");


             for(int g=0 ; g<10 ; g++)
            {

            out.println(""+g+".-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + cuestionario[0][g]);
            out.println("<br/>");
            out.print(" a)"+ cuestionario[1][g] + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  b)" + cuestionario[2][g] + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  c)" +  cuestionario[3][g] + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  d)" + cuestionario[4][g]);
             out.println("<br/>");
              out.println("<br/>");
            out.println("<input type='text' name='R"+g+"' value=''/>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            }
            out.println(" <input type='submit' value='Califica'/>    </form>\n" +
"                    </section>\n" +
"            </div>\n" +
"       \n" +
"    </body>");
        
        
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
"  text: \"Preguntas insuficientes!\",\n" +
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
