/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controldor;

import ec.edu.ups.modelo.Libro;
import ec.edu.ups.persistencia.BDConexcion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author BrymOzl
 */
public class ControladorLibro {
    
    public void crearPais(Libro datos) {
        try {
            String sql = "INSERT INTO libro VALUES (?,?,?,?,?)";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(sql);
            sta.setInt(1, datos.getCodigo_P());
            sta.setString(2, datos.getTitulo());
            sta.setString(3, datos.getAutor());
            sta.setString(4, datos.getEdicion());
            sta.setDouble(5, datos.getPrecio());
            sta.executeUpdate();
            sta.close();
            BDConexcion.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al insertar en la base de datos: " + ex.getMessage());
        }
    }
    
    public void actualizarPais(Libro datosNuevos) {
        try {
            String actualizar = "UPDATE libro SET lib_titulo = ?  WHERE lib_id = ?";
            BDConexcion.conectar();//conecta
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(actualizar);
            sta.setString(1, datosNuevos.getTitulo());
            sta.setInt(2, datosNuevos.getCodigo_P());
            sta.executeUpdate();//Ejecutar las lineas en la base
            sta.close();
            BDConexcion.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
    
    public void eliminarPais(Libro datos) {
        try {
            String eliminar = "DELETE FROM libro WHERE lib_id = ? ;";
            BDConexcion.conectar();//conecta
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(eliminar);//devuelve la coneccion y manda el string
            sta.setInt(1, datos.getCodigo_P());
            sta.executeUpdate();//ejecutar
            sta.close();//terminar el ejecutado
            BDConexcion.desconectar();//desconectar
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        }
    }
    
    public List<Libro> listarPais() {
        List<Libro> lista = new ArrayList<>();
        try {
            String listar = "SELECT * FROM libro ORDER BY lib_id ASC";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(listar);
            ResultSet res = sta.executeQuery(); //me devuleve y almacena en el result resultset este es el execute
            while (res.next()) {
                Libro datos = new Libro() {
                    @Override
                    public double costoComision(double precio) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public double costoEnvio(double precio) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                datos.setCodigo_P(res.getInt("lib_id"));
                datos.setTitulo(res.getString("lib_titulo"));
                datos.setAutor(res.getString("lib_autor"));
                datos.setEdicion(res.getString("lib_edicion"));
                datos.setPrecio(res.getDouble("lib_precio"));
                lista.add(datos);
            }
            sta.close();
            BDConexcion.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
    
    public Libro buscarPais(int codigo) {
        Libro datos = new Libro() {
            @Override
            public double costoComision(double precio) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public double costoEnvio(double precio) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
            
        try {
            String buscar = "SELECT * FROM libro WHERE lib_titulo = ?";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(buscar);
            sta.setInt(1, codigo);
            ResultSet res = sta.executeQuery(); //me devuleve y almacena en el result resultset este es el execute
            res.next();

            datos.setCodigo_P(res.getInt("lib_id"));
            datos.setTitulo(res.getString("lib_titulo"));
            datos.setAutor(res.getString("lib_autor"));
            datos.setEdicion(res.getString("lib_edicion"));
            datos.setPrecio(res.getDouble("lib_precio"));
            
            
            sta.close();
            BDConexcion.desconectar();
            //System.out.println(datos);
            //nombre.setText(datos.getNombre());
            
        } catch (SQLException e) {
            System.out.println("Error al buscar en la base: " + e.getMessage());
        }
        return datos;
    }
    
    public void contar(JTextField texto) {
        try {
            //DatosPersonales d = new DatosPersonales();
            int s;
            String contar = "SELECT MAX(lib_id) FROM libro ";
            BDConexcion.conectar();//conecta
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(contar);//devuelve la coneccion y manda el string
            ResultSet res = sta.executeQuery(); //me devuleve y almacena en el result resultset este es el execute
            res.next();
            s = res.getInt(1) + 1;
            sta.close();//terminar el ejecutado
            BDConexcion.desconectar();//desconectar
            //System.out.println(s);
            texto.setText(String.valueOf(s));
        } catch (SQLException e) {
            System.out.println("Error al contar en la Base de Datos: " + e.getMessage());
        }
    }
}
