package paquete;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Mau
 */
public class Publicacion {
    int idPubli;
    int idCurso;
    int idUsuario;
    int likes;
    String contenido;
    String mediaURL;
    boolean validacion;
    
    public int getIdPubli()
    {
        return idPubli;
    }
    public void setIdPubli(int idPubli)
    {
        this.idPubli=idPubli;
    }
    public int getIdCurso()
    {
        return idCurso;
    }
    public void setIdCurso(int idCurso)
    {
        this.idCurso=idCurso;
    }
    public int getIdUsusario()
    {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario)
    {
        this.idUsuario=idUsuario;
    }
    public int getLikes()
    {
        return likes;
    }
    public void setLikes(int likes)
    {
        this.likes=likes;
    }
    public String getContenido()
    {
        return contenido;
    }
    public void setContenido(String contenido)
    {
        this.contenido=contenido;
    }
    public String getMediaURL()
    {
        return mediaURL;
    }
    public void setMediaURL(String mediaURL)
    {
        this.mediaURL=mediaURL;
    }
    public boolean getValidacion()
    {
        return validacion;
    }
    public void setValidacion(boolean validacion)
    {
        this.validacion=validacion;
    }
    public Publicacion(String contenido,Usuario user, Curso curso) //se ha agregado una nueva publicacion
    {
        Statement sta =null;
       String sql=null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
        sta=con.createStatement();
        resul=sta.executeQuery("select max(idcurso) from publicacion;");  
        if(resul.next())
           idPubli=resul.getInt(1)+1;
        sta.executeUpdate("insert into publicacion (idpubli, idcurso, idusuario,likes, contenido, validacion) values ('"+idPubli+"','"+curso.getId_curso()+"','"+user.getId()+"','0','"+contenido+"'0');");
        this.contenido=contenido;
        this.idCurso=curso.getId_curso();
        this.idUsuario=user.getId();
        likes=0;
        validacion=false;
        mediaURL=null;
       }
       catch(Exception e)
       {
           System.out.println("Error en Creacion publicacion " + e);
       }
    }
    
}