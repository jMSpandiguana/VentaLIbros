/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;


/**
 *
 * @author BrymOzl
 */
public abstract class Libro  {
    private int codigo_P;
    private String titulo;
    private String autor;
    private String edicion;
    private String tipo;
    
    private double precio;
    
    public abstract double costoComision(double precio);
    public abstract double costoEnvio(double precio);
            

    public Libro() {
    }

    public int getCodigo_P() {
        return codigo_P;
    }

    public void setCodigo_P(int codigo_P) {
        this.codigo_P = codigo_P;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Libro{" + "codigo_P=" + codigo_P + ", titulo=" + titulo + ", autor=" + autor + ", edicion=" + edicion + ", tipo=" + tipo + ", precio=" + precio + '}';
    }
    

    public double calcularCosto(String edicion, double precio){
        String digital = "Digital";
        String impreso = "Impreso";
        String a;
        Impreso impres = new Impreso();
        Digital digita = new Digital();
        double resultdo;
        double precioa = precio;
        if (edicion.equals(impreso) ) {
            resultdo=impres.costoComision(precioa) + impres.costoEnvio(precioa);
            resultdo = redondearDecimales(resultdo, 2);
            
            return resultdo;
            
            
        } else if (edicion.equals(digital)){
            resultdo=digita.costoComision(precioa) + digita.costoEnvio(precioa);
            
            return resultdo;
        }
        
        return precio;
    }
    
    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
    

    
}
