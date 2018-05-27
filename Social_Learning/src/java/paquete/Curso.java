
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
    public Curso()
    {
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
    public boolean Eliminar ()
    {
       Statement sta =null;
       Conexion_Base conexion = new Conexion_Base();
       Connection con = conexion.getConnection();
       try{
           //obteniendo temas del curso
        sta=con.createStatement();
        sta.executeUpdate("delete from curso where idcurso="+this.id_curso+"");
        return true;
       }
       catch(Exception e)
       {
           System.out.println("Error en Eliminar Curso " + e);
           return false;
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
    
    public ResultSet buscarCursos(String keywords, String criterio, String orden )
    {
         String query;
        try
        {
            Conexion_Base conexion= new Conexion_Base();
        Connection con=conexion.getConnection();
        ResultSet resul;
        Statement sta=con.createStatement();
        if(keywords.equals("") || criterio.equals("") || orden.equals("") )
            query="select c.*, u.nombre as 'autor' from curso c, usuario u where u.idusuario=c.idcreador;";
        else{ 
            query="select c.*, u.nombre as 'autor' from curso c, usuario u where u.idusuario=c.idcreador and  ";
                    if(criterio.equals("autor"))
                    {
                        query+="u.nombre like '"+keywords+"'";
                    }
                    else if(criterio.equals("curso"))
                    {
                        query+="c.nombre like '%"+keywords+"%'";
                    }
                    else
                    {
                        query+="c.descripcion like '%"+keywords+"%'";
                    }
                    if(orden.equals("alfabetico"))
                            query+=" order by c.nombre;";
                    else
                            query+=" order by c.idcurso desc;";
                    }
                    resul=sta.executeQuery(query);
                    return resul;
        }
        catch(Exception e){
               System.out.println("Error en:"+e);
               return null;
            }
    }

}