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
    int validacion;
     private ArrayList <Comentario> comentarios;
            String autor="hola";
    
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
        resul=sta.executeQuery("select max(idpubli) from publicacion;");  
        if(resul.next())
           idPubli=resul.getInt(1)+1;
        else
            idPubli=1;
        sta.executeUpdate("insert into publicacion (idpubli, idcurso, idusuario, likes, contenido, validacion) values ("+idPubli+", "+curso.getId_curso()+", "+user.getId()+", 0, '"+contenido+"', 0);");
        this.contenido=contenido;
        this.idCurso=curso.getId_curso();
        this.idUsuario=user.getId();
        likes=0;
        validacion=0;
        mediaURL=null;
       }
       catch(Exception e)
       {
           System.out.println("Error en Creacion publicacion " + e);
       }
    }
    
    public Publicacion(int idPubli, int idCurso, int idUsuario, int likes, String contenido, String mediaURL, int validacion)
    {
        this.idPubli=idPubli;
        this.idUsuario=idUsuario;
        this.idCurso=idCurso;
        this.likes=likes;
        this.contenido=contenido;
        this.mediaURL=mediaURL;
        this.validacion=validacion;   
    }
    public Publicacion(int idPubli)
    {
         Statement sta =null;
         ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       try{
           //obteniendo temas del curso
        sta=con.createStatement();
        resul=sta.executeQuery("select * from publicacion where idpubli="+idPubli+";");
        if(resul.next())
            {
                this.idPubli=idPubli;
                this.idUsuario=resul.getInt("idusuario");
                this.idCurso=resul.getInt("idcurso");
                this.contenido=resul.getString("contenido");
                this.likes=resul.getInt("likes");
                this.mediaURL=resul.getString("mediaUrl");
                this.validacion=resul.getInt("validacion");

            }
       }
       catch(Exception e)
       {
           System.out.println("Error en Obtener la publicacion " + e);
       }
        
    }
    
    public boolean Eliminar()
    {
        Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       try{
           //obteniendo temas del curso
        sta=con.createStatement();
        sta.executeUpdate("delete from publicacion where idpubli="+idPubli+"");
        return true;
       }
       catch(Exception e)
       {
           System.out.println("Error en Eliminar Curso " + e);
           return false;
       }
    }
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
    public int getIdUsuario()
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
    public int getValidacion()
    {
        return validacion;
    }
    public void setValidacion(int validacion)
    {
        this.validacion=validacion;
    }
    
    public void darLike()
    {
        Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       try
       {
           sta=con.createStatement();
           sta.executeUpdate("update publicacion set likes="+(likes+1)+" where idpubli="+idPubli+";");
           
       }
       catch(Exception e)
       {
           System.out.println("Error al dar Like " + e);
       }
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
         return autor;
       }
       catch(Exception e)
       {
           System.out.println("Error en Eliminar Curso " + e);
           return "error";
       }
    }
    
    public Boolean buscarComentarios()
    {
        Statement sta =null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       comentarios=new ArrayList<Comentario>();
       try{
           //obteniendo publicaciones para el foro
        sta=con.createStatement();
        resul=sta.executeQuery("select * from comentarios where idpubli="+idPubli+";");
           System.out.println("está buscando en:"+idPubli);
        if(resul.isBeforeFirst())
        {
            while(resul.next())
            {
                Comentario aux = new Comentario(resul.getInt("idcomen"), resul.getString("texto"), resul.getInt("idusuario"), resul.getInt("idpubli"));
                comentarios.add(aux);
            }
            return true;
        }
        else 
            return false;
       }
       catch(Exception e)
       {
           System.out.println("Error en buscar comentarios " + e);
           return false;
       }
    }
    
    public ArrayList getComentarios()
    {
        return comentarios;
    }
    
}
