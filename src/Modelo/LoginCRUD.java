/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConexionBD.Conexion;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class LoginCRUD {
    
    Conexion cn = new Conexion();
    Connection con;
    java.sql.PreparedStatement ps;
    ResultSet rs;
    
    public boolean agregarUsuarios(Login cl){
        String sql = "INSERT INTO usuarios (usuario, mail, pass) VALUES (?,?,?)";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getUsuario());
            ps.setString(2, cl.getMail());
            ps.setString(3, cl.getPass());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void validarAcceso(Login cl){
        
    }
    
}
