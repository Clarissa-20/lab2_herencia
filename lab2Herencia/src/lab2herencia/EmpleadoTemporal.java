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
public class EmpleadoTemporal extends Empleado{
     private Calendar fechaFinContrato;

    public EmpleadoTemporal(int codigo, String nombre, double salarioBase, Calendar fechaFinContrato) {
        super(codigo, nombre, salarioBase);
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public double calculoPago() {
        Calendar hoy = Calendar.getInstance();
        if (hoy.after(fechaFinContrato)) { 
            return 0.0;
        }
        return super.calculoPago();
    }

    public void actualizarFechaFinContrato(Calendar nuevaFecha) {
        this.fechaFinContrato = nuevaFecha;
    }

    public Calendar getFechaFinContrato() {
        return fechaFinContrato;
    }

    @Override
    public String toString() {
        String infoBase = super.toString();

        String fechaFinFormateada = String.format("%02d/%02d/%d",
                fechaFinContrato.get(Calendar.DAY_OF_MONTH),
                fechaFinContrato.get(Calendar.MONTH) + 1,
                fechaFinContrato.get(Calendar.YEAR));

        return infoBase + " | Fin contrato: " + fechaFinFormateada;
    }
    
}
