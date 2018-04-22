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
public class Pregunta {
    int  idP;
    int idT;
    String []  pregunta= new String [5];
    Conexion_Base conexion;

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public String[] getPregunta() {
        return pregunta;
    }

    public void setPregunta(String[] pregunta) {
        this.pregunta = pregunta;
    }

    public void ObtenerP (int IDt , int IDp) throws SQLException   //puede necesitar booleano para desplegar error en caso de que exista
    {
        
      conexion = new Conexion_Base();
        Connection con = conexion.getConnection();
        Statement st=null;
        ResultSet result=null;  
         try
        {
         st = con.createStatement();
             result = st.executeQuery("select * from pregunta  where idpregunta ='"+IDp+"' and idt ='"+IDt+"';");
            if(result.next())
            {
             this.idP=IDp;
             this.idT=IDt;
             this.pregunta[0]=result.getString("pregunta");
             this.pregunta[1]=result.getString("respuesta");
             this.pregunta[2]=result.getString("a");
             this.pregunta[3]=result.getString("b");
             this.pregunta[4]=result.getString("c");
                         }
        }
        catch(SQLException e)
        {
            System.out.println("e");
        }
        con.close();
        st.close();
        result.close();
        
    } 
    
}
