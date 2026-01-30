/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2herencia;

import java.util.Calendar;

/**
 *
 * @author spodi
 */
public class Empleado {
    protected int codigo;
    protected String nombre;
    protected Calendar fechaCont;
    protected double salarioBase;
    protected int horasTotal;

    public Empleado(int codigo, String nombre, double salarioBase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaCont = Calendar.getInstance(); 
        this.salarioBase = salarioBase;
        this.horasTotal = 0; 
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Calendar getFechaCont() { return fechaCont; }
    public double getSalarioBase() { return salarioBase; }
    public int getHorasTotal() { return horasTotal; }

    public void horasTrabajadas(int horas) {
        if (horas > 0) {
            horasTotal += horas;
        }
    }

    
    public double calculoPago() {
        return (horasTotal / 160.0) * salarioBase;
    }

    public String mostrarInformacion() {
        return "Código: " + codigo +
               " | Nombre: " + nombre +
               " | Fecha de contratación: " + fechaCont.getTime();
    }

    @Override
    public String toString() {
        return mostrarInformacion();
    }
}
