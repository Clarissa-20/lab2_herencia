/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2herencia;

import java.util.Calendar;

/**
 *
 * @author HP
 */
public class EmpleadoVentas extends Empleado{
    private double[] ventasMensuales;
    private double tasaComision;

    public EmpleadoVentas(int codigo, String nombre, double salarioBase, double tasaComision) {
        super(codigo, nombre, salarioBase);
        this.tasaComision = tasaComision;
        this.ventasMensuales = new double[12];
    }

    public void registrarVentas(double monto) {
        if (monto <= 0) return;
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        ventasMensuales[mesActual] += monto;
    }

    public double calcularComision() {
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        return ventasMensuales[mesActual] * tasaComision;
    }

    @Override
    public double calculoPago() {
        return super.calculoPago() + calcularComision();
    }

    public double calcularVentasAnuales() {
        double total = 0;
        for (double v : ventasMensuales) total += v;
        return total;
    }

    public double getTasaComision() { return tasaComision; }
    public void setTasaComision(double tasaComision) { this.tasaComision = tasaComision; }

    @Override
    public String toString() {
        return super.toString()
                + " | Tasa comisión: " + (tasaComision * 100) + "%"
                + " | Ventas anuales: Lps. " + calcularVentasAnuales();
    }
    
}
