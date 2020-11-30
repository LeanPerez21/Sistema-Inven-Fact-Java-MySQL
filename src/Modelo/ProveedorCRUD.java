/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ConexionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class ProveedorCRUD {
    Conexion cn = new Conexion();
    com.mysql.jdbc.Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedores( nombre, telefono, direccion, mail, categoria) VALUES (?,?,?,?,?)";
        try {
           con = (com.mysql.jdbc.Connection) cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, pr.getNombre());
           ps.setLong(2, pr.getTelefono());
           ps.setString(3, pr.getDireccion());
           ps.setString(4, pr.getMail());
           ps.setString(5, pr.getCategoria());
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
    
    public List ListarProveedor(){
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedores";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getLong("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setMail(rs.getString("mail"));
                pr.setCategoria(rs.getString("categoria"));
                Listapr.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }
    
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM proveedores WHERE id = ? ";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
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
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor pr){
        String sql = "UPDATE proveedores SET  nombre=?, telefono=?, direccion=?, mail=?, categoria=? WHERE id=?";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNombre());
            ps.setLong(2, pr.getTelefono());
            ps.setString(3, pr.getDireccion());
            ps.setString(4, pr.getMail());
            ps.setString(5, pr.getCategoria());
            ps.setInt(6, pr.getId());
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
