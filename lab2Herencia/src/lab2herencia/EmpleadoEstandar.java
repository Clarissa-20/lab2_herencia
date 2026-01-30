/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2herencia;

/**
 *
 * @author riche
 */
public class EmpleadoEstandar extends Empleado{
    
 private static final double DEDUCCION = 0.035; 

    public EmpleadoEstandar(int codigo, String nombre, double salarioBase) {
        super(codigo, nombre, salarioBase);
    }

    @Override
    public double calculoPago() {
        double bruto = super.calculoPago(); 
        return bruto * (1.0 - DEDUCCION);
    }

    @Override
    public String toString() {
        return "[Estandar] " + super.toString();
    }
}
   
