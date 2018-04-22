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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
   <body onload="carga()">
        <h1>Examen <br/> 
            Del tema <% out.println(idt);%></h1>
            <h3> <p>Tiempo transcurrido segundos:  <span id="segundos"> </span></p> <h3>
            
                    <form action="califica" method="get" name="t" id="t">
                        
                
            </form>
       
    </body>
</html>
<%
    }
%>