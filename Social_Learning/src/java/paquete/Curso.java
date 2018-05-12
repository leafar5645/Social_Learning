
package paquete;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author betoj
 */
public class Curso 
{
    private int id_curso;
    private String nombre;
    private String descripcion;
    private ArrayList <Tema> temas;

    public Curso(String nombre, String descripcion, Usuario user)
    {
       temas= new ArrayList<Tema>();
       Statement sta =null;
       String sql=null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
       sta=con.createStatement();
       resul=sta.executeQuery("select max(idcurso) from curso;");
       if(resul.next())
          id_curso=resul.getInt(1)+1;
       sta.executeUpdate("insert into curso values ('"+id_curso+"','"+nombre+"','"+user.getId()+"','0','"+descripcion+"');");
             
       this.nombre=nombre;
       this.descripcion=descripcion;
       }
       catch(Exception e)
       {
           id_curso=-1;
           System.out.println("Error en Creacion Curso " + e);
           return;
       }
       
    }
    public int getId_curso() {
        return id_curso;
    }


    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }
    public ArrayList getTemas(){
        return this.temas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
