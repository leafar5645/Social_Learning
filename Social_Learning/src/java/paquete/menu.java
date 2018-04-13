/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//hola esto es un experimento
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcus
 */
public class menu extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   response.setContentType("text/html;charset=UTF-8");
   
        PrintWriter out = response.getWriter();
       
        out.println("  <script type=\"text/javascript\">\n" +
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
        
           HttpSession sesion = request.getSession();
           Usuario user = (Usuario) sesion.getAttribute("AlumnoR");
           if(user==null)
           {
                 out.println("<script>window.parent.location='index.html'</script>");
           }
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet menu</title>");            
            out.println("</head>");
            out.println("<frameset cols='75%,*' name='logout'>");
           
            out.println("<frame src='subMenuR' name='objetivo'>");
             out.println("<frame src='barraR'>");
            out.println("</html>");
    


}
}