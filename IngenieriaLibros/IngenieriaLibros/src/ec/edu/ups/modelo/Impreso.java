/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

/**
 *
 * @author fernandosanchez
 */
public class Impreso extends Libro{
    
    private double comision =0.02;
    private double envio=20.00;


    @Override
    public double costoComision(double precio) {
        precio = comision*precio;
        return precio;
    }

    @Override
    public double costoEnvio(double precio) {
        precio = envio + precio;
        return precio;
    }
}
