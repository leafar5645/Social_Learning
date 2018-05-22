/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class BuscarCurso extends HttpServlet {
    Conexion_Base conexion= new Conexion_Base();
    Connection con=conexion.getConnection();
    ResultSet resul=null;
    ResultSet resul2=null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarCurso</title>");
            out.println("</head>");
            out.println("<body>");
           out.println("Criterio de busqueda: <form method='get' action='BuscarCurso'>");
           out.println("Ingrese palabras clave (curso, autor o descripcion)<input type='text' name='palabras' required/>");
           out.println("Seleccione el criterio de busqueda<select name='criterio'>");
           out.println("<option value='autor'>autor</option>");
           out.println("<option value='curso'>curso</option>");
           out.println("<option value='descripcion'>descripcion</option>");
           out.println("</select>");
           out.println("Seleccione el orden<select name='orden'>");
           out.println("<option value='recientes'>más recientes</option>");
           out.println("<option value='alfabetico'>alfabetico</option>");
           out.println("</select>");
           out.println("<input type='submit' value='buscar'>");
           out.println("</form>");
           String palabras=request.getParameter("palabras");
        String criterio=request.getParameter("criterio");
        String orden=request.getParameter("orden");
           try{
               Statement sta=con.createStatement();


                if(palabras==null)
                {

                    resul=sta.executeQuery("select * from curso;");
                    while(resul.next())
                    {
                        Statement sta2=con.createStatement();
                        resul2=sta2.executeQuery("select nombre from usuario where idusuario= "+resul.getInt("idcreador")+";");
                        if(resul2.next())
                        out.println("creador "+resul2.getString("nombre")+"<br>");
                        out.println("nombre del curso "+resul.getString("nombre")+"<br>");
                        out.println("descripcion del curso "+resul.getString("descripcion")+"<br>");
                        out.println(" <li><a href='Inscribir?id="+resul.getInt("idcurso")+"'>Inscribir</a></li>");
                    }//falta modificar el link que manda a los datos del curso

                }
                else
                {
                    String query="select * from curso where ";
                    if(criterio.equals("autor"))
                    {
                        query="select c.* from curso c, usuario u where u.idusuario=c.idcreador and u.nombre like '"+palabras+"'";
                        if(orden.equals("alfabetico"))
                            query+=" order by c.nombre;";
                        else
                            query+=" order by c.idcurso desc;";
                    }
                    else if(criterio.equals("curso"))
                    {
                        query+="nombre like '%"+palabras+"%'";
                    }
                    else
                    {
                        query+="descripcion like '%"+palabras+"%'";
                    }
                    if(!criterio.equals("autor"))
                    {
                        if(orden.equals("alfabetico"))
                            query+=" order by nombre;";
                        else
                            query+=" order by idcurso desc;";
                    }
                    resul=sta.executeQuery(query);
                    if(resul.isBeforeFirst())
                    {
                        while(resul.next())
                        {
                            Statement sta2=con.createStatement();
                            resul2=sta2.executeQuery("select nombre from usuario where idusuario= "+resul.getInt("idcreador")+";");
                            if(resul2.next())
                                out.println("creador: "+resul2.getString("nombre")+"<br>");

                            out.println("nombre del curso: "+resul.getString("nombre")+"<br>");
                            out.println("descripcion del curso: "+resul.getString("descripcion")+"<br>");
                            out.println(" <li><a href='Inscribir?id="+resul.getInt("idcurso")+"'>Inscribir</a></li>");
                        }
                    }
                    else
                        out.println("No hay resultados!!");
                }
           }
            catch(Exception e)
            {
             out.println("Error en:"+e);
            }
            out.println("</body>");
            out.println("</html>");
        }

    }

}
