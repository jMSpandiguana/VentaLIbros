/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BrymOzl
 */
public class BDConexcion {

    private static Connection con = null;

    public static void conectar() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/software","root","root");
            if (con != null) {
                System.out.println("Conecxion exitosa");
            } else {
                System.out.println("Conecxion fallida");
            }
        } catch (SQLException e) {
            System.out.println("Error de Conecxion: " + e.getMessage());
        }
    }

    public static void desconectar() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Desconectado");
            } catch (SQLException e) {
                System.out.println("Error al Desconectar: " + e.getMessage());
            }
        }
    }

    public static Connection getCon() {
        return con;
    }
}
