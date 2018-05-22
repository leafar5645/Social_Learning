/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author betoj
 */
public class Tema 
{
    private String nombre;
    private int id_tema;
    private String informacion;
    private String recurso;
    
    public Tema(String nombre, String desc,int curso)
    {
       Statement sta =null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
       sta=con.createStatement();
       resul=sta.executeQuery("select max(idt) from tema;");
       if(resul.next())
          id_tema=resul.getInt(1)+1;
       String sql="insert into tema values('"+id_tema+"','"+nombre+"','"+desc+"','"+curso+"','"+0+"');";
       System.out.println(sql);
       conexion = new Conexion_Base(); 
       con = conexion.getConnection();
       sta=con.createStatement();
       sta.executeUpdate("insert into tema (idt,nombre,texto,idcurso,recurso)values('"+id_tema+"','"+nombre+"','"+desc+"','"+curso+"','"+0+"');");
             
        this.nombre=nombre;
        this.informacion=desc;
       }
       catch(Exception e)
       {
           id_tema=-1;
           System.out.println("Error en Creacion Tema " + e);
           return;
       }
    }
    public Tema(int id_tema, String nombre,String desc, int id_curso, String recurso)
    {
         this.nombre=nombre;
        this.informacion=desc;
         this.id_tema=id_tema;
         this.recurso=recurso;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        Statement sta =null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
       sta=con.createStatement();
       String sql="update tema set nombre='"+nombre+"' where idt="+id_tema+";";
       System.out.println(sql);
       sta.executeUpdate(sql);    
        this.nombre=nombre;
       }
       catch(Exception e)
       {
           System.out.println("Error en Modificacion Nombre: " + e);
           return;
       } 
    }
    public void setInformacion(String informacion)
    {
        Statement sta =null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
       sta=con.createStatement();
       String sql="update tema set texto='"+informacion+"' where idt="+id_tema+";";
       System.out.println(sql);
       sta.executeUpdate(sql);    
         this.informacion=informacion;
       }
       catch(Exception e)
       {
           System.out.println("Error en Modificacion Informacion: " + e);
           return;
       } 
       
    }
    public int getId_tema() {
        return id_tema;
    }

    public void setId_tema(int id_tema) {
        this.id_tema = id_tema;
    }
    public String getInformacion() {
        return informacion;
    }
      public String getRecurso() {
        return recurso;
        
    }

    public void setRecurso(String recurso) {
       Statement sta =null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
       try
       {
       sta=con.createStatement();
       System.out.println("UPDATE tema set recurso='"+recurso+"' where idt="+id_tema+";");
       sta.executeUpdate("UPDATE tema set recurso='"+recurso+"' where idt="+id_tema+";");
       
       this.recurso = recurso;
       }
       catch(Exception e)
       {
           System.out.println("Error en Guardar Recurso" + e);
           return;
       }
        
    }
    
    
}
