
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
    int inscritos;
    public Curso(String nombre, String descripcion, Usuario user)
    {
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
        inscritos=0;
        buscarTemas();
       }
       catch(Exception e)
       {
           id_curso=-1;
           System.out.println("Error en Creacion Cursos " + e);
           return;
       }
    }
    public Curso(int id, String nombre, int inscritos,String descripcion)
    {
        this.id_curso=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.inscritos=inscritos;
        buscarTemas();
    }
    public boolean buscarTemas()
    {
       Statement sta =null;
       String sql=null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       temas= new ArrayList<Tema>();
       try{
           //obteniendo temas del curso
        sta=con.createStatement();
        System.out.println("select * from tema where idcurso="+id_curso+";");
        resul=sta.executeQuery("select * from tema where idcurso="+id_curso+";");
        System.out.println("hola2");
        while(resul.next())
        {
            Tema aux = new Tema(resul.getInt("idt"),resul.getNString("nombre"),resul.getNString("texto"),resul.getInt("idcurso"),resul.getNString("recurso"));
            temas.add(aux);
        }
        return true;
       }
       catch(Exception e)
       {
           System.out.println("Error en Busqueda Temas " + e);
           return false;
       }
    }
    public Tema agregarTema (String nombre,String informacion)
    {
        Tema salida=null;
        salida = new Tema(nombre,informacion,this.id_curso);
        if(salida!=null)
            temas.add(salida);
        return salida;
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