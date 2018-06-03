
package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class Comentario {
    int idComen;
    int idPubli;
    int idUsuario;
    String texto;
    String autor;
    
    public String getTexto()
    {
        return texto;
    }
    
    public int getIdComen()
    {
        return idComen;
    }
    
    public int getIdPubli()
    {
        return idPubli;
    }
    
    public int getIdUsuario()
    {
        return idUsuario;
    }
    
    
    public Comentario(Usuario creador, Publicacion padre, String texto)
    {
          Statement sta =null;
       String sql=null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
        sta=con.createStatement();
        resul=sta.executeQuery("select max(idcomen) from comentarios;");  
        if(resul.next())
           idComen=resul.getInt(1)+1;
        else
            idComen=1;
        sta.executeUpdate("insert into comentarios values ("+idComen+", '"+texto+"', "+creador.getId()+", "+padre.getIdPubli()+");");
        this.idPubli=padre.getIdPubli();
        this.idUsuario=creador.getId();
        this.texto=texto;
        resul.close();
        sta.close();  
        con.close();
       }
       catch(Exception e)
       {
           System.out.println("Error en Creacion de comentario " + e);
       }
        
    }
    public Comentario(int idComen, String texto, int idUsuario, int idPubli)
    {
        this.idComen=idComen;
        this.texto=texto;
        this.idUsuario=idUsuario;
        this.idPubli=idPubli;
    }
        public String getAutor()
    {
        
         Statement sta =null;
         ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       try{
           //obteniendo temas del curso
        sta=con.createStatement();
        resul=sta.executeQuery("select nombre from usuario where idusuario="+idUsuario+";");
        resul.first();
        autor= resul.getNString("nombre");
        resul.close();
        sta.close();  
        con.close();
         return autor;
       }
       catch(Exception e)
       {
           System.out.println("Error en Eliminar Curso " + e);
           return "error";
       }
    }
}
