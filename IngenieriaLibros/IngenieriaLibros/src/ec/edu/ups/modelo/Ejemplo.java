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
public abstract class Ejemplo {
    
    public abstract double costoComision(double precio);
    public abstract double costoEnvio(double precio);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Digital digital = new Digital();
        System.out.println(digital.costoComision(25));
        System.out.println(digital.costoEnvio(25));
        
        Impreso impreso = new Impreso();
        System.out.println(impreso.costoComision(25));
        System.out.println(impreso.costoEnvio(25));
        
    }
    
}
