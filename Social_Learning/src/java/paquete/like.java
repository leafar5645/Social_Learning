
package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class like {
    //nmms que tenemos que hacer a like
    int like;
    int idPubli;
    int idUsuario;
    
    public like(int idPubli, int idUsuario) 
    {
        Statement sta =null;
       String sql=null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
        sta=con.createStatement();
        resul=sta.executeQuery("select * from megusta where idpubli="+idPubli+" and idusuario="+idUsuario+";"); 
        if(resul.isBeforeFirst())
        {
            this.idPubli=idPubli;
            this.idUsuario=idUsuario;
            like=resul.getInt("megusta");
            sta.close();
        }
        else
        {
         sta=con.createStatement();
        System.out.println("insert into meGusta values ("+idPubli+", "+idUsuario+", 0);");
        sta.executeUpdate("insert into meGusta values ("+idPubli+", "+idUsuario+", 0);");
        this.idPubli=idPubli;
        this.idUsuario=idUsuario;
        like=0;
        sta.close();   
        }
       }
       catch(Exception e)
       {
           System.out.println("Error en Creacion publicacion2 " + e);
       }
    }
    
    public like()
    {
        
    }
    
     public void darLike()
    {
        Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       try
       {
           sta=con.createStatement();
           sta.executeUpdate("update meGusta set megusta="+1+" where idpubli="+idPubli+" and idusuario="+idUsuario+";");
          like=1;
          sta.close();
           
       }
       catch(Exception e)
       {
           System.out.println("Error al dar Like " + e);
       }
    }
     
     public void quitarLike()
    {
        Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       try
       {
           sta=con.createStatement();
           sta.executeUpdate("update meGusta set megusta="+1+" where idpubli="+idPubli+" and idusuario="+idUsuario+";");
          like=0;
           sta.close();
       }
       catch(Exception e)
       {
           System.out.println("Error al dar Like " + e);
       }
    }
    
    
}
