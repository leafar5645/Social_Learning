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
    public int  loginR( String correos , String passs) throws SQLException{
        int res=0;
        conexion = new Conexion_Base();
        Connection con=conexion.getConnection();
           Statement st = null  ;
            ResultSet result = null;
            System.out.println("algo pasa");
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
  
    
}
