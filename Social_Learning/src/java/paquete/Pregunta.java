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
import java.util.Random;

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
      int z=0 , w =0, p =0;
       int [] entero = new int [4];
        Connection con = conexion.getConnection();
        Statement st=null;
        ResultSet result=null;
         Random r = new Random();
p = r.nextInt(4)+1;
while(w<4)
       {
           while(z<4)
           {
               if(entero[z]==p)
               {
                   p= r.nextInt(4)+1;
                   z=-1;
               }
               z=z+1;
           }
           entero[w]=p;
           p= r.nextInt(4)+1;
          z=0; 
         w=w+1;  
       }
         try
        {
         st = con.createStatement();
             result = st.executeQuery("select * from pregunta  where idpregunta ='"+IDp+"' and idt ='"+IDt+"';");
            if(result.next())
            {
             this.idP=IDp;
             this.idT=IDt;
             this.pregunta[0]=result.getString("pregunta");
             this.pregunta[entero[0]]=result.getString("respuesta");
             this.pregunta[entero[1]]=result.getString("a");
             this.pregunta[entero[2]]=result.getString("b");
             this.pregunta[entero[3]]=result.getString("c");
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
    public int insertPregunta(int idt , String pregunta , String respuesta, String a , String b , String c ) throws SQLException
    {
        int res=0;
        int idp=0;
         conexion = new Conexion_Base();
      int z=0 , w =0, p =0;
       int [] entero = new int [4];
        Connection con = conexion.getConnection();
        Statement st=null;
             ResultSet resul=null;
        st=con.createStatement();
           resul=st.executeQuery("select max(idpregunta) from usuario where idt='"+idt+"';");
           if (resul.next()){
                idp  =resul.getInt(1);
               idp=idp+1;
               
            res= st.executeUpdate("insert into usuario values('"+idp+"','" +idt+ "','" +pregunta+"','" + respuesta+"','"+a+"','"+b+"' , '"+c+"'  );");

           }

        
        
        return res;
    }
    
}

