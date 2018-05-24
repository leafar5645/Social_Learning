
package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Foro {
    private ArrayList <Publicacion> publicaciones;
    int idCurso;
    int idUsuario;
    
    public Foro(int idCurso, int idUsuario){
        this.idCurso=idCurso;
        this.idUsuario=idUsuario;
    }
    public int getIdCurso(){
        return idCurso;
    }
    public void setIdCurso(int idCurso){
        this.idCurso=idCurso;
    }
    public int getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario){
        this.idUsuario=idUsuario;
    }
    
    public ArrayList getPublicaciones(){
        return publicaciones;
    }
    
    public boolean buscarPublicaciones()
    {
        Statement sta =null;
       String sql=null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       publicaciones= new ArrayList<Publicacion>();
       try{
           //obteniendo publicaciones para el foro
        sta=con.createStatement();
        System.out.println("select * from publicacion where idcurso="+idCurso+";");
        resul=sta.executeQuery("select * from publicacion where idcurso="+idCurso+";");
        System.out.println("hola2");
        if(resul.isBeforeFirst())
        {
            while(resul.next())
            {
                Publicacion aux = new Publicacion(resul.getInt("idpubli"), resul.getInt("idcurso"), resul.getInt("idusuario"), resul.getInt("likes"), resul.getNString("contenido"), resul.getNString("mediaUrl"), resul.getInt("validacion"));
                publicaciones.add(aux);
            }
            return true;
        }
        else 
            return false;
       }
       catch(Exception e)
       {
           System.out.println("Error en Busqueda Temas " + e);
           return false;
       }
    }
}
