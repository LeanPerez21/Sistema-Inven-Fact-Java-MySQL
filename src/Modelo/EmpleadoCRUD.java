/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConexionBD.Conexion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class EmpleadoCRUD {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarEmpleado(Empleado cl){
        String sql = "INSERT INTO empleados (dni, nombre, telefono, direccion, cargo) VALUES (?,?,?,?,?)";
        try {
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setLong(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getCargo());
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
    
    public void ConsularEmpleado(JComboBox empleado){
        String sql = "SELECT nombre FROM empleados";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
               empleado.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public List ListarEmpleado(){
       List<Empleado> ListaEmple = new ArrayList();
       String sql = "SELECT * FROM empleados";
       try {
           con = (Connection) cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Empleado em = new Empleado();
               em.setId(rs.getInt("id"));
               em.setDni(rs.getInt("dni"));
               em.setNombre(rs.getString("nombre"));
               em.setTelefono(rs.getLong("telefono"));
               em.setDireccion(rs.getString("direccion"));
               em.setCargo(rs.getString("cargo"));
               ListaEmple.add(em);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaEmple;
   }
    
    public boolean EliminarEmpleado(int id){
       String sql = "DELETE FROM empleados WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
    
    public boolean ModificarEmpleado(Empleado cl){
       String sql = "UPDATE empleados SET dni=?, nombre=?, telefono=?, direccion=?, cargo=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, cl.getDni());
           ps.setString(2, cl.getNombre());
           ps.setLong(3, cl.getTelefono());
           ps.setString(4, cl.getDireccion());
           ps.setString(5, cl.getCargo());
           ps.setInt(6, cl.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
    
}
