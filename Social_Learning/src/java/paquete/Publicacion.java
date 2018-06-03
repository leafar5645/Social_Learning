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
    String contenido;
    String mediaURL;
    int validacion;
    like Like;
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
        sta.executeUpdate("insert into publicacion (idpubli, idcurso, idusuario, contenido, validacion) values ("+idPubli+", "+curso.getId_curso()+", "+user.getId()+", '"+contenido+"', 0);");
        this.contenido=contenido;
        this.idCurso=curso.getId_curso();
        this.idUsuario=user.getId();
        validacion=0;
        mediaURL=null;
        resul.close();
        sta.close();  
        con.close();
       }
       catch(Exception e)
       {
           System.out.println("Error en Creacion publicacion2 " + e);
       }
    }
    
    public Publicacion(int idPubli, int idCurso, int idUsuario,String contenido, String mediaURL, int validacion)
    {
        this.idPubli=idPubli;
        this.idUsuario=idUsuario;
        this.idCurso=idCurso;
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
                this.mediaURL=resul.getString("mediaUrl");
                this.validacion=resul.getInt("validacion");

            }
        resul.close();
        sta.close();  
        con.close();
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
        sta.close();  
        con.close();
        return true;
        
       }
       catch(Exception e)
       {
           System.out.println("Error en Eliminar Curso " + e);
           return false;
       }
    }
    
    public int getLikes() 
    {
       Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       ResultSet resul=null;
       try{
           //obteniendo temas del curso
        sta=con.createStatement();
        resul=sta.executeQuery("select * from megusta where idpubli="+idPubli+" and megusta=1;");
        resul.last();
        int valor=resul.getRow();
        resul.close();
        sta.close();  
        con.close();
            return valor;
       }
       
       catch(Exception e)
       {
           System.out.println("Error en Eliminar Curso " + e);
           return 0;
       }
    }
    
    public boolean estadoLike(int idUsuario)
    {
       Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       ResultSet resul=null;
       try{
           //se desea saber si un alumno ya dío like a la publicación
        sta=con.createStatement();
        resul=sta.executeQuery("select * from megusta where idpubli="+idPubli+" and idusuario="+idUsuario+";");
        if(resul.next())
        {
            int estado=resul.getInt("megusta");
            resul.close();
        sta.close();  
        con.close();
            if(estado==1)
                return false;
            else 
                return true;
        }
        resul.close();
        sta.close();  
        con.close();
        return true;
            
       }
       catch(Exception e)
       {
           System.out.println("Error en Estado like " + e);
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
    public void setValidacion()
    {
        Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       ResultSet resul=null;
       try
       {
          sta=con.createStatement();
          resul=sta.executeQuery("select * from publicacion where idpubli="+idPubli+";");
          resul.next();
          int val=resul.getInt("validacion");
          resul.close();
          sta.close();
              sta=con.createStatement();
          if(val==0)
          {
          sta.executeUpdate("update publicacion set validacion="+1+" where idpubli="+idPubli+";");
          validacion=1;
          }
          else
          {
              sta.executeUpdate("update publicacion set validacion="+0+" where idpubli="+idPubli+";");
              validacion=0;
          }
          sta.close();
          con.close();
             
       }
       catch(Exception e)
       {
           System.out.println("Error al validar " + e);
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
        if(resul.isBeforeFirst())
        {
            while(resul.next())
            {
                Comentario aux = new Comentario(resul.getInt("idcomen"), resul.getString("texto"), resul.getInt("idusuario"), resul.getInt("idpubli"));
                comentarios.add(aux);
            }
            sta.close();
            return true;
        }
        else 
        {
            sta.close();
            return false;
        }
            
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
