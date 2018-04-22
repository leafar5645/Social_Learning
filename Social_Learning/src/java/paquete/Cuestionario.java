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
import java.sql.Timestamp;

/**
 *
 * @author Marcus
 */
public class Cuestionario {
    int idC;
    int calif;
    int idA;
    int idT;
     Conexion_Base conexion;
    Timestamp fecha = new Timestamp(System.currentTimeMillis());
    String [][] cuestionario = new String[5][10];
    public int GeneraC (int idt)
    {
        int res=0;
        Pregunta [] pregs = new Pregunta[10];
        int z=0 , w =0, p =0;
        int [] entero = new int [10];
        String [] pregun = new String [5];
        conexion = new Conexion_Base();
        Connection con = conexion.getConnection();
        Statement st=null;
        ResultSet resul=null;
        
        try
        {
            st=con.createStatement();
           resul=st.executeQuery("select max(idpregunta) from usuario where idt='"+idt+"';");
           if (resul.next()){
               int ids  =resul.getInt(1);
               if(ids>=10)
               {
                    p=(int) (Math.random()*ids)+1;
        
        
      
       while(w<10)
       {
           while(z<10)
           {
               if(entero[z]==p)
               {
                   p=(int) (Math.random()* ids)+1;
                   z=-1;
               }
               z=z+1;
           }
           entero[w]=p;
           p=(int) (Math.random()*ids)+1;
          z=0; 
         w=w+1;  
       }
       for (int n =0; n<10 ;n++)
       {
           pregs[n].ObtenerP(idt, entero[n]);
          pregun=pregs[n].getPregunta();
          for(int k=0; k<5 ; k++)
          {
              cuestionario[k][n] =pregun[k];
          }
           
       }
       res=1;
               }
               else
               {
                   return 0;
               }
               }
           
           }
        
        catch(SQLException e)
        {
            System.out.println("" + e);
        }
        return res;
    }
    
}
