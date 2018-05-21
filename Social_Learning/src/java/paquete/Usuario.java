/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
//import Conexion_Base.java;

/**
 *
 * @author Marcus
 */
public class Usuario {
   int id;
   String correo;
   String pass;
   String nombre;
   String tipo;
   Conexion_Base conexion;
   String foto;
   ArrayList<Curso> cursos;
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public Usuario() {

    }



   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public ArrayList<Curso> getCursos() {
        return cursos;
    }
    public int  loginR( String correos , String passs) throws SQLException{
        int res=0;
        conexion = new Conexion_Base();
        Connection con=conexion.getConnection();
           Statement st = null  ;
            ResultSet result = null;

        try
        {
         st = con.createStatement();
             result = st.executeQuery("select * from usuario where correo ='"+correos+"' and pass ='"+passs+"';");
            if(result.next())
            {
                res=1;
                this.nombre=result.getString("nombre");
                this.tipo=result.getString("tipo");
                this.id=result.getInt("idusuario");
                this.correo=correos;
                this.pass=passs;
                this.foto=result.getString("img");
            }
        }
        catch(SQLException e)
        {
            System.out.println("e");
        }
        con.close();
        st.close();
        result.close();


        return res;
    }
    public int AltaUR (String correos , String nombres , String contra ,String type )
    {
        int res=0;
        conexion = new Conexion_Base();
        Connection con = conexion.getConnection();
        Statement st=null;
        ResultSet resul=null;

        try
        {
            st=con.createStatement();
           resul=st.executeQuery("select max(idusuario) from usuario;");
           if (resul.next()){
               int ids  =resul.getInt(1);
               ids=ids+1;
               this.id=ids;
            res = st.executeUpdate("insert into usuario values('"+ids+"','" + correos+ "','" +contra+"','" + nombres+"','"+type+"','"+ids+"' , 'no'  );");

           }
        }
        catch(SQLException e)
        {
            System.out.println("" + e);
        }

        return res;
    }
    public int EditPR (String correos , String nombres , int ids, String conN , String conN2 , String contra) throws SQLException
    {
    int res=0;
    conexion  = new Conexion_Base();
    Connection con =conexion.getConnection();
    Statement st = null  ;
    String sql;
    if(!conN.equalsIgnoreCase("") && conN.equals(conN2))
    {

          sql="Update usuario set nombre='"+nombres+"',  correo='"+ correos+"' , pass='"+conN+"' where idusuario='"+ids+"' and pass='"+contra+"';";
    }
    else
    {
         sql="Update usuario set nombre='"+nombres+"',  correo='"+ correos+"'  where idusuario='"+ids+"' and pass='"+contra+"';";
    }

        try
        {
         st = con.createStatement();
         res =st.executeUpdate(sql);
         if(res==1)
         {
         this.correo=correos;
         this.nombre=nombres;
         }
        }
        catch(SQLException e)
        {
            System.out.println("e");
        }
        con.close();
     


    return res;
    }
    
    public boolean buscarCursos()
    {
        Statement sta =null;
       String sql=null;
       ResultSet resul=null;
       Conexion_Base conexion = new Conexion_Base(); 
       Connection con = conexion.getConnection();
        cursos= new ArrayList<Curso>();
       try
       {
        sta=con.createStatement();
        System.out.println("hola1");
        resul=sta.executeQuery("select * from curso where idcreador="+id+";");
        System.out.println("hola");
        while(resul.next())
        {
            Curso aux = new Curso(resul.getInt("idcurso"),resul.getNString("nombre"),resul.getInt("numinscritos"),resul.getNString("descripcion"));
            if(aux!=null)
            cursos.add(aux);
        }
        con.close();
        return true;
       }
       catch(Exception e)
       {
           System.out.println("Error en Buqueda de Cursoso"+e);
           return false;
           
       }
        
    }
    public int forgot (String correo , int p) throws SQLException
    {
        int res=0;
        
    conexion  = new Conexion_Base();
    Connection con =conexion.getConnection();
    Statement st = null  ;
     st = con.createStatement();
         res =st.executeUpdate("update usuario set olvidar='"+p+"' where correo='"+correo+"' ");
       con.close();  
        return res;
    }
    public int Recupera (String correo , String pass) throws SQLException
    {
        int res=0;
        conexion  = new Conexion_Base();
    Connection con =conexion.getConnection();
    Statement st = null  ;
     st = con.createStatement();
         res =st.executeUpdate("update usuario set olvidar='no' , pass='"+pass+"' where olvidar='"+correo+"' ");
       con.close();
        return res;
    }


}
