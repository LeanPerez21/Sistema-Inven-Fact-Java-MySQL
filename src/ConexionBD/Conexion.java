package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class Conexion {

    private Connection con;
    
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="";
    private static final String url="jdbc:mysql://localhost:3306/sistemainventariodb";
    
     public Connection getConnection() {
        con=null;
        try{
            Class.forName(driver);
            con= (Connection) DriverManager.getConnection(url, user, pass);
            if (con!=null){
//                JOptionPane.showMessageDialog(null,"Se establecio la conexion a la base de datos");
            }
        }
        catch (ClassNotFoundException | SQLException e){
//            JOptionPane.showMessageDialog(null,"No se establecio la conexion a la base de datos" + e);
        }
        return con;
    }
     
//     public ResultSet consultar(String sql) {
//         ResultSet resultado;
//         try {
//             Statement sentencia;
//             sentencia = conexion().createStatement();
//             resultado = sentencia.executeQuery(sql);
//         } catch (SQLException e) {
//             JOptionPane.showMessageDialog(null, "Error sql consultar" + e);
//             return null;
//         }  
//         
//         return resultado;
//     }


}
    

