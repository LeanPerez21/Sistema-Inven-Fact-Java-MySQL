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
import javax.swing.JComboBox;

/**
 *
 * @author Leandro
 */
public class ProductoCRUD {
    Conexion cn = new Conexion();
    com.mysql.jdbc.Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Producto pro){
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, stock, precio, descripcion) VALUES (?,?,?,?,?,?)";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.setString(6, pro.getDescripcion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public void ConsularProveedor(JComboBox proveedor){
        String sql = "SELECT nombre FROM proveedores";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
               proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public List ListarProductos(){
       List<Producto> Listapro = new ArrayList();
       String sql = "SELECT * FROM productos";
       try {
           con = (com.mysql.jdbc.Connection) cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Producto pro = new Producto();
               pro.setId(rs.getInt("id"));
               pro.setCodigo(rs.getString("codigo"));
               pro.setNombre(rs.getString("nombre"));
               pro.setProveedor(rs.getString("proveedor"));
               pro.setStock(rs.getInt("stock"));
               pro.setPrecio(rs.getDouble("precio"));
               pro.setDescripcion(rs.getString("descripcion"));
               Listapro.add(pro);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return Listapro;
   }
    
    public boolean EliminarProductos(int id){
       String sql = "DELETE FROM productos WHERE id = ?";
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
    
    public boolean ModificarProductos(Producto pro){
       String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, stock=?, precio=?, descripcion=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, pro.getCodigo());
           ps.setString(2, pro.getNombre());
           ps.setString(3, pro.getProveedor());
           ps.setInt(4, pro.getStock());
           ps.setDouble(5, pro.getPrecio());
           ps.setString(6, pro.getDescripcion());
           ps.setInt(7, pro.getId());
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
    
    public Producto BuscarPro(String cod){
        Producto producto = new Producto();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            con = (com.mysql.jdbc.Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
}
