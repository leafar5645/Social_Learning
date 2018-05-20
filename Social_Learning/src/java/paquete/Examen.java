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
"          <script> \n" +
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
"\n" +
"    </script>");            
           out.println("</head>");
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
          c.GeneraC(idt);
          String [][] cuestionario = new String[5][10];
          cuestionario=c.getCuestionario(); 
          sesion.setAttribute("Cuestionario", c);
          
            out.println(" <body class onload=\"carga()\" >\n" +
"   <center>\n" +
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
        }
            out.println("</html>");
    
    }

   
}
