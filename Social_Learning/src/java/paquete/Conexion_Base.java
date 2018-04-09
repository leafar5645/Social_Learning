

package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion_Base {
    public static String servidor="jdbc:mysql://localhost/proyecto_adoo";
    public static String usuario="root";
    public static String contra="root";
    public static String driver="com.mysql.jdbc.Driver";
    public static Connection conexion;
    public Conexion_Base(){
        try{
            
            Class.forName(driver);
            conexion=DriverManager.getConnection(servidor, usuario, contra);
           
        }
        catch( ClassNotFoundException | SQLException e)
                {
                    System.out.println("" +e);
                }
    }
    public Connection getConnection(){
        return conexion;
    }
    
    
}
