/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controldor;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.persistencia.BDConexcion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author BrymOzl
 */
public class ControladorCliente {

    public void crear(Cliente datos) {
        try {
            String sql = "INSERT INTO cliente VALUES (?,?,?,?,?,?,?)";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(sql);
            sta.setInt(1, datos.getCodigo());
            sta.setString(2, datos.getCedula());
            sta.setString(3, datos.getNombre());
            sta.setString(4, datos.getApellido());           
            sta.setString(5, datos.getDireccion());
            sta.setString(6, datos.getTelefono());
            sta.setDouble(7, datos.getPeso());
            sta.executeUpdate();
            sta.close();
            BDConexcion.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al insertar en la base de datos: " + ex.getMessage());
        }
    }

    public void actualizar(Cliente datosNuevos) {
        try {
            String actualizar = "UPDATE cliente SET cli_cedula = ?, cli_nombres = ?, cli_apallidos=?, cli_direccion = ?, cli_telefono = ? , cli_credito = ?   WHERE cli_id = ?";
            BDConexcion.conectar();//conecta
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(actualizar);
            sta.setString(1, datosNuevos.getCedula());
            sta.setString(2, datosNuevos.getNombre());
            sta.setString(3, datosNuevos.getApellido());
            sta.setString(4, datosNuevos.getTelefono());
            sta.setString(5, datosNuevos.getDireccion());
            sta.setDouble(6, datosNuevos.getPeso());
            sta.setInt(7, datosNuevos.getCodigo());
            sta.executeUpdate();//Ejecutar las lineas en la base
            sta.close();
            BDConexcion.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminar(Cliente datos) {
        try {
            String eliminar = "DELETE FROM cliente WHERE cli_id = ? ;";
            BDConexcion.conectar();//conecta
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(eliminar);//devuelve la coneccion y manda el string
            sta.setInt(1, datos.getCodigo());
            sta.executeUpdate();//ejecutar
            sta.close();//terminar el ejecutado
            BDConexcion.desconectar();//desconectar
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        }
    }

    public List<Cliente> listarTodo() {
        List<Cliente> lista = new ArrayList<>();
        try {
            String listar = "SELECT * FROM cliente ORDER BY cli_id ASC";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(listar);
            ResultSet res = sta.executeQuery(); //me devuleve y almacena en el result resultset este es el execute
            while (res.next()) {
                Cliente datos = new Cliente();
                datos.setCodigo(res.getInt("cli_id"));
                datos.setCedula(res.getString("cli_cedula"));
                datos.setNombre(res.getString("cli_nombres"));
                datos.setApellido(res.getString("cli_apallidos"));
                datos.setTelefono(res.getString("cli_direccion"));
                datos.setDireccion(res.getString("cli_telefono"));
                datos.setPeso(res.getDouble("cli_credito"));
                lista.add(datos);
            }
            sta.close();
            BDConexcion.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public void buscarCodigo(int codigo, Cliente datos) {
        try {
            //DatosPersonales datos = new DatosPersonales();
            String buscar = "SELECT * FROM cliente WHERE cli_id = ?";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(buscar);
            sta.setInt(1, codigo);
            ResultSet res = sta.executeQuery(); //me devuleve y almacena en el result resultset este es el execute
            res.next();

            datos.setCodigo(res.getInt("cli_id"));
            datos.setCedula(res.getString("cli_cedula"));
            datos.setNombre(res.getString("cli_nombres"));
            datos.setApellido(res.getString("cli_apallidos"));
            datos.setTelefono(res.getString("cli_direccion"));
            datos.setDireccion(res.getString("cli_telefono"));
            datos.setPeso(res.getDouble("cli_credito"));

            sta.close();
            BDConexcion.desconectar();

        } catch (SQLException e) {
            System.out.println("Error al buscar en la base: " + e.getMessage());
        }
    }

    public void contar(JTextField texto) {
        try {
            //DatosPersonales d = new DatosPersonales();
            int s;
            String contar = "SELECT MAX(cli_id) FROM cliente";
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

    public void buscarCedula(String cedula, Cliente datos) {
        try {
            //DatosPersonales datos = new DatosPersonales();
            String buscarCedula = "SELECT * FROM cliente WHERE cli_cedula = ? ";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(buscarCedula);
            sta.setString(1, cedula);
            ResultSet res = sta.executeQuery(); //me devuleve y almacena en el result resultset este es el execute
            res.next();

            datos.setCodigo(res.getInt("cli_id"));
            datos.setCedula(res.getString("cli_cedula"));
            datos.setNombre(res.getString("cli_nombres"));
            datos.setApellido(res.getString("cli_apallidos"));
            datos.setTelefono(res.getString("cli_direccion"));
            datos.setDireccion(res.getString("cli_telefono"));
            datos.setPeso(res.getDouble("cli_credito"));

            sta.close();
            BDConexcion.desconectar();
            //System.out.println(datos);
            //codigo.setText(String.valueOf(datos.getCodigo()));
            //nombre.setText(datos.getNombre());
            //apellido.setText(datos.getApellido());
            //telefono.setText(datos.getTelefono());
            //direccion.setText(datos.getDireccion());
            //peso.setText(String.valueOf(datos.getPeso()));

        } catch (SQLException e) {
            System.out.println("Error al buscar en la base: " + e.getMessage());
        }
    }
}
/*
public void buscarCodigo(int codigo, JTextField cedula) {
        try {
            DatosPersonales datos = new DatosPersonales();
            String buscar = "SELECT * FROM \"Datos_Personales\" WHERE codigo = ?";
            BDConexcion.conectar();
            PreparedStatement sta = BDConexcion.getCon().prepareStatement(buscar);
            sta.setInt(1, codigo);
            ResultSet res = sta.executeQuery(); //me devuleve y almacena en el result resultset este es el execute
            res.next();

            datos.setCodigo(res.getInt("codigo"));
            datos.setCedula(res.getString("cedula"));
            datos.setNombre(res.getString("nombres"));
            datos.setApellido(res.getString("apellidos"));
            datos.setTelefono(res.getString("telefono"));
            datos.setDireccion(res.getString("direccion"));
            datos.setPeso(res.getDouble("peso"));
            
            
            sta.close();
            BDConexcion.desconectar();
            System.out.println(datos);
            cedula.setText(datos.getCedula());
        } catch (SQLException e) {
            System.out.println("Error al buscar en la base: " + e.getMessage());
        }
    }
 */
