<%-- 
    Document   : Examen
    Created on : 22/04/2018, 02:41:20 PM
    Author     : Marcus
--%>

<%@page import="paquete.Usuario"%>
<%@page import="paquete.Cuestionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
   <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="assets/css/main.css">
          <script> 
   var cronometro;
    function detenerse()
    {
        clearInterval(cronometro);
    }
   function carga()
    {
       contador_s =0;
       s = document.getElementById("segundos");
           cronometro = setInterval(
            function()
       {
                 s.innerHTML = contador_s;
                contador_s++;
                     if(contador_s>=160)
                {
                    
                document.t.submit();
                 }
        } ,1000);
    }
   

    </script>
     </head>
       <%
        HttpSession sesion = request.getSession();
        Usuario user = new Usuario();
        user= (Usuario)sesion.getAttribute("AlumnoR");
        int idA=user.getId();
        int idt=Integer.parseInt(request.getParameter("tema"));
        Cuestionario c = new Cuestionario();
        int r = c.permitir(idt, idA);
        if(r==1)
        {
            response.sendRedirect("index.html");
        }
        else
        {
          c.GeneraC(idt);
          String [][] cuestionario = new String[5][10];
          cuestionario=c.getCuestionario(); 
        %>
          
    
   <body class onload="carga()" >
   <center>
       <h1 class="style2"><font color="white">Examen  </font></h1></center>
            <div id="wrapper">
            Del tema  "nombre del tema"</h1>
            <h3> <p>Tiempo transcurrido segundos:  <span id="segundos"> </span></p> </h3>
                    <section id="main">
            
                    <form action="califica" method="get" name="t" id="t">
                       <% 
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
                           
                           
           
                
                
            %>
                
                    </form>
                    </section>
            </div>
       
    </body>
    <%
        }
        %>
</html>
